package com.example.karim.todo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.karim.todo.Adapters.ListAdapter;
import com.example.karim.todo.DataBaseModels.ProjectDB;
import com.example.karim.todo.DataBaseModels.Task;
import com.example.karim.todo.R;

import java.util.List;

public class TodoList extends AppCompatActivity {

    FloatingActionButton fab;
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    List<Task> tasks;
    ProjectDB pdb;

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter = new ListAdapter(tasks, this);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        setTitle("TODO");
        recyclerView = findViewById(R.id.root_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fab_add);
        pdb = ProjectDB.getAppDatabase(this);
        tasks = pdb.taskDao().getAllTasks();
        listAdapter = new ListAdapter(tasks, this);
        recyclerView.setAdapter(listAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoList.this, CreateTask.class);

                startActivity(intent);
            }
        });
    }
}
