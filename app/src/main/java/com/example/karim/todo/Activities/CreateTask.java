package com.example.karim.todo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.karim.todo.DataBaseModels.ProjectDB;
import com.example.karim.todo.DataBaseModels.Task;
import com.example.karim.todo.R;

public class CreateTask extends AppCompatActivity {


    Button btnSave, btnCancel;
    ProjectDB pdb;
    TextInputEditText etTaskName, etTaskDesc;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setTitle("Create Task");
        btnSave = findViewById(R.id.save_tsk);
        btnCancel = findViewById(R.id.cancel_tsk);
        etTaskName = findViewById(R.id.get_task_name);
        etTaskDesc = findViewById(R.id.get_task_desc);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etTaskDesc.getText().equals("") == true || etTaskName.getText().equals("") == true) {
                    Toast.makeText(getBaseContext(), "task name or task desc can not be null!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Task task = new Task();
                            task.setTaskName(etTaskName.getText().toString());
                            task.setDesc(etTaskDesc.getText().toString());
                            pdb = ProjectDB.getAppDatabase(getBaseContext());
                            pdb.taskDao().insertAll(task);
                            //etTaskDesc.setText("");
                            //etTaskName.setText("");
                            //Toast.makeText(getBaseContext(), "Task successfully saved!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).start();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Operation Canceled!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
