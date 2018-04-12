package com.example.server.PluginRegistry;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by frytime on 4/10/18.
 */

public class PluginRegistry {
    private PluginDescriptor descriptor;

    public PluginRegistry(PluginDescriptor descriptor) {
        this.descriptor = descriptor;
    }
    public PluginRegistry(){

    }

    /**
     * returns an object from the plugin that needs to be cast to a plugin interface
     * @return
     */
    public Object register(){

        try {
            URL[] classLoaderURLs = new URL[]{new URL(descriptor.getFilePath())};
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderURLs);
            Class<?> pluginClass = urlClassLoader.loadClass(descriptor.getClassName());
            Constructor<?> constructor = pluginClass.getConstructor();
            Object pluginObject = constructor.newInstance();
            return pluginObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void loadConfiguration(String persistenceType){
        Gson gson = new Gson();
        try {
            PluginDescriptor[] descriptors = gson.fromJson(new FileReader("Server/config.json"), PluginDescriptor[].class);
            if(descriptors[0].getName().equals(persistenceType))
                this.descriptor = descriptors[0];
            else this.descriptor = descriptors[1];


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
