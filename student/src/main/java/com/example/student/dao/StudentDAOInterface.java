package com.example.student.dao;

import com.example.student.entity.Student;

import java.util.List;

public interface StudentDAOInterface {

    //Get all students;
    List<Student> findAll();

    //Get a particular student
    Student findById(int id);

    //Update a particular student
    Student save(Student student);

    //Delete a particular student
    void deleteById(int id);

}
