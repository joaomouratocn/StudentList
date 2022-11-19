package com.example.studentlist.ui.activity;

import static com.example.studentlist.ui.activity.ConstantActivities.STUDENT_SELECTED;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlist.R;
import com.example.studentlist.model.Student;
import com.example.studentlist.ui.ListStudentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListStudentActivity extends AppCompatActivity {
    private static final String TITLE_APPBAR = "Student List";
    private final ListStudentView listStudentView = new ListStudentView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        setTitle(TITLE_APPBAR);
        configureFabNewStudent();
        configureList();
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
            listStudentView.confirmRemove(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listStudentView.updateStudents();
    }

    private void configureFabNewStudent() {
        FloatingActionButton floatAddStudent = findViewById(R.id.float_new_student);
        floatAddStudent.setOnClickListener(view -> openFormNewStudent());
    }

    private void openFormNewStudent() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    private void configureList() {
        ListView listStudent = findViewById(R.id.list_student);
        listStudentView.configAdapter(listStudent);
        configItemClickList(listStudent);
        registerForContextMenu(listStudent);
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