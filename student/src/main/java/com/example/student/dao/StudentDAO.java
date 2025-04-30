package com.example.student.dao;

import com.example.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAO implements StudentDAOInterface{

    private EntityManager entityManager;

    @Autowired
    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {

        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        //First fetch the student
        Student student_delete = entityManager.find(Student.class,id);
        if (student_delete!=null) {
            entityManager.remove(student_delete);
        }
    }
}
