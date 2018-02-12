package gui_facade;

import com.example.server.Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class GetAvailablePlayerColorsService {

    /**
     * gets the colors that available for a new player to pick from
     * @return the list of available colors represented by boolean values (because the
     * colors are being presented in radio buttons)
     */
    public static List<Boolean> getPlayerColors() {
        List<Boolean> availableColors = new ArrayList<>();
            availableColors.add(true);
            availableColors.add(true);
            availableColors.add(true);
            availableColors.add(true);
            availableColors.add(true);
        Map<String, Player> players = ClientModelRoot.instance().getPlayers();
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            String color = entry.getValue().getColor();
            if (color.equals("red"))
                availableColors.set(0, false);
            else if (color.equals("blue"))
                availableColors.set(1, false);
            else if (color.equals("yellow"))
                availableColors.set(2, false);
            else if (color.equals("green"))
                availableColors.set(3, false);
            else if (color.equals("black"))
                availableColors.set(4, false);
        }
        return availableColors;
    }
}
