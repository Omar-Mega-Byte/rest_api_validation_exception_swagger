package com.example.todo_api.validator;
import com.example.todo_api.exception.TodoBadRequestException;
import com.example.todo_api.model.Todo;
import org.springframework.stereotype.Service;

@Service
public interface ITodoValidator {
    void validate(Todo todo) throws TodoBadRequestException;
}
