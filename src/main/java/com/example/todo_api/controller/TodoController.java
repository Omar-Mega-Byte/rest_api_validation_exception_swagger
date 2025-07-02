package com.example.todo_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_api.exception.TodoNotFoundException;
import com.example.todo_api.model.Todo;
import com.example.todo_api.repository.TodoRepository;
import com.example.todo_api.validator.TodoValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo Controller", description = "REST API for managing Todo items")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoValidator todoValidator;

    @Operation(summary = "Get all todos", description = "Retrieve a list of all todo items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all todos")
    })
    @GetMapping
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Operation(summary = "Get todo by ID", description = "Retrieve a specific todo item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the todo"),
        @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @GetMapping("/{id}")
    public Todo getById(@Parameter(description = "ID of the todo to retrieve") @PathVariable Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @Operation(summary = "Create a new todo", description = "Create a new todo item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the todo"),
        @ApiResponse(responseCode = "400", description = "Invalid todo data provided")
    })
    @PostMapping
    public Todo create(@Parameter(description = "Todo object to be created") @RequestBody Todo todo) {
        todoValidator.validate(todo);
        return todoRepository.save(todo);
    }

    @Operation(summary = "Update an existing todo", description = "Update an existing todo item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the todo"),
        @ApiResponse(responseCode = "404", description = "Todo not found"),
        @ApiResponse(responseCode = "400", description = "Invalid todo data provided")
    })
    @PutMapping("/{id}")
    public Todo update(
        @Parameter(description = "ID of the todo to update") @PathVariable Long id, 
        @Parameter(description = "Updated todo object") @RequestBody Todo todoDetails) {
        todoValidator.validate(todoDetails);
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(todo);
    }

    @Operation(summary = "Delete a todo", description = "Delete a todo item by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the todo"),
        @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the todo to delete") @PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todoRepository.delete(todo);
    }
}
