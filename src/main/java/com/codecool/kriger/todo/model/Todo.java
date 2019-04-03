package com.codecool.kriger.todo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    private boolean completed;

    public Todo(String title, Status status) {
        this.title = title;
        this.status = status;
    }

    public void checkStatusAndSetCompleted() {
        if (status.equals(Status.ACTIVE)) {
            setCompleted(false);
        } else if (status.equals(Status.COMPLETE)) {
            setCompleted(true);
        } else {
            // TODO error handling
        }
    }
}
