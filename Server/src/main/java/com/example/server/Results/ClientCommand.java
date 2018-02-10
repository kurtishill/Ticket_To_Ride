package com.example.server.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HillcollegeMac on 2/9/18.
 */

public class ClientCommand {

    private List<Object> data;
    private String type;

    public ClientCommand() {

    }

    public ClientCommand(String type) {
        data = new ArrayList<>();
        this.type = type;
    }

    public List<Object> getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public void addData(Object obj) {
        data.add(obj);
    }

    public void setType(String type) {
        this.type = type;
    }
}
