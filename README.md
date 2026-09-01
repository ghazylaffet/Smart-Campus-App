# Smart Campus – Firebase Backend

Smart Campus is a university-oriented digital platform designed to centralize campus communication and academic task management.

This repository contains the **Firebase backend/database structure** and documentation for the Smart Campus project. The Android application source code is intentionally not included in this package.

## Features

- Firebase Authentication with email/password
- User profiles
- Real-time campus messaging
- Academic task management
- Task completion tracking
- Real-time data synchronization
- Firebase Realtime Database security rules
- Structure ready for Android integration

## Firebase Services

The project is designed around:

- **Firebase Authentication** – user registration and login
- **Firebase Realtime Database** – users, messages, and tasks
- **Firebase Cloud Messaging (optional)** – push notifications

## Realtime Database Structure

```text
root
├── users
│   └── userId
│       ├── username
│       ├── email
│       └── createdAt
│
├── messages
│   └── messageId
│       ├── senderId
│       ├── senderName
│       ├── message
│       └── timestamp
│
└── tasks
    └── taskId
        ├── userId
        ├── title
        ├── description
        ├── dueDate
        ├── completed
        └── createdAt
```

## Example Database

The file `firebase/database.example.json` contains example data that can be used as a reference when creating the database.

## Firebase Setup

1. Open the Firebase Console.
2. Create a new Firebase project.
3. Enable **Authentication → Email/Password**.
4. Create a **Realtime Database**.
5. Import or create the database structure shown in `firebase/database.example.json`.
6. Apply the security rules from `firebase/database.rules.json`.
7. Connect the Firebase project to your Android application when integrating the frontend.

## Security

The included rules require authentication before users can access application data.

For a production deployment, rules should be adapted to the application's exact authorization requirements.


## Future Android Integration

The Android application can use this Firebase backend to:

- Authenticate users
- Read and update user profiles
- Send and receive messages in real time
- Create and update academic tasks
- Track task completion
- Synchronize information between multiple devices
