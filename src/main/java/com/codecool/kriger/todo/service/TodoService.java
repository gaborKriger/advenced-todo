package com.codecool.kriger.todo.service;

import com.codecool.kriger.todo.model.Status;
import com.codecool.kriger.todo.model.Todo;
import com.codecool.kriger.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Todo> listByStatus(String status) {
        List<Todo> allTodo = todoRepository.findAll();

        for (Todo todo : allTodo) {
            todo.checkStatusAndSetCompleted();
        }

        if (status.equals("active")) {
            List<Todo> activeTodo = new ArrayList<>();
            for (Todo todo : allTodo) {
                if (todo.getStatus().equals(Status.ACTIVE)) {
                    activeTodo.add(todo);
                }
            }
            return activeTodo;
        }
        if (status.equals("complete")) {
            List<Todo> completeTodo = new ArrayList<>();
            for (Todo todo : allTodo) {
                if (todo.getStatus().equals(Status.COMPLETE)) {
                    completeTodo.add(todo);
                }
            }
            return completeTodo;
        }
        return allTodo;
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

    // Toggle all status
    public void toggleAllStatus(boolean complete) {
        List<Todo> todos = todoRepository.findAll();

        if (complete) {
            for (Todo todo : todos) {
                todo.setStatus(Status.COMPLETE);
                todoRepository.save(todo);
            }
        } else {
            for (Todo todo :
                    todos) {
                todo.setStatus(Status.ACTIVE);
                todoRepository.save(todo);
            }
        }
    }

    // Remove by id
    public void removeById(Long TodoID) {
        List<Todo> todos = todoRepository.findAll();

        for (Todo todo : todos) {
            if (todo.getId().equals(TodoID)) {
                todoRepository.delete(todo);
                break;
            }
        }
    }

    // Update by id
    public void updateById(Long ID, String title) {
        List<Todo> todos = todoRepository.findAll();

        for (Todo todo : todos) {
            if (todo.getId().equals(ID)) {
                todo.setTitle(title);
                todoRepository.save(todo);
                break;
            }
        }
    }

    // Find by id
    public Todo findById(Long id) {
        return todoRepository.findAll()
                .stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Toggle status by id
    public void toggleStatus(Long id, boolean status) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (status) {
            todo.get().setStatus(Status.COMPLETE);
            todoRepository.save(todo.get());
        } else {
            todo.get().setStatus(Status.ACTIVE);
            todoRepository.save(todo.get());
        }

    }

}
