package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    private StudentServiceInterface studentServiceInterface;

    @Autowired
    public StudentWebController(StudentServiceInterface studentServiceInterface) {
        this.studentServiceInterface = studentServiceInterface;
    }

    @GetMapping("/student_list")
    public String get_list(Model model) {
        model.addAttribute("students", studentServiceInterface.findAll());
        return "student-list";
    }

    @GetMapping("/studentForm")
    public String show_form(Model model) {
        Student new_student = new Student();
        model.addAttribute("student", new_student);
        return "student-form";
    }

    @PostMapping("/processForm")
    public String process_form(@Valid @ModelAttribute("student") Student student,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student-form";
        } else {
            studentServiceInterface.save(student);
            return "redirect:/students/student_list";
        }
    }
}
