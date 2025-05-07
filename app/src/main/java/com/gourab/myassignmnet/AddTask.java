package com.gourab.myassignmnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task); // Loads the new layout
        setupButtonClickListener();
        setupBackButton();
    }

    private void setupButtonClickListener() {
        Button addButton = findViewById(R.id.buttonAddTask);
        EditText editTaskName = findViewById(R.id.taskName);
        EditText editTaskDescription = findViewById(R.id.taskDescription);

        if (addButton != null) {
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String taskName = editTaskName.getText().toString();
                    String taskDescription = editTaskDescription.getText().toString();

                    Intent intent = new Intent(AddTask.this, TaskAdded.class);
                    intent.putExtra("taskName", taskName);
                    intent.putExtra("taskDescription", taskDescription);
                    startActivity(intent);
                }
            });
        }
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
