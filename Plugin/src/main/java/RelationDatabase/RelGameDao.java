package RelationDatabase;

import java.util.ArrayList;
import java.util.List;

import Plugin.IGameDao;
import dto.GameDTO;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelGameDao implements IGameDao {
    @Override
    public List<GameDTO> read() {
        return null;
    }

    @Override
    public void create(GameDTO game) {
        String sql = "INSERT INTO User(username, password, userId, currentGame) VALUES(?,?,?,?)";
        List<String> info = new ArrayList<>();
        info.add(user.getUsername());
        info.add(user.getPassword());
        info.add(user.getId());
        info.add((Integer.toString(user.getGameId())));
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        database.create(createTable);
    }

    @Override
    public void update(GameDTO game) {

    }

    @Override
    public void delete(int Id) {

    }
}
