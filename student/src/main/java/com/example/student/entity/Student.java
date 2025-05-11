package com.example.student.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @NotNull(message = "First name is required")
    @Size(min = 1, message = "First name must not be empty")
    @Column(name="first_name")
    private String firstName;


    @NotNull(message = "Last name is required")
    @Size(min = 1, message = "Last name must not be empty")
    @Column(name="last_name")
    private String lastName;


    @Min(value = 0,message = "Marks should be greater than or equal to 0")
    @Max(value = 100,message =  "Marks should be less than or equal to 100" )
    @Column(name="science")
    private int science;


    @Min(value = 0,message = "Marks should be greater than or equal to 0")
    @Max(value = 100,message =  "Marks should be less than or equal to 100" )
    @Column(name="maths")
    private int maths;


    @Min(value = 0,message = "Marks should be greater than or equal to 0")
    @Max(value = 100,message =  "Marks should be less than or equal to 100" )
    @Column(name="art")
    private int art;


    @Min(value = 0, message = "Classes held cannot be negative")
    @Column(name="classes_held")
    private int classesHeld;


    @Min(value = 0, message = "Classes held cannot be negative")
    @Column(name="classes_attended")
    private int classesAttended;



    //No args constructor is compulsorily required by JPA
    public Student(){
        //do nothing
    }

    public Student(String firstName, String lastName, int science, int maths, int art, int classesHeld, int classesAttended) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.science = science;
        this.maths = maths;
        this.art = art;
        this.classesHeld = classesHeld;
        this.classesAttended = classesAttended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public int getArt() {
        return art;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public int getClassesHeld() {
        return classesHeld;
    }

    public void setClassesHeld(int classesHeld) {
        this.classesHeld = classesHeld;
    }

    public int getClassesAttended() {
        return classesAttended;
    }

    public void setClassesAttended(int classesAttended) {
        this.classesAttended = classesAttended;
    }


    //For calculating attendance percentage without adding an extra database column
    public int getAttendancePercentage() {
        if (classesHeld == 0) {
            return 0;
        }
        return (classesAttended * 100) / classesHeld;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", science=" + science +
                ", maths=" + maths +
                ", art=" + art +
                ", classesHeld=" + classesHeld +
                ", classesAttended=" + classesAttended +
                '}';
    }
}
