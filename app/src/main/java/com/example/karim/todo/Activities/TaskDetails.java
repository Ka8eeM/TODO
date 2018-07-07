package com.example.karim.todo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karim.todo.DataBaseModels.ProjectDB;
import com.example.karim.todo.DataBaseModels.Task;
import com.example.karim.todo.R;

import java.util.List;

public class TaskDetails extends AppCompatActivity {

    Intent intent;
    TextView taskName, taskDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        setTitle("Task Details");
        taskName = findViewById(R.id.task_name_);
        taskDesc = findViewById(R.id.desc_task);
        intent = getIntent();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Task> tasks ;
                if (intent.getData() == null) {
                    Toast.makeText(getBaseContext(), "Error! return null", Toast.LENGTH_SHORT).show();
                } else {
                    ProjectDB pdb = ProjectDB.getAppDatabase(TaskDetails.this);
                    int id = Integer.parseInt(String.valueOf(intent.getData()));
                    Log.e(TaskDetails.class.getSimpleName(), String.valueOf(id) + " ID is ");
                    tasks = pdb.taskDao().getAllTasks();
                    Task task ;
                    task = tasks.get(id);
                    taskName.setText(task.getTaskName().toString());
                    taskDesc.setText(task.getDesc().toString());
                    //Toast.makeText(getBaseContext(), "It works", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }
}