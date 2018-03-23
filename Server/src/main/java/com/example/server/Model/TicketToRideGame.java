package com.example.server.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tnels on 1/29/2018.
 */

public class TicketToRideGame {

    private List<Player> players;
    private String name;
    private int gameID;
    private int maxNumPlayers;
    private List<String> availableColors;
    private List<ChatMessage> chat;
    private List<TrainCard> deckTrainCards;
    private List<DestinationCard> deckDestinationCards;
    private List<TrainCard> faceUpCards;
    private List<TrainCard> discardPile;
    private List<Route> availableRoutes;
    private List<GameHistory> gameHistoryList;
    private int turn;

    public TicketToRideGame() {
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
        discardPile = new ArrayList<>();
        onStartUp();
        players = new ArrayList<>();
        name = null;
        gameID = 0;
        maxNumPlayers = 0;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        gameHistoryList = new ArrayList<>();
        turn = 0;
    }

    public TicketToRideGame(Player player) {
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
        discardPile = new ArrayList<>();
        onStartUp();
        players = new ArrayList<>();
        addPlayer(player);
        name = null;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        gameHistoryList = new ArrayList<>();
        turn = 0;
    }

    public TicketToRideGame(List<Player> players) {
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
        discardPile = new ArrayList<>();
        onStartUp();
        this.players = players;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        gameHistoryList = new ArrayList<>();
        turn = 0;
    }

