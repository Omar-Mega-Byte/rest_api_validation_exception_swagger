package com.example.todo_api.validator;

import org.springframework.stereotype.Service;

import com.example.todo_api.exception.TodoBadRequestException;
import com.example.todo_api.model.Todo;
import com.example.todo_api.utils.StringUtils;

@Service
public class TodoValidator implements ITodoValidator {
    @Override
    public void validate(Todo todo) {
        if (StringUtils.isBlank(todo.getTitle())) {
            throw new TodoBadRequestException("Title is required");
        }
        if (StringUtils.isBlank(todo.getDescription())) {
            throw new TodoBadRequestException("Description is required");
        }
    }
}
