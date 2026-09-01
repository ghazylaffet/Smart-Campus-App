package com.example.smartcampus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnChat, btnTasks, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 🔥 Start notification service
        Intent service = new Intent(this, NotificationService.class);
        startService(service);

        btnChat = findViewById(R.id.btnChat);
        btnTasks = findViewById(R.id.btnTasks);
        btnLogout = findViewById(R.id.btnLogout);

        // 💬 Chat
        btnChat.setOnClickListener(v -> {
            startActivity(new Intent(this, ChatActivity.class));
        });

        // 📌 Tasks
        btnTasks.setOnClickListener(v -> {
            startActivity(new Intent(this, TaskActivity.class));
        });

        // 🔓 Logout
        btnLogout.setOnClickListener(v -> {

            SharedPreferences sp =
                    getSharedPreferences("USER", MODE_PRIVATE);

            sp.edit().clear().apply();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            finish();
        });
    }
}