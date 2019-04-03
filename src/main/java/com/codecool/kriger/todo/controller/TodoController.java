package com.codecool.kriger.todo.controller;

import com.codecool.kriger.todo.model.Todo;
import com.codecool.kriger.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Todo> listByStatus(@RequestParam("status") String status) {
        return todoService.listByStatus(status);
    }

    // Remove all completed
    @DeleteMapping("/todos/completed")
    public String removeAllCompleted() {
        todoService.removeAllCompleted();
        return SUCCESS;
    }

    // Toggle all status
    @PutMapping("/todos/toggle_all")
    public String toggleAllStatus(@RequestParam("toggle-all") String complete) {
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
    public String updateById(@PathVariable("todoID") Long todoID,
                             @RequestParam("todo-title") String title) {
        todoService.updateById(todoID, title);
        return SUCCESS;
    }

    // Find by id
    @GetMapping("/todos/{todoID}")
    public void findById(@PathVariable("todoID") Long todoID) {
        todoService.findById(todoID);
    }

    // Toggle status by id
    @PutMapping("/todos/{todoID}/toggle_status")
    public String toggleStatusById(@PathVariable("todoID") Long todoID,
                                   @RequestParam("status") boolean status) {
        todoService.toggleStatus(todoID, status);
        return SUCCESS;
    }

}
