package dto;

/**
 * Created by kurtishill on 4/9/18.
 */

public class GameDTO {

    private int id;

    private String game;

    public GameDTO() {}

    public GameDTO(int id, String game) {
        this.id = id;
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