    public TicketToRideGame(Player player,
                            String name,
                            int gameID,
                            int maxNumPlayers) {
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
        discardPile = new ArrayList<>();
        onStartUp();
        players = new ArrayList<>();
        addPlayer(player);
        this.name = name;
        this.gameID = gameID;
        this.maxNumPlayers = maxNumPlayers;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        gameHistoryList = new ArrayList<>();
        turn = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public List<ChatMessage> getChat() {
        return chat;
    }

    public void addChat(ChatMessage message) {
        this.chat.add(message);
    }

    public void setChat(List<ChatMessage> chatMessages) {
        this.chat = chatMessages;
    }

    public void addPlayer(Player player)
    {
        if(players.size() < 5)
        {
            for(int i = 0; i < 4; i++)
            {
                player.addTrainCard(deckTrainCards.get(0));
                removeTrainCard(deckTrainCards.get(0));
            }
            players.add(player);
        }
    }

    public List<GameHistory> getGameHistoryList() {
        return gameHistoryList;
    }

    public void addGameHistoryList(GameHistory event) {
        gameHistoryList.add(event);
    }

    public void setGameHistoryList(List<GameHistory> events) {
        gameHistoryList = events;
    }

    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public int getMaxNumPlayers() {
        return maxNumPlayers;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    public void setAvailableColors(List<String> availableColors){
        this.availableColors=availableColors;
    }

    public List<TrainCard> getDeckTrainCards() {
        return deckTrainCards;
    }

    public void addTrainCard(TrainCard card) {
        deckTrainCards.add(card);
    }

    public void removeTrainCard(TrainCard card) {
        for(int i = 0; i < deckTrainCards.size(); i++) {
            if(deckTrainCards.get(i).equals(card))
            {
                deckTrainCards.remove(i);
                break;
            }
        }
    }

    public List<DestinationCard> getDeckDestinationCards() {
        return deckDestinationCards;
    }

    public void addDestinationCard(DestinationCard card) {
        deckDestinationCards.add(card);
    }

    public void removeDestinationCard(DestinationCard card)
    {
        for(int i = 0; i < deckDestinationCards.size(); i++)
        {
            if(deckDestinationCards.get(i).equals(card))
            {
                deckDestinationCards.remove(i);
                break;
            }
        }
    }

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void addFaceUpCard()
    {
        if(faceUpCards.size() < 5)
        {
            faceUpCards.add(deckTrainCards.get(0));
            deckTrainCards.remove(0);
        }
    }

    public void removeFaceUpCard(TrainCard card)
    {
        for(int i = 0; i < faceUpCards.size(); i++)
        {
            if(faceUpCards.get(i).equals(card))
            {
                faceUpCards.remove(i);
                break;
            }
        }
    }

    public List<TrainCard> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(List<TrainCard> discardPile) {
        this.discardPile = discardPile;
    }

    public void addToDiscardPile(List<TrainCard> cards) {
        discardPile.addAll(cards);
    }

    public List<Route> getAvailableRoutes() {
        return availableRoutes;
    }

    public void removeRoute(Route route)
    {
        for(int i = 0; i < availableRoutes.size(); i++)
        {
            if(availableRoutes.get(i).equals(route))
            {
                availableRoutes.remove(i);
                break;
            }
        }
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public void setDeckTrainCards(List<TrainCard> deckTrainCards) {
        this.deckTrainCards = deckTrainCards;
    }

    public void shuffleTrainCards()
    {
        Collections.shuffle(deckTrainCards);
    }

    public void shuffleDestinationCards()
    {
        Collections.shuffle(deckDestinationCards);
    }

    public void changeTurn() {
        turn++;
        if (turn == players.size()) {
            turn = 0;
        }
    }

    public void recycleTrainCardDeck() {
        if (deckTrainCards.size() == 0 && discardPile.size() > 0) {
            Collections.shuffle(discardPile);
            deckTrainCards = new ArrayList<>();
            deckTrainCards.addAll(discardPile);
            discardPile.clear();
            int count = 0;
            int deckSize = deckTrainCards.size();
            for (int i = 0; i < faceUpCards.size() && deckSize > 0; i++) {
                if (faceUpCards.get(i).getColor().equals("null")) {
                    faceUpCards.set(i, deckTrainCards.get(0));
                    count++;
                    deckSize--;
                }
            }

            for (int i = 0; i < count; i++) {
                deckTrainCards.remove(0);
            }
        }
    }

    //hard coding in the cities and routes and cards
    private void onStartUp()
    {
        City duluth = new City("Duluth", 1020.46875f, 261.41748f);
        City washington = new City("Washington", 1646.7773f, 395.5276f);
        City winnipeg = new City("Winnipeg", 814.39453f, 95.27124f);
        City pittsburg = new City("Pittsburg", 1475.6836f, 327.4673f);
        City newOrleans = new City("New Orleans", 1249.5703f, 7628.85547f);
        City chicago = new City("Chicago", 1242.5977f, 349.4839f);
        City miami = new City("Miami", 1648.7695f, 806.92163f);
        City newYork = new City("New York", 1631.7773f, 268.43774f);
        City charleston = new City("Charleston", 1589.7656f, 580.69116f);
        City boston = new City("Boston", 1725.8203f, 158.32178f);
        City atlanta = new City("Atlanta", 1420.6641f, 573.70386f);
        City montreal = new City("Montreal", 1596.7383f, 75.26514f);
        City nashville = new City("Nashville", 1330.6055f, 523.63916f);
        City toronto = new City("Toronto", 1448.6719f, 200.37744f);
        City saintLouis = new City("Saint Louis", 1161.5625f, 468.59766f);
        City oklahomaCity = new City("Oklahoma City", 965.4492f, 586.72266f);
        City raleigh = new City("Raleigh", 1538.7305f, 488.63672f);
        City saultStMarie = new City("Sault St. Marie", 1249.5703f, 167.31958f);
        City elPaso = new City("El Paso", 674.2969f, 745.84863f);
        City denver = new City("Denver", 698.3203f, 487.615f);
        City phoenix = new City("Phoenix", 460.1953f, 698.81616f);
        City santaFe = new City("Santa Fe", 681.3281f, 621.75806f);
        City losAngeles = new City("Los Angeles", 243.10547f, 687.80786f);
        City houston = new City("Houston", 1073.4961f, 769.87573f);
        City lasVegas = new City("Las Vegas", 362.16797f, 602.7407f);
        City dallas = new City("Dallas", 999.4336f, 712.8237f);
        City sanFrancisco = new City("San Francisco", 103.00781f, 535.6692f);
        City seattle = new City("Seattle", 168.04688f, 182.34888f);
        City saltLakeCity = new City("Salt Lake City", 460.1953f, 441.5713f);
        City vancouver = new City("Vancouver", 175.07812f, 108.29004f);
        City portland = new City("Portland", 125.03906f, 256.4077f);
        City littleRock = new City("Little Rock", 1130.5078f, 591.69949f);
        City calgary = new City("Calgary", 407.16797f, 80.24194f);
        City kansasCity = new City("Kansas City", 1003.47656f, 466.58716f);
        City helena = new City("Helena", 590.27344f, 269.4265f);
        City omaha = new City("Omaha", 963.45703f, 391.53955f);

        Route miamiNewOrleans = new Route(6, 15, "red", miami, newOrleans);
        Route miamiAtlanta = new Route(5, 10, "blue", miami, atlanta);
        Route miamiCharleston = new Route(4, 7, "purple", miami, charleston);
        Route charlestonAtlanta = new Route(2, 2, "wild", charleston, atlanta);
        Route charlestonRaleigh = new Route(2, 2, "wild", charleston, raleigh);
        Route atlantaRaleigh1 = new Route(2, 2, "wild", atlanta, raleigh);
        Route atlantaRaleigh2 = new Route(2, 2, "wild", atlanta, raleigh);
        Route atlantaNashville = new Route(1, 1, "wild", atlanta, nashville);
        Route atlantaNewOrleans1 = new Route(4, 7, "orange", atlanta, newOrleans);
        Route atlantaNewOrleans2 = new Route(4, 7, "yellow", atlanta, newOrleans);
        Route newOrleansHouston = new Route(2, 2, "wild", newOrleans, houston);
        Route newOrleansLittleRock = new Route(3, 4, "green", newOrleans, littleRock);
        Route littleRockNashville = new Route(3, 4, "white", littleRock, nashville);
        Route houstonDallas1 = new Route(1, 1, "wild", houston, dallas);
        Route houstonDallas2 = new Route(1, 1, "wild", houston, dallas);
        Route houstonElPaso = new Route(6, 15, "green", houston, elPaso);
        Route elPasoDallas = new Route(4, 7, "red", elPaso, dallas);
        Route elPasoOklahomaCity = new Route(5, 10, "yellow", elPaso, oklahomaCity);
        Route dallasLittleRock = new Route(2, 2, "wild", dallas, littleRock);
        Route dallasOklahomaCity1 = new Route(2, 2, "wild", dallas, oklahomaCity);
        Route dallasOklahomaCity2 = new Route(2, 2, "wild", dallas, oklahomaCity);
        Route oklahomaCityLittleRock = new Route(2, 2, "wild", oklahomaCity, littleRock);
        Route littleRockSaintLouis = new Route(2, 2, "wild", littleRock, saintLouis);
        Route saintLouisNashville = new Route(2, 2, "wild", saintLouis, nashville);
        Route nashvilleRaleigh = new Route(3, 4, "black", nashville, raleigh);
        Route raleighWashington1 = new Route(2, 2, "wild", raleigh, washington);
        Route raleighWashington2 = new Route(2, 2, "wild", raleigh, washington);
        Route raleighPittsburg = new Route(2, 2, "wild", raleigh, pittsburg);
        Route washingtonPittsburg = new Route(2, 2, "wild", washington, pittsburg);
        Route washingtonNewYork1 = new Route(2, 2, "orange", washington, newYork);
        Route washingtonNewYork2 = new Route(2, 2, "black", washington, newYork);
        Route newYorkBoston1 = new Route(2, 2, "red", boston, newYork);
        Route newYorkBoston2 = new Route(2, 2, "yellow", boston, newYork);
        Route pittsburgNewYork1 = new Route(2, 2, "green", pittsburg, newYork);
        Route pittsburgNewYork2 = new Route(2, 2, "white", pittsburg, newYork);
        Route bostonMontreal1 = new Route(2, 2, "wild", boston, montreal);
        Route bostonMontreal2 = new Route(2, 2, "wild", boston, montreal);
        Route newYorkMontreal = new Route(3, 4, "blue", newYork, montreal);
        Route montrealToronto = new Route(3, 4, "wild", montreal, toronto);
        Route torontoPittsburg = new Route(2, 2, "wild", toronto, pittsburg);
        Route pittsburgNashville = new Route(4, 7, "yellow", pittsburg, nashville);
        Route pittsburgSaintLouis = new Route(5, 10, "green", pittsburg, saintLouis);
        Route pittsburgChicago1 = new Route(3, 4, "orange", pittsburg, chicago);
        Route pittsburgChicago2 = new Route(3, 4, "black", pittsburg, chicago);
        Route torontoChicago = new Route(4, 7, "white", toronto, chicago);
        Route torontoSaultStMarie = new Route(2, 2, "wild", toronto, saultStMarie);
        Route torontoDuluth = new Route(6, 15, "purple", toronto, duluth);
        Route montrealSaultStMarie = new Route(5, 10, "black", montreal, saultStMarie);
        Route saultStMarieWinnipeg = new Route(6, 15, "wild", saultStMarie, winnipeg);
        Route saultStMarieDuluth = new Route(3, 4, "wild", saultStMarie, duluth);
        Route chicagoDuluth = new Route(3, 4, "red", chicago, duluth);
        Route chicagoOmaha = new Route(4, 7, "blue", chicago, omaha);
        Route chicagoSaintLouis1 = new Route(2, 2, "green", chicago, saintLouis);
        Route chicagoSaintLouis2 = new Route(2, 2, "white", chicago, saintLouis);
        Route saintLoiusKansasCity1 = new Route(2, 2, "blue", saintLouis, kansasCity);
        Route saintLoiusKansasCity2 = new Route(2, 2, "purple", saintLouis, kansasCity);
        Route kansasCityOmaha1 = new Route(1, 1, "wild", kansasCity, omaha);
        Route kansasCityOmaha2 = new Route(1, 1, "wild", kansasCity, omaha);
        Route kansasCityOklahomaCity1 = new Route(2, 2, "wild", kansasCity, oklahomaCity);
        Route kansasCityOklahomaCity2 = new Route(2, 2, "wild", kansasCity, oklahomaCity);
        Route kansasCityDenver1 = new Route(4, 7, "black", kansasCity, denver);
        Route kansasCityDenver2 = new Route(4, 7, "orange", kansasCity, denver);
        Route duluthOmaha1 = new Route(2, 2, "wild", duluth, omaha);
        Route duluthOmaha2 = new Route(2, 2, "wild", duluth, omaha);
        Route omahaDenver = new Route(4, 7, "purple", omaha, denver);
        Route omahaHelena = new Route(5, 10, "red", omaha, helena);
        Route duluthWinnipeg = new Route(4, 7, "black", duluth, winnipeg);
        Route duluthHelena = new Route(5, 10, "orange", duluth, helena);
        Route oklahomaCityDenver = new Route(4, 7, "red", oklahomaCity, denver);
        Route oklahomaCitySantaFe = new Route(3, 4, "blue", oklahomaCity, santaFe);
        Route elPasoSantaFe = new Route(2, 2, "wild", elPaso, santaFe);
        Route santaFeDenver = new Route(2, 2, "wild", santaFe, denver);
        Route elPasoLosAngeles = new Route(6, 15, "black", elPaso, losAngeles);
        Route elPasoPhoenix = new Route(3, 4, "wild", elPaso, phoenix);
        Route phoenixSantaFe = new Route(3, 4, "wild", phoenix, santaFe);
        Route denverHelena = new Route(4, 7, "green", denver, helena);
        Route helenaWinnipeg = new Route(4, 7, "blue", helena, winnipeg);
        Route winnipegCalgary = new Route(6, 15, "white", winnipeg, calgary);
        Route helenaCalgary = new Route(4, 7, "wild", helena, calgary);
        Route helenaSeattle = new Route(6, 15, "yellow", helena, seattle);
        Route helenaSaltLakeCity = new Route(3, 4, "purple", helena, saltLakeCity);
        Route denverSaltLakeCity1 = new Route(3, 4, "red", denver, saltLakeCity);
        Route denverSaltLakeCity2 = new Route(3, 4, "yellow", denver, saltLakeCity);
        Route denverPhoenix = new Route(5, 10, "white", denver, phoenix);
        Route phoenixLosAngeles = new Route(3, 4, "wild", phoenix, losAngeles);
        Route losAngelesLasVegas = new Route(2, 2, "wild", losAngeles, lasVegas);
        Route lasVegasSaltLakeCity = new Route(3, 4, "orange", lasVegas, saltLakeCity);
        Route calgaryVancouver = new Route(3, 4, "wild", calgary, vancouver);
        Route calgarySeattle = new Route(4, 7, "wild", calgary, seattle);
        Route vanCouverSeattle1 = new Route(1, 1, "wild", vancouver, seattle);
        Route vanCouverSeattle2 = new Route(1, 1, "wild", vancouver, seattle);
        Route seattlePortland1 = new Route(1, 1, "wild", seattle, portland);
        Route seattlePortland2 = new Route(1, 1, "wild", seattle, portland);
        Route portlandSaltLakeCity = new Route(6, 15, "blue", portland, saltLakeCity);
        Route saltLakeCitySanFrancisco1 = new Route(5, 10, "orange", saltLakeCity, sanFrancisco);
        Route saltLakeCitySanFrancisco2 = new Route(5, 10, "white", saltLakeCity, sanFrancisco);
        Route sanFranciscoPortland1 = new Route(5, 10, "green", sanFrancisco, portland);
        Route sanFranciscoPortland2 = new Route(5, 10, "purple", sanFrancisco, portland);
        Route losAngelesSanFrancisco1 = new Route(3, 4, "purple", losAngeles, sanFrancisco);
        Route losAngelesSanFrancisco2 = new Route(3, 4, "yellow", losAngeles, sanFrancisco);

        if(maxNumPlayers > 3)
        {
            availableRoutes.add(losAngelesSanFrancisco2);
            availableRoutes.add(sanFranciscoPortland2);
            availableRoutes.add(saltLakeCitySanFrancisco2);
            availableRoutes.add(seattlePortland2);
            availableRoutes.add(vanCouverSeattle2);
            availableRoutes.add(denverSaltLakeCity2);
            availableRoutes.add(atlantaRaleigh2);
            availableRoutes.add(atlantaNewOrleans2);
            availableRoutes.add(houstonDallas2);
            availableRoutes.add(dallasOklahomaCity2);
            availableRoutes.add(raleighWashington2);
            availableRoutes.add(washingtonNewYork2);
            availableRoutes.add(bostonMontreal2);
            availableRoutes.add(newYorkBoston2);
            availableRoutes.add(pittsburgNewYork2);
            availableRoutes.add(pittsburgChicago2);
            availableRoutes.add(chicagoSaintLouis2);
            availableRoutes.add(saintLoiusKansasCity2);
            availableRoutes.add(kansasCityOmaha2);
            availableRoutes.add(kansasCityDenver2);
            availableRoutes.add(kansasCityOklahomaCity2);
            availableRoutes.add(duluthOmaha2);
        }

        availableRoutes.add(losAngelesSanFrancisco1);
        availableRoutes.add(sanFranciscoPortland1);
        availableRoutes.add(saltLakeCitySanFrancisco1);
        availableRoutes.add(portlandSaltLakeCity);
        availableRoutes.add(seattlePortland1);
        availableRoutes.add(vanCouverSeattle1);
        availableRoutes.add(calgarySeattle);
        availableRoutes.add(calgaryVancouver);
        availableRoutes.add(lasVegasSaltLakeCity);
        availableRoutes.add(losAngelesLasVegas);
        availableRoutes.add(phoenixLosAngeles);
        availableRoutes.add(denverPhoenix);
        availableRoutes.add(denverSaltLakeCity1);
        availableRoutes.add(helenaSaltLakeCity);
        availableRoutes.add(miamiNewOrleans);
        availableRoutes.add(miamiAtlanta);
        availableRoutes.add(miamiCharleston);
        availableRoutes.add(charlestonAtlanta);
        availableRoutes.add(charlestonRaleigh);
        availableRoutes.add(atlantaRaleigh1);
        availableRoutes.add(atlantaNashville);
        availableRoutes.add(atlantaNewOrleans1);
        availableRoutes.add(newOrleansHouston);
        availableRoutes.add(newOrleansLittleRock);
        availableRoutes.add(littleRockNashville);
        availableRoutes.add(houstonDallas1);
        availableRoutes.add(houstonElPaso);
        availableRoutes.add(elPasoDallas);
        availableRoutes.add(elPasoOklahomaCity);
        availableRoutes.add(dallasLittleRock);
        availableRoutes.add(dallasOklahomaCity1);
        availableRoutes.add(oklahomaCityLittleRock);
        availableRoutes.add(littleRockSaintLouis);
        availableRoutes.add(saintLouisNashville);
        availableRoutes.add(nashvilleRaleigh);
        availableRoutes.add(raleighWashington1);
        availableRoutes.add(raleighPittsburg);
        availableRoutes.add(washingtonPittsburg);
        availableRoutes.add(washingtonNewYork1);
        availableRoutes.add(newYorkBoston1);
        availableRoutes.add(pittsburgNewYork1);
        availableRoutes.add(bostonMontreal1);
        availableRoutes.add(newYorkMontreal);
        availableRoutes.add(montrealToronto);
        availableRoutes.add(torontoPittsburg);
        availableRoutes.add(pittsburgNashville);
        availableRoutes.add(pittsburgSaintLouis);
        availableRoutes.add(pittsburgChicago1);
        availableRoutes.add(torontoChicago);
        availableRoutes.add(torontoSaultStMarie);
        availableRoutes.add(torontoDuluth);
        availableRoutes.add(montrealSaultStMarie);
        availableRoutes.add(saultStMarieWinnipeg);
        availableRoutes.add(saultStMarieDuluth);
        availableRoutes.add(chicagoDuluth);
        availableRoutes.add(chicagoOmaha);
        availableRoutes.add(chicagoSaintLouis1);
        availableRoutes.add(saintLoiusKansasCity1);
        availableRoutes.add(kansasCityOmaha1);
        availableRoutes.add(kansasCityOklahomaCity1);
        availableRoutes.add(kansasCityDenver1);
        availableRoutes.add(duluthOmaha1);
        availableRoutes.add(omahaDenver);
        availableRoutes.add(omahaHelena);
        availableRoutes.add(duluthWinnipeg);
        availableRoutes.add(duluthHelena);
        availableRoutes.add(oklahomaCityDenver);
        availableRoutes.add(oklahomaCitySantaFe);
        availableRoutes.add(elPasoSantaFe);
        availableRoutes.add(santaFeDenver);
        availableRoutes.add(elPasoLosAngeles);
        availableRoutes.add(elPasoPhoenix);
        availableRoutes.add(phoenixSantaFe);
        availableRoutes.add(denverHelena);
        availableRoutes.add(helenaWinnipeg);
        availableRoutes.add(winnipegCalgary);
        availableRoutes.add(helenaCalgary);
        availableRoutes.add(helenaSeattle);

        DestinationCard seattleNewYork = new DestinationCard(seattle, newYork, 22);
        DestinationCard duluthHouston = new DestinationCard(duluth, houston, 8);
        DestinationCard helenaLosAngeles = new DestinationCard(helena, losAngeles, 8);
        DestinationCard losAngelesMiami = new DestinationCard(losAngeles, miami, 20);
        DestinationCard sanFranciscoAtlanta = new DestinationCard(sanFrancisco, atlanta, 17);
        DestinationCard chicagoNewOrleans = new DestinationCard(chicago, newOrleans, 7);
        DestinationCard chicagoSantaFe = new DestinationCard(chicago, santaFe, 9);
        DestinationCard seattleLosAngeles = new DestinationCard(seattle, losAngeles, 9);
        DestinationCard calgarySaltLakeCity = new DestinationCard(calgary, saltLakeCity, 7);
        DestinationCard portlandPhoenix = new DestinationCard(portland, phoenix, 11);
        DestinationCard denverElPaso = new DestinationCard(denver, elPaso, 4);
        DestinationCard winnipegHouston = new DestinationCard(winnipeg, houston, 12);
        DestinationCard duluthElPaso = new DestinationCard(duluth, elPaso, 10);
        DestinationCard saultStMarieOklahomaCity = new DestinationCard(saultStMarie, oklahomaCity, 9);
        DestinationCard denverPittsburg = new DestinationCard(denver, pittsburg, 11);
        DestinationCard losAngelesChicago = new DestinationCard(losAngeles, chicago, 16);
        DestinationCard portlandNashville = new DestinationCard(portland, nashville, 17);
        DestinationCard torontoMiami = new DestinationCard(toronto, miami, 10);
        DestinationCard vancouverSantaFe = new DestinationCard(vancouver, santaFe, 13);
        DestinationCard calgaryPhoenix = new DestinationCard(calgary, phoenix, 13);
        DestinationCard montrealNewOrleans = new DestinationCard(montreal, newOrleans, 13);
        DestinationCard montrealAtlanta = new DestinationCard(montreal, atlanta, 9);
        DestinationCard losAngelesNewYork = new DestinationCard(losAngeles, newYork, 21);
        DestinationCard kansasCityHouston = new DestinationCard(kansasCity, houston, 5);
        DestinationCard saultStMarieNashville = new DestinationCard(saultStMarie, nashville, 8);
        DestinationCard winnipegLittleRock = new DestinationCard(winnipeg, littleRock, 11);
        DestinationCard vancouverMontreal = new DestinationCard(vancouver, montreal,20);
        DestinationCard newYorkAtlanta = new DestinationCard(newYork, atlanta, 6);
        DestinationCard dallasNewYork = new DestinationCard(dallas, newYork, 11);
        DestinationCard bostonMiami = new DestinationCard(boston, miami, 12);

        deckDestinationCards.add(seattleNewYork);
        deckDestinationCards.add(duluthHouston);
        deckDestinationCards.add(helenaLosAngeles);
        deckDestinationCards.add(losAngelesMiami);
        deckDestinationCards.add(sanFranciscoAtlanta);
        deckDestinationCards.add(chicagoNewOrleans);
        deckDestinationCards.add(chicagoSantaFe);
        deckDestinationCards.add(seattleLosAngeles);
        deckDestinationCards.add(calgarySaltLakeCity);
        deckDestinationCards.add(portlandPhoenix);
        deckDestinationCards.add(denverElPaso);
        deckDestinationCards.add(winnipegHouston);
        deckDestinationCards.add(duluthElPaso);
        deckDestinationCards.add(saultStMarieOklahomaCity);
        deckDestinationCards.add(denverPittsburg);
        deckDestinationCards.add(losAngelesChicago);
        deckDestinationCards.add(portlandNashville);
        deckDestinationCards.add(torontoMiami);
        deckDestinationCards.add(vancouverSantaFe);
        deckDestinationCards.add(calgaryPhoenix);
        deckDestinationCards.add(montrealNewOrleans);
        deckDestinationCards.add(montrealAtlanta);
        deckDestinationCards.add(losAngelesNewYork);
        deckDestinationCards.add(kansasCityHouston);
        deckDestinationCards.add(saultStMarieNashville);
        deckDestinationCards.add(winnipegLittleRock);
        deckDestinationCards.add(vancouverMontreal);
        deckDestinationCards.add(newYorkAtlanta);
        deckDestinationCards.add(dallasNewYork);
        deckDestinationCards.add(bostonMiami);

        for(int i = 0; i < 12; i++)
        {
            deckTrainCards.add(new TrainCard("purple"));
            deckTrainCards.add(new TrainCard("white"));
            deckTrainCards.add(new TrainCard("blue"));
            deckTrainCards.add(new TrainCard("yellow"));
            deckTrainCards.add(new TrainCard("orange"));
            deckTrainCards.add(new TrainCard("black"));
            deckTrainCards.add(new TrainCard("red"));
            deckTrainCards.add(new TrainCard("green"));
        }

        for(int i = 0; i < 14; i++)
        {
            deckTrainCards.add(new TrainCard("wild"));
        }

        shuffleTrainCards();
        shuffleDestinationCards();

        for (int i = 0; i < 5; i++) {
            faceUpCards.add(deckTrainCards.get(i));
        }

        for (int i = 0; i < 5; i++) {
            deckTrainCards.remove(0);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;

        TicketToRideGame other = (TicketToRideGame) o;

        return other.getGameID() == this.getGameID();
    }

    @Override
    public int hashCode() {
        return 31 * gameID + maxNumPlayers + name.hashCode();
    }
}
