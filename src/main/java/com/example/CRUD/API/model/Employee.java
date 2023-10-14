package com.example.CRUD.API.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[\\u0621-\\u064A]+$", message = "Name must contain only Arabic letters")
    private String name;


    @Column(unique = true)
    @NotNull(message = "National Id is not Valid")
    @Size(min=14, max=14,message = "National Id is not Valid")
    private String nationalId;


    private int age;


    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
