package dto;

/**
 * Created by kurtishill on 4/9/18.
 */

public class CommandDTO {

    private int id;

    private String command;

    private int gameId;

    public CommandDTO() {}

    public CommandDTO(int id, String command, int gameId) {
        this.id = id;
        this.command = command;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public int getGameId() {
        return gameId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
