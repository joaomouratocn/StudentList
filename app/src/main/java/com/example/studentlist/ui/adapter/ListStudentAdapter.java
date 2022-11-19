package com.example.studentlist.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.studentlist.R;
import com.example.studentlist.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends BaseAdapter {
    private final List<Student> studentList = new ArrayList<>();
    private final Context context;

    public ListStudentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Student getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studentList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View inflate = createView(viewGroup);
        Student studentSelect = studentList.get(position);
        bindsView(inflate, studentSelect);
        return inflate;
    }

    private void bindsView(View inflate, Student studentSelect) {
        TextView txtName = inflate.findViewById(R.id.txt_item_student_name);
        TextView txtPhone = inflate.findViewById(R.id.txt_item_student_phone);
        txtName.setText(studentSelect.getName());
        txtPhone.setText(studentSelect.getPhone());
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_student_layout, viewGroup, false);
    }

    public void updateList(@NonNull List<Student> students){
        studentList.clear();
        studentList.addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        studentList.remove(student);
        notifyDataSetChanged();
    }
}
