package com.example.studentlist.ui.activity;

import static com.example.studentlist.ui.activity.ConstantActivities.STUDENT_SELECTED;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlist.R;
import com.example.studentlist.dao.StudentDAO;
import com.example.studentlist.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListStudentActivity extends AppCompatActivity {
    private static final String TITLE_APPBAR = "Student List";
    private final StudentDAO studentDAO = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        setTitle(TITLE_APPBAR);
        configureFabNewStudent();
        configureList();
//        studentDAO.saveStudent(new Student("João Mourato", "16991376210", "joaoubamit@gmail.com"));
//        studentDAO.saveStudent(new Student("Arthur Henrique", "16997321066", "arthurh@gmail.com"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_students_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.act_list_student_item_menu_remove){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student studentSelect = adapter.getItem(menuInfo.position);
            removeStudent(studentSelect);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStudents();
    }

    private void updateStudents() {
        adapter.clear();
        adapter.addAll(studentDAO.getAll());
    }

    private void configureFabNewStudent() {
        FloatingActionButton floatAddStudent = findViewById(R.id.float_new_student);
        floatAddStudent.setOnClickListener(view -> openFormNewStudent());
    }

    private void openFormNewStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    private void configureList() {
        ListView listStudent = findViewById(R.id.listv_student);
        configAdapter(listStudent);
        configItemClickList(listStudent);
        registerForContextMenu(listStudent);
    }

    private void removeStudent(Student student) {
        studentDAO.removeStudent(student);
        adapter.remove(student);
    }

    private void configAdapter(ListView listStudent) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listStudent.setAdapter(adapter);
    }

    private void configItemClickList(ListView listStudent) {
        listStudent.setOnItemClickListener((adapterView, view, position, id) -> {
            Student studentSelected = (Student) adapterView.getItemAtPosition(position);
            openFormEditStudent(studentSelected);
        });
    }

    private void openFormEditStudent(Student studentSelected) {
        Intent goToFormWithStudent = new Intent(ListStudentActivity.this, FormStudentActivity.class);
        goToFormWithStudent.putExtra(STUDENT_SELECTED, studentSelected);
        startActivity(goToFormWithStudent);
    }
}