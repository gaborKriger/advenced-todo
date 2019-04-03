package com.codecool.kriger.todo;

import com.codecool.kriger.todo.model.Status;
import com.codecool.kriger.todo.model.Todo;
import com.codecool.kriger.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Todo first = Todo.builder()
                    .status(Status.COMPLETE)
                    .title("First")
                    .build();

            todoRepository.save(first);

            Todo second = Todo.builder()
                    .status(Status.ACTIVE)
                    .title("Second")
                    .build();

            todoRepository.save(second);

            Todo third = Todo.builder()
                    .status(Status.COMPLETE)
                    .title("Third")
                    .build();

            todoRepository.save(third);
        };
    }

}
