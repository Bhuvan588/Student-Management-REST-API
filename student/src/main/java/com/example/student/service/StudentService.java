package com.example.student.service;

import com.example.student.dao.StudentDAOInterface;
import com.example.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceInterface{

    private StudentDAOInterface studentDAOInterface;

    @Autowired
    public StudentService(StudentDAOInterface studentDAOInterface) {
        this.studentDAOInterface = studentDAOInterface;
    }

    @Override
    public List<Student> findAll() {
        return studentDAOInterface.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentDAOInterface.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentDAOInterface.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentDAOInterface.deleteById(id);
    }
}
