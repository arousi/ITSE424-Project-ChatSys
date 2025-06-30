# ITSE424 Project - Chat System

## Overview

This project is a Chat System developed for the ITSE424 course, focusing on concurrency and the implementation of various design patterns. The system consists of two clients that can communicate with each other through a server. It demonstrates the use of several design patterns, including:

- **Factory Pattern**
- **Observer Pattern**
- **Singleton Pattern**
- **Proxy Pattern**
- **MVC Architecture**

Despite some minor errors when restarting the server and reconnecting the clients, the project showcases the practical application of these patterns in a fun and interactive way.

## Features

- **Two-Client Communication**: The system allows two clients to chat with each other via a server.

- **Server Management**: The server acts as the intermediary, handling client connections and message delivery.

- **Design Patterns**: Implements multiple design patterns to ensure modularity, scalability, and maintainability.

- **Concurrency**: Demonstrates concurrency principles in handling multiple client connections.

## Known Issues

- **Server Restart**: Minor errors occur when restarting the server and reconnecting the clients.

## Project Structure

The project is divided into three main components:

### 1. Chat Frontend

Located in `chat-front_1` and `chat-front_2` directories, this component contains:

- **Controller**: Handles user interactions and updates the view.

- **Model**: Manages the data and business logic.

- **View**: Provides the user interface.

- **Network**: Handles communication with the server.

### 2. Server Dashboard

Located in `chat-server-dashboard_1`, this component contains:

- **Core**: Includes server logic and client handling.

- **Main**: Entry point for the server application.

### Directory Structure

```plaintext
ITSE424-Project-ChatSys
├── chat-front_1
│   ├── build.xml
│   ├── manifest.mf
│   ├── build
│   │   ├── built-jar.properties
│   │   ├── classes
│   │   │   ├── controller
│   │   │   ├── main
│   │   │   ├── model
│   │   │   ├── network
│   │   │   └── view
│   │   ├── empty
│   │   └── generated-sources
│   ├── nbproject
│   ├── src
│   │   ├── controller
│   │   ├── main
│   │   ├── model
│   │   ├── network
│   │   └── view
│   └── test
├── chat-front_2
│   ├── build.xml
│   ├── manifest.mf
│   ├── build
│   ├── nbproject
│   ├── src
│   └── test
├── chat-server-dashboard_1
│   ├── build.xml
│   ├── manifest.mf
│   ├── nbproject
│   ├── src
│   │   ├── chat
│   │   ├── core
│   │   └── Main
│   └── test
└── README.md
```

## How to Run

1. **Start the Server**:

   - Navigate to the `chat-server-dashboard_1` directory.

   - Run the `Main.java` file to start the server.

2. **Start the Clients**:

   - Navigate to the `chat-front_1` and `chat-front_2` directories.

   - Run the `ChatApp.java` file in each directory to start the clients.

3. **Chat**:

   - Use the clients to send and receive messages through the server.

## Future Improvements

- Fix server restart and client reconnection issues.

- Enhance error handling and logging.

- Add support for more clients.

## Conclusion

This project is a great demonstration of concurrency and design patterns in action. While there are minor issues, it serves as a valuable learning experience and a fun exploration of software design principles.
