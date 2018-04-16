package com.example.server.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class ChatMessage implements Serializable {

    private String message;
    private String username;
    private String color;

    public ChatMessage(String message, String username, String color){
        this.message = message;
        this.username = username;
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getColor() {
        return color;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;

        ChatMessage other = (ChatMessage) o;

        return this.message.equals(other.message) &&
                this.username.equals(other.username) &&
                this.color.equals(other.color);
    }

    @Override
    public int hashCode() {
        return 31 * message.hashCode() + username.hashCode();
    }
}
