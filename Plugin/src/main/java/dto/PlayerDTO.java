package dto;

import java.io.Serializable;

/**
 * Created by kurtishill on 4/9/18.
 */

public class PlayerDTO implements Serializable {

    private String id;

    private String username;

    private String password;

    private int gameId;

    public PlayerDTO() {}

    public PlayerDTO(String id, String username, String password, int gameId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gameId = gameId;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getGameId() {
        return gameId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
