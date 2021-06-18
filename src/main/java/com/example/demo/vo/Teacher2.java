package com.example.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class Teacher2{
    private int id;
    private String name;
    private List<Student2> students;
}
