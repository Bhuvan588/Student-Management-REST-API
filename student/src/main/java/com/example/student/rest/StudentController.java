package com.example.student.rest;


import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import com.example.student.service.StudentServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/class")
public class StudentController {

    private StudentServiceInterface studentServiceInterface;

    public StudentController(StudentServiceInterface studentServiceInterface) {
        this.studentServiceInterface = studentServiceInterface;
    }

    @GetMapping("/students")
    public List<Student> findAll()
    {
        return studentServiceInterface.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student findById(@PathVariable int studentId)
    {
        return studentServiceInterface.findById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student)
    {
        student.setId(0);
        studentServiceInterface.save(student);
        return student;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student)
    {
        studentServiceInterface.save(student);
        return student;
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {
        Student temp = studentServiceInterface.findById(studentId);
        if (temp == null) {
            throw new RuntimeException("Student id not found - " + studentId);
        }
        studentServiceInterface.deleteById(studentId);
        return "Deleted teacher id - " + studentId;
    }
}
