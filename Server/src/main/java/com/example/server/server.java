package com.example.server;

import com.example.server.Database.RestoreServer;
import com.example.server.Database.StoredData;
import Plugin.IPlugin;
import Plugin.PluginWrapper;

import com.example.server.PluginRegistry.PluginRegistry;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by ckingsbu on 1/29/18.
 */

public class server {
    // The maximum number of waiting incoming connections to queue.
    // While this value is necessary, for our purposes it is unimportant.
    // Take CS 460 for a deeper understanding of what it means.
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // Java provides an HttpServer class that can be used to embed
    // an HTTP server in any Java program.
    // Using the HttpServer class, you can easily make a Java
    // program that can receive incoming HTTP requests, and respond
    // with appropriate HTTP responses.
    // HttpServer is the class that actually implements the HTTP network
    // protocol (be glad you don't have to).
    // The "server" field contains the HttpServer instance for this program,
    // which is initialized in the "run" method below.
    private HttpServer server;


    private void run(String portNumber) {


        System.out.println("Initializing HTTP Server");

        try {

            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }




        server.createContext("/command", new CommandHandler());
        // Log message indicating that the HttpServer is about the start accepting
        // incoming client connections.
        System.out.println("Starting server");
        try{
            //System.out.println(InetAddress.getLocalHost().getHostAddress());
        }
        catch (Exception e){

        }

        RestoreServer.restore();

        server.start();

        // Log message indicating that the server has successfully started.
        System.out.println("Server started");
    }

    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) {
        String portNumber = args[0];
        String persistenceType = args[1];
        int numCommandsBetweenCheckpoints = Integer.parseInt(args[2]);
        StoredData.instance().SetCount(numCommandsBetweenCheckpoints);
        PluginRegistry registry = new PluginRegistry();
        registry.loadConfiguration(persistenceType);
        IPlugin plugin = (IPlugin) registry.register();
        PluginWrapper.instance().InstallPlugin(plugin);
        PluginWrapper.instance().getPlugin().test();
        if (args.length == 4) {
            if (args[3].equals("clear")) {
                plugin.getUserDao().clear();
                plugin.getGameDao().clear();
                plugin.getCommandDao().clear();
            }
        }
        new server().run(portNumber);
    }
}
