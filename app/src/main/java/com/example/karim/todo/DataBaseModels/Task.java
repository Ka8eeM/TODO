package com.example.karim.todo.DataBaseModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {


    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "taskName")
    private String taskName;

    @ColumnInfo(name = "desc")
    private String desc;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
