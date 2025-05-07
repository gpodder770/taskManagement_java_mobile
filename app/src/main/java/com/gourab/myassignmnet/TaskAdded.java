package com.gourab.myassignmnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskAdded extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_added); // Loads the new layout
        NavigationHelper.setupBackButton(this, R.id.buttonBack);
        String taskName = getIntent().getStringExtra("taskName");
        String taskDescription = getIntent().getStringExtra("taskDescription");

        // Set retrieved values to TextViews
        TextView textViewTaskName = findViewById(R.id.viewTaskName);
        TextView textViewTaskDescription = findViewById(R.id.viewTaskDescription);

        textViewTaskName.setText(taskName);
        textViewTaskDescription.setText(taskDescription);

        goHome();
        setupBackButton();
    }
    protected void goHome(){
        Button goHomeButton = findViewById(R.id.goHome);

        goHomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TaskAdded.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupBackButton() {
        ImageButton backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // ✅ Closes current activity
            }
        });
    }


}