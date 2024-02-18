package com.maleman.taskm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;
    private String title;
    private String type;
    private Date dueDate;
    private String description;
}
