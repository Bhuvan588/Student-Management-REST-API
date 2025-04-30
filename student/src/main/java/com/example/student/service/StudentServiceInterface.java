package com.example.student.service;

import com.example.student.entity.Student;

import java.util.List;

public interface StudentServiceInterface {

    List<Student> findAll();


    Student findById(int id);


    Student save(Student student);


    void deleteById(int id);
}
