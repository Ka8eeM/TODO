package com.example.karim.todo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.karim.todo.R;

public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    private static int splashScreen = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome Page");
        imageView = findViewById(R.id.image_logo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo_todo));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, TodoList.class);
                startActivity(intent);
            }
        }, splashScreen);
    }
}