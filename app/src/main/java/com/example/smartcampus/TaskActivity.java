package com.example.smartcampus;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    EditText taskEdit;
    Button addTaskBtn;
    ListView taskList;
    ProgressBar progressBar;

    ArrayList<String> tasks;
    ArrayAdapter<String> adapter;

    DatabaseReference db;

    int completed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskEdit = findViewById(R.id.taskEdit);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        taskList = findViewById(R.id.taskList);
        progressBar = findViewById(R.id.progressBar);

        tasks = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                tasks
        );

        taskList.setAdapter(adapter);
        taskList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        db = FirebaseDatabase.getInstance().getReference("tasks");

        addTaskBtn.setOnClickListener(v -> {

            String task = taskEdit.getText().toString().trim();

            if (task.isEmpty()) return;

            db.push().setValue(task).addOnSuccessListener(a -> {

                NotificationHelper.show(
                        this,
                        "New Task",
                        task
                );
            });

            taskEdit.setText("");
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                tasks.clear();

                for (DataSnapshot data : snapshot.getChildren()) {

                    String task = data.getValue(String.class);

                    if (task != null) {
                        tasks.add(task);
                    }
                }

                adapter.notifyDataSetChanged();
                updateProgress();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });

        taskList.setOnItemClickListener((parent, view, position, id) -> {
            updateProgress();
        });
    }

    private void updateProgress() {

        int total = tasks.size();
        int done = taskList.getCheckedItemCount();

        if (total == 0) {
            progressBar.setProgress(0);
            return;
        }

        int progress = (done * 100) / total;
        progressBar.setProgress(progress);
    }
}