package com.example.server;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class ChatMessage {
    public ChatMessage(String message, String username, String color){
        this.message = message;
        this.username = username;
        this.color = color;
    }

    public String message;
    public String username;
    public String color;
}
