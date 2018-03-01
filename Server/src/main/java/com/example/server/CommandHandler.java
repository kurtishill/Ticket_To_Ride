package com.example.server;


import com.example.server.Model.ChatMessage;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Results.ICommand;
import com.example.server.Results.Result;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckingsbu on 1/29/18.
 */
public class CommandHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;

        try {

            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                InputStream reqData = exchange.getRequestBody();

                if (reqData != null) {
                    // Check to see if an "Authorization" header is present
                    String authToken = null;
                    if (reqHeaders.containsKey("Authorization")) {
                        authToken = reqHeaders.getFirst("Authorization");
                    }

                    String reqBody = Serializer.readString(reqData);

                    List<Object> commandValues = (ArrayList) Serializer.decode(reqBody, ArrayList.class);

                    ICommand command;
                    if (commandValues.get(0).equals("Login")) {
                        command = CommandFactory.instance().Login(commandValues.get(1).toString(),
                                commandValues.get(2).toString());
                    }
                    else if (commandValues.get(0).equals("Register")) {
                        command = CommandFactory.instance().Register(commandValues.get(1).toString(),
                                commandValues.get(2).toString());
                    }
                    else if (commandValues.get(0).equals("JoinGame")) {
                        Double d = (Double) commandValues.get(1);
                        command = CommandFactory.instance().JoinGame(d.intValue(), authToken);
                        ClientCommandManager.instance().addCommand(authToken,
                                    "UpdateGameList");
                    }
                    else if (commandValues.get(0).equals("CreateGame")) {
                        Double d = (Double) commandValues.get(2);
                        command = CommandFactory.instance().CreateGame(commandValues.get(1).toString(),
                                d.intValue(), commandValues.get(3).toString(), authToken);
                        ClientCommandManager.instance().addCommand(authToken, "UpdateGameList");
                    }
                    else if (commandValues.get(0).equals("UpdateChat")) {
                        Double gameId = (Double) commandValues.get(4);
                        ChatMessage message = new ChatMessage(commandValues.get(1).toString(),
                                commandValues.get(2).toString(), commandValues.get(3).toString());
                        command = CommandFactory.instance().UpdateChat(message, gameId.intValue());
                        ClientCommandManager.instance().addGameCommand(gameId.intValue(),
                                "GetChat");
                    }
                    else if (commandValues.get(0).equals("GetChat")) {
                        Double d = (Double) commandValues.get(1);
                        String username = (String) commandValues.get(2);
                        command = CommandFactory.instance().GetChat(d.intValue(), username);
                    }
                    else if (commandValues.get(0).equals("DrawDestinationTickets")){
                        Double d = (Double) commandValues.get(2);
                        //THESE VALUES MIGHT BE WRONG
                        command = CommandFactory.instance().DrawDestinationTicekts(
                                (Player) commandValues.get(1), d.intValue()); //fixme an error was thrown
                        ClientCommandManager.instance().addCommand(authToken, "DrawDestinationTickets");
                    }
                    else if (commandValues.get(0).equals("SelectDestinationTickets")){
                        Double d = (Double) commandValues.get(2);
                        List<DestinationCard> selectedCards = (ArrayList) commandValues.get(3);
                        List<DestinationCard> discardedCards =(ArrayList) commandValues.get(4);
                        //FIXME idk what to do here so it creates a command correctly
                        command = CommandFactory.instance().SelectDestinationTicekts(
                                (Player) commandValues.get(1), d.intValue(), selectedCards, discardedCards);
                        ClientCommandManager.instance().addCommand(authToken, "SelectDestinationTickets");
                    }
                    else {
                            command = CommandFactory.instance().GetGameList(authToken);

                    }

                    Result result = (Result) command.execute();

                    String respData = Serializer.encode(result);


                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    // Now that the status code and headers have been sent to the client,
                    // next we send the JSON Locations in the HTTP response body.

                    // Get the response body output stream.
                    OutputStream respBody = exchange.getResponseBody();
                    // Write the JSON string to the output stream.
                    Serializer.writeString(respData, respBody);
                    // Close the output stream.  This is how Java knows we are done
                    // sending Locations and the response is complete/
                    respBody.close();

                    success = true;
                }
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                String respData = "{\n" +
                        "\"message\": \"Error in logging in\"\n" +
                        "}\n";
                OutputStream respBody = exchange.getResponseBody();
                // Write the JSON string to the output stream.
                Serializer.writeString(respData, respBody);
                respBody.close();
            }
        }
        catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            String respData = "{\n" +
                    "\"message\": \""+e.getMessage()+"\"\n" +
                    "}\n";
            OutputStream respBody = exchange.getResponseBody();
            // Write the JSON string to the output stream.
            Serializer.writeString(respData, respBody);
            // Close the output stream.  This is how Java knows we are done
            // sending Locations and the response is complete/
            respBody.close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }
}
