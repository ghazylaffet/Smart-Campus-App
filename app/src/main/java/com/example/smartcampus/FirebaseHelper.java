package com.example.smartcampus;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private static final DatabaseReference db =
            FirebaseDatabase.getInstance().getReference();

    public static DatabaseReference getMessages() {
        return db.child("messages");
    }

    public static DatabaseReference getTasks() {
        return db.child("tasks");
    }
}