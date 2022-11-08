package com.example.studentlist.ui.activity;

import static com.example.studentlist.ui.activity.ConstantActivities.STUDENT_SELECTED;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlist.R;
import com.example.studentlist.dao.StudentDAO;
import com.example.studentlist.model.Student;

public class FormStudentActivity extends AppCompatActivity {
    private static final String TITLE_APPBAR_NEW_STUDENT = "New Student";
    private static final String TITLE_APPBAR_EDIT_STUDENT = "Edit Student";
    private final StudentDAO studentDAO = new StudentDAO();
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        initFields();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_students_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.act_form_student_menu_save){finishForm();}
        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent data = getIntent();
        if (data.hasExtra(STUDENT_SELECTED)){
            setTitle(TITLE_APPBAR_EDIT_STUDENT);
            student = (Student) data.getSerializableExtra(STUDENT_SELECTED);
            if (student != null){
                setFields();
            }
        }else {
            setTitle(TITLE_APPBAR_NEW_STUDENT);
            student = new Student();
        }
    }

    private void setFields() {
        edtName.setText(student.getName());
        edtPhone.setText(student.getPhone());
        edtEmail.setText(student.getEmail());
    }

    private void finishForm() {
        getStudent();
        if (student.hasId()){
            studentDAO.editStudent(student);
        }else {studentDAO.saveStudent(student);}
        finish();
    }

    private void initFields() {
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
    }

    private void getStudent() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
    }
}