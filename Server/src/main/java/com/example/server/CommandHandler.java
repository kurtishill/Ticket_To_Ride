package com.example.server;


import com.example.server.Database.DeleteGameData;
import com.example.server.Database.StoredData;
import com.example.server.Model.ChatMessage;
import com.example.server.Model.City;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.Route;
import com.example.server.Model.TrainCard;
import com.example.server.Results.GenericCommand;
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
                    int gameID = 0;
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
                        gameID = d.intValue();
                        command = CommandFactory.instance().JoinGame(d.intValue(), authToken);
                        ClientCommandManager.instance().addCommand(authToken,
                                    "UpdateGameList");
                    }
                    else if (commandValues.get(0).equals("CreateGame")) {
                        Double d = (Double) commandValues.get(2);
                        gameID = d.intValue();
                        command = CommandFactory.instance().CreateGame(commandValues.get(1).toString(),
                                d.intValue(), commandValues.get(3).toString(), authToken);
                        ClientCommandManager.instance().addCommand(authToken, "UpdateGameList");
                    }
                    else if (commandValues.get(0).equals("UpdateChat")) {
                        Double gameId = (Double) commandValues.get(4);
                        gameID = gameId.intValue();
                        ChatMessage message = new ChatMessage(commandValues.get(1).toString(),
                                commandValues.get(2).toString(), commandValues.get(3).toString());
                        command = CommandFactory.instance().UpdateChat(message, gameId.intValue());
                        ClientCommandManager.instance().addGameCommand(gameId.intValue(),
                                "GetChat");
                    }
                    else if (commandValues.get(0).equals("GetChat")) {
                        Double d = (Double) commandValues.get(1);
                        gameID = d.intValue();
                        String username = (String) commandValues.get(2);
                        command = CommandFactory.instance().GetChat(d.intValue(), username);
                    }
                    else if (commandValues.get(0).equals("DrawDestinationTickets")){
                        Double d = (Double) commandValues.get(2); //gameID
                        gameID = d.intValue();
                        //THESE VALUES MIGHT BE WRONG
                        command = CommandFactory.instance().DrawDestinationTickets(
                                (String) commandValues.get(1), d.intValue());
                        ClientCommandManager.instance().addGameCommand(d.intValue(), "DrawDestinationTickets");
                    }
                    else if (commandValues.get(0).equals("SelectDestinationTickets")) {
                        Double d = (Double) commandValues.get(2); //gameid
                        gameID = d.intValue();
                        ArrayList<Object> selectedCards = (ArrayList) commandValues.get(3);
                        ArrayList<Object> discardedCards = (ArrayList) commandValues.get(4);

                        ArrayList<DestinationCard> reconstructedSelectedCards = new ArrayList<>();
                        ArrayList<DestinationCard> reconstructedDiscardedCards = new ArrayList<>();

                        for (int i = 0; i < selectedCards.size(); i++) {
                            LinkedTreeMap selectedCard = (LinkedTreeMap) selectedCards.get(i);
                            //get city 1 and city 2
                            LinkedTreeMap city1Map = (LinkedTreeMap) selectedCard.get("city1");
                            LinkedTreeMap city2Map = (LinkedTreeMap) selectedCard.get("city2");
                            Double pointValueDouble= (Double) selectedCard.get("pointValue");
                            int pointValue = pointValueDouble.intValue();

                            City city1 = new City((String) city1Map.get("name"),
                                    ((Double) city1Map.get("x")).floatValue(),
                                    ((Double)city1Map.get("y")).floatValue());
                            City city2 = new City((String) city2Map.get("name"),
                                    ((Double) city2Map.get("x")).floatValue(),
                                    ((Double)city2Map.get("y")).floatValue());
                            DestinationCard card = new DestinationCard(city1, city2, pointValue);
                            reconstructedSelectedCards.add(card);
                        }
                        for (int i = 0; i < discardedCards.size(); i++) {
                            LinkedTreeMap discardedCard = (LinkedTreeMap) discardedCards.get(i);
                            //get city 1 and city 2
                            LinkedTreeMap city1Map = (LinkedTreeMap) discardedCard.get("city1");
                            LinkedTreeMap city2Map = (LinkedTreeMap) discardedCard.get("city2");
                            Double pointValueDouble= (Double) discardedCard.get("pointValue");
                            int pointValue = pointValueDouble.intValue();
                            City city1 = new City((String) city1Map.get("name"),
                                    ((Double) city1Map.get("x")).floatValue(),
                                    ((Double)city1Map.get("y")).floatValue());
                            City city2 = new City((String) city2Map.get("name"),
                                    ((Double) city2Map.get("x")).floatValue(),
                                    ((Double)city2Map.get("y")).floatValue());
                            DestinationCard card = new DestinationCard(city1, city2, pointValue);
                            reconstructedDiscardedCards.add(card);
                        }



                        command = CommandFactory.instance().SelectDestinationTickets(
                                (String) commandValues.get(1), d.intValue(), reconstructedSelectedCards, reconstructedDiscardedCards);
                        ClientCommandManager.instance().addGameCommand(d.intValue(), "UpdateGameList");
                    }
                    else if (commandValues.get(0).equals("DrawTwoCardsFromBank")) {
                        Double gameId = (Double) commandValues.get(5);
                        gameID = gameId.intValue();
                        ArrayList<Object> selectedCards = (ArrayList) commandValues.get(1);
                        ArrayList<Object> faceUpCards = (ArrayList) commandValues.get(2);
                        ArrayList<Object> trainCardDeck = (ArrayList) commandValues.get(3);
                        ArrayList<Object> discardPile = (ArrayList) commandValues.get(4);

                        ArrayList<TrainCard> reconstructedSelectedCards = new ArrayList<>();
                        ArrayList<TrainCard> reconstructedFaceUpCards = new ArrayList<>();
                        ArrayList<TrainCard> reconstructedTrainCardDeck = new ArrayList<>();
                        ArrayList<TrainCard> reconstructedDiscardPile = new ArrayList<>();

                        // this is really ugly, but I think necessary because of how Gson treats generic types (e.g. ArrayList)
                        for (int i = 0; i < selectedCards.size(); i++) {
                            LinkedTreeMap<String, String> selectedCard = (LinkedTreeMap) selectedCards.get(i);
                            TrainCard card = new TrainCard(selectedCard.get("color"));
                            reconstructedSelectedCards.add(card);
                        }
                        for (int i = 0; i < faceUpCards.size(); i++) {
                            LinkedTreeMap<String, String> faceUpCard = (LinkedTreeMap) faceUpCards.get(i);
                            TrainCard card = new TrainCard(faceUpCard.get("color"));
                            reconstructedFaceUpCards.add(card);
                        }
                        for (int i = 0; i < trainCardDeck.size(); i++) {
                            LinkedTreeMap<String, String> deckCard = (LinkedTreeMap) trainCardDeck.get(i);
                            TrainCard card = new TrainCard(deckCard.get("color"));
                            reconstructedTrainCardDeck.add(card);
                        }
                        for (int i = 0; i < discardPile.size(); i++) {
                            LinkedTreeMap<String, String> discardCard = (LinkedTreeMap) discardPile.get(i);
                            TrainCard card = new TrainCard(discardCard.get("color"));
                            reconstructedDiscardPile.add(card);
                        }

                        command = CommandFactory.instance().DrawCardsFromBank(reconstructedSelectedCards,
                                reconstructedFaceUpCards, reconstructedTrainCardDeck, reconstructedDiscardPile,
                                gameId.intValue(), authToken);

                        ClientCommandManager.instance().addGameCommand(gameId.intValue(), "UpdateGameList");
                    }

                    else if (commandValues.get(0).equals("DeleteGame")) {
                        Double gameId = (Double) commandValues.get(1);
                        gameID = gameId.intValue();
                        command = CommandFactory.instance().DeleteGame(gameId.intValue());
                        DeleteGameData.instance().DeleteGame(gameID); //call plugin and delete the game
                        ClientCommandManager.instance().addCommand(authToken, "UpdateGameList");
                    }

                    else if (commandValues.get(0).equals("ClaimRoute")) {
                        String playerName = (String) commandValues.get(1);
                        Double gameId = (Double) commandValues.get(2);
                        gameID = gameId.intValue();
                        LinkedTreeMap routeMap = (LinkedTreeMap) commandValues.get(3);
                        LinkedTreeMap city1Map = (LinkedTreeMap) routeMap.get("city1");
                        String city1Name = (String) city1Map.get("name");
                        float city1x = ((Double)city1Map.get("x")).floatValue();
                        float city1y = ((Double)city1Map.get("y")).floatValue();
                        City city1 = new City(city1Name, city1x,city1y);

                        LinkedTreeMap city2Map = (LinkedTreeMap) routeMap.get("city2");
                        String city2Name = (String) city2Map.get("name");
                        float city2x = ((Double)city2Map.get("x")).floatValue();
                        float city2y = ((Double)city2Map.get("y")).floatValue();
                        City city2 = new City(city2Name, city2x,city2y);

                        String color = (String) routeMap.get("color");
                        int length = ((Double) routeMap.get("length")).intValue();
                        boolean occupied = (Boolean) routeMap.get("occupied");
                        int pointValue = ((Double) routeMap.get("pointValue")).intValue();

                        Route route = new Route(length,pointValue,color,city1,city2);

                        command = CommandFactory.instance().ClaimRoute(playerName,gameId.intValue(),route);
                        ClientCommandManager.instance().addGameCommand(gameId.intValue(), "UpdateGameList");

                    }
                    else {
                            command = CommandFactory.instance().GetGameList(authToken);

                    }

                    if (!commandValues.get(0).equals("GetGameList") && !commandValues.get(0).equals("GetChat")
                            && !commandValues.get(0).equals("Register") && !commandValues.get(0).equals("Login")
                            && !commandValues.get(0).equals("CreateGame")) {
                        StoredData.instance().Store((GenericCommand) command, gameID);
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
