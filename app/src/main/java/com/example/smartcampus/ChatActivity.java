package com.example.smartcampus;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    EditText messageEdit;
    Button sendBtn;
    ListView listView;

    ArrayList<String> messages;
    ArrayAdapter<String> adapter;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageEdit = findViewById(R.id.messageEdit);
        sendBtn = findViewById(R.id.sendBtn);
        listView = findViewById(R.id.listView);

        messages = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                messages
        );

        listView.setAdapter(adapter);

        db = FirebaseDatabase.getInstance().getReference("messages");

        sendBtn.setOnClickListener(v -> {

            String msg = messageEdit.getText().toString().trim();

            if (msg.isEmpty()) return;

            String email = FirebaseAuth.getInstance()
                    .getCurrentUser()
                    .getEmail();

            String username = email != null ? email.split("@")[0] : "user";

            Message model = new Message(username, msg);

            db.push().setValue(model).addOnSuccessListener(a -> {

                NotificationHelper.show(
                        this,
                        "New Message",
                        username + ": " + msg
                );
            });

            messageEdit.setText("");
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                messages.clear();

                for (DataSnapshot data : snapshot.getChildren()) {

                    Message msg = data.getValue(Message.class);

                    if (msg != null) {
                        messages.add(msg.username + " : " + msg.message);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}