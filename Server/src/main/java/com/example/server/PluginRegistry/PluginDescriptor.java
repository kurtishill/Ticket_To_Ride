package com.example.server.PluginRegistry;

import com.google.gson.Gson;

import java.io.File;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

/**
 * Created by frytime on 4/10/18.
 */

public class PluginDescriptor {
    private String name;
    private String className;
    private String filePath;

    public PluginDescriptor(String name, String className, String filePath) {
        this.name = name;
        this.className = className;
        this.filePath = filePath;
    }
    public PluginDescriptor(String configURL){

    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
