package com.example.studentlist.dao;

import androidx.annotation.Nullable;

import com.example.studentlist.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final static List<Student> students = new ArrayList<>();
    private static int countId = 1;

    public void saveStudent(Student student) {
        student.setId(countId);
        students.add(student);
        addId();
    }

    private void addId() {
        countId++;
    }

    public void editStudent(Student student) {
        Student studentFind = getStudentById(student);
        if (studentFind != null){
            int studentPosition = students.indexOf(studentFind);
            students.set(studentPosition, student);
        }
    }

    @Nullable
    private Student getStudentById(Student student) {
        for (Student a : students) {
            if (a.getId() == student.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public void removeStudent(Student selectStudent) {
        Student studentFind = getStudentById(selectStudent);
        if (studentFind != null){
            students.remove(studentFind);
        }
    }
}
