package com.gourab.myassignmnet;

public class Task {
    private String name;

    // ✅ No-argument constructor required by Firebase
    public Task() {}

    // ✅ Constructor for manual task creation
    public Task(String name) {
        this.name = name;
    }

    // ✅ Getter for Firebase mapping
    public String getName() { return name; }

    // ✅ Optional: Setter for updating task name (if needed)
    public void setName(String name) { this.name = name; }
}
