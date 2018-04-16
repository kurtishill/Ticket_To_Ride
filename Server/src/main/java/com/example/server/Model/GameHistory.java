package com.example.server.Model;

import java.io.Serializable;

/**
 * Created by kurtis on 3/1/18.
 */

public class GameHistory implements Serializable {

    private String username;
    private String color;
    private String historyMessage;

    public GameHistory(String username, String color, String historyMessage) {
        this.username = username;
        this.color = color;
        this.historyMessage = historyMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getColor() {
        return color;
    }

    public String getHistoryMessage() {
        return historyMessage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHistoryMessage(String historyMessage) {
        this.historyMessage = historyMessage;
    }

    @Override
    public int hashCode() {
        return 31 * username.hashCode() + historyMessage.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;

        GameHistory other = (GameHistory) o;

        return this.username.equals(other.username) &&
                this.color.equals(other.color) &&
                this.historyMessage.equals(other.historyMessage);
    }

    @Override
    public String toString() {
        return username + " - " + historyMessage;
    }
}
