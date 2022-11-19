package com.example.studentlist;

import android.app.Application;

import com.example.studentlist.dao.StudentDAO;
import com.example.studentlist.model.Student;

public class StudentListApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        insertStudents();
    }

    private void insertStudents() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.saveStudent(new Student("João Mourato", "16991376210", "joaoubamit@gmail.com"));
        studentDAO.saveStudent(new Student("Arthur Henrique", "16997321066", "arthurh@gmail.com"));
    }
}
