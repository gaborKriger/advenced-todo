package com.codecool.kriger.todo.controller;

import com.codecool.kriger.todo.model.Todo;
import com.codecool.kriger.todo.repository.TodoRepository;
import com.codecool.kriger.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TodoController {

    private static final String SUCCESS = "{\"success\":true}";

    private TodoRepository todoRepository;
    private TodoService todoService;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    // Add new
    @PostMapping("/addTodo")
    public String addNew(@RequestBody @Valid String incomingTodoTitle) {
        todoService.addNew(incomingTodoTitle);
        return SUCCESS;
    }

    // List by id
    @PostMapping("/list")
    public List<Todo> listByID() {
        return todoService.listByID();
    }

    // Remove all completed
    @DeleteMapping("/todos/completed")
    public String removeAllCompleted() {
        todoService.removeAllCompleted();
        return SUCCESS;
    }

    // Toggle all status
    @PutMapping("/todos/toggle_all")
    public String toggleAllStatus() {
        // TODO
        return SUCCESS;
    }

}
