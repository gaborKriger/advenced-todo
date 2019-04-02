package com.codecool.kriger.todo.controller;

import com.codecool.kriger.todo.model.Todo;
import com.codecool.kriger.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TodoController {

    private static final String SUCCESS = "{\"success\":true}";

    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    // Add new
    @PostMapping("/addTodo")
    public String addNew(@RequestParam("todo-title") String incomingTodoTitle) {
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
    public String toggleAllStatus(@RequestBody @Valid String complete) {
        todoService.toggleAllStatus(complete.equals("true"));
        return SUCCESS;
    }

    // Remove by id
    @DeleteMapping("/todos/{todoID}")
    public String removeById(@PathVariable("todoID") Long todoID) {
        todoService.removeById(todoID);
        return SUCCESS;
    }

    // Update by id
    @PutMapping("/todos/{todoID}")
    public String updateById(@RequestParam("todo-title") String title,
                             @PathVariable("todoID") Long todoID) {
        todoService.updateById(todoID, title);
        return SUCCESS;
    }

}
