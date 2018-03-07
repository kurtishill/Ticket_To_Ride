package gui_facade;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by frytime on 2/26/18.
 */

public class SelectDestinationTicketsService {
    public void selectCards(Player player, List<DestinationCard> selectedCards, List<DestinationCard> discardedCards){
        TicketToRideGame game = ClientModelRoot.instance().getCurrGame();

        //give player his new routes
        for(int i=0; i<selectedCards.size(); i++){
            player.addDestinationCard(selectedCards.get(i));
        }
        //put discarded routes on the bottom of the deck
        for(int i=0; i<discardedCards.size(); i++){
            game.getDeckDestinationCards().add(discardedCards.get(i));
        }

    }
}
