package com.codecool.kriger.todo.service;

import com.codecool.kriger.todo.model.Status;
import com.codecool.kriger.todo.model.Todo;
import com.codecool.kriger.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Add new
    public void addNew(String incomingTodoTitle) {
        Todo newTodo = new Todo(incomingTodoTitle, Status.ACTIVE);
        todoRepository.save(newTodo);
    }

    // List by id
    public List<Todo> listByID() {
        return todoRepository.findAll();
    }

    // Remove all completed
    public void removeAllCompleted() {
        List<Todo> todos = todoRepository.findAll();
        for (Todo todo : todos) {
            if (todo.getStatus().equals(Status.COMPLETE)) {
                todoRepository.delete(todo);
            }
        }
    }

}
