package com.example.studentlist.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.studentlist.dao.StudentDAO;
import com.example.studentlist.model.Student;
import com.example.studentlist.ui.adapter.ListStudentAdapter;

public class ListStudentView {

    private final ListStudentAdapter adapter;
    private final Context context;
    private final StudentDAO studentDAO;

    public ListStudentView(Context context) {
        this.context = context;
        adapter = new ListStudentAdapter(this.context);
        studentDAO = new StudentDAO();
    }

    public void updateStudents() {
        adapter.updateList(studentDAO.getAll());
    }

    public void removeStudent(Student student) {
        studentDAO.removeStudent(student);
        adapter.remove(student);
    }

    public void configAdapter(ListView listStudent) {
        listStudent.setAdapter(adapter);
    }

    public void confirmRemove(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Remove Student")
                .setMessage("really want to remove the student?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student studentSelect = adapter.getItem(menuInfo.position);
                    removeStudent(studentSelect);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
