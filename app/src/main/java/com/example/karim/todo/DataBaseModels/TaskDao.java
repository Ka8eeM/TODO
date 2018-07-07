package com.example.karim.todo.DataBaseModels;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM task WHERE uid=:myID")
    Task getTask(int myID);

    @Insert
    void insertAll(Task... tasks);

    @Delete
    void deleteTask(Task task);


}
