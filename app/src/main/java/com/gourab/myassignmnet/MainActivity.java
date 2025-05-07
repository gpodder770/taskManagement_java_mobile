package com.gourab.myassignmnet;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private LinearLayout taskContainer;
//    private List<String> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        taskContainer = findViewById(R.id.taskListLayout);
        addTaskView();
//        loadJsonData();
        fetchTaskData();
    }

    private void addTaskView() {
        FloatingActionButton btnSwitch = findViewById(R.id.floatingActionButton);

        if (btnSwitch != null) {
            btnSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DEBUG", "Floating button clicked! Navigating to AddTask.");
                    Intent intent = new Intent(MainActivity.this, AddTask.class);
                    startActivity(intent); // ✅ Opens AddTaskActivity
                }
            });
        } else {
            Log.e("ERROR", "Floating button not found! Check XML ID.");
        }
    }

//    With Json File
//    private void loadJsonData() {
//        try {
//            AssetManager assetManager = getAssets();
//            InputStream inputStream = assetManager.open("tasks.json");
//            InputStreamReader reader = new InputStreamReader(inputStream);
//
//            Gson gson = new Gson();
//            JsonObject root = gson.fromJson(reader, JsonObject.class);
//            JsonArray tasksArray = root.getAsJsonArray("tasks");
//
//            taskList.clear();
//            for (int i = 0; i < tasksArray.size(); i++) {
//                JsonObject taskObj = tasksArray.get(i).getAsJsonObject();
//                taskList.add(taskObj.get("name").getAsString());
//            }
//
//            reader.close();
//            inputStream.close();
//
//            displayTasks(); // ✅ Calls method to dynamically add TextViews
//
//        } catch (IOException e) {
//            Log.e("ERROR", "Error loading JSON: " + e.getMessage());
//        }
//    }
//
//
//    private void displayTasks() {
//        for (String task : taskList) {
//            TextView taskView = new TextView(this);
//            taskView.setText(task);
//            taskView.setTextSize(18);
//            taskView.setPadding(8, 8, 8, 8);
//            taskContainer.addView(taskView); // ✅ Adds dynamically created TextView to the layout
//        }
//    }
//    With Json File


    private void fetchTaskData() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://myassignmnet-5ed76-default-rtdb.firebaseio.com/").getReference("tasks");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskContainer.removeAllViews(); // ✅ Clear previous views

                if (snapshot.exists()) {
                    Log.d("DEBUG", "Tasks found: " + snapshot.getChildrenCount());
                    for (DataSnapshot taskSnapshot : snapshot.getChildren()) {
                        Task task = taskSnapshot.getValue(Task.class);
                        if (task != null) {
                            Log.d("DEBUG", "Task loaded: " + task.getName());
                            addTaskToUI(task.getName()); // ✅ Add only task name
                        }
                    }
                } else {
                    Log.e("ERROR", "No tasks found in Firebase.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ERROR", "Failed to load data: " + error.getMessage());
            }
        });
    }



    private void addTaskToUI(String taskName) {
        // ✅ Create TextView for Task Name
        TextView taskView = new TextView(this);
        taskView.setText(taskName);
        taskView.setTextSize(17);
        taskView.setPadding(8, 8, 8, 8);

        // ✅ Add TextView to Main Container in `main_activity.xml`
        taskContainer.addView(taskView);
    }


}
