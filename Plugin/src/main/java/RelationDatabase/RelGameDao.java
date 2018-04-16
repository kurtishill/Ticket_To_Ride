package RelationDatabase;

import java.util.ArrayList;
import java.util.List;

import Plugin.IGameDao;
import dto.GameDTO;
import dto.PlayerDTO;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelGameDao implements IGameDao {
    private RelDatabaseAccess databaseAccess;
    public RelGameDao(){
        databaseAccess = new RelDatabaseAccess();
    }

    @Override
    public List<GameDTO> read() {
        String sql = "SELECT gameId, gameInfo FROM Game";
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(2);
        Object ret = databaseAccess.read(createTable);
        List<List<Object>> des = (List<List<Object>>)ret;
        List<GameDTO> list = new ArrayList<>();
        for (int i = 0; i < des.size(); i++){
            list.add(new GameDTO(Integer.parseInt((String) des.get(i).get(0)), (String)des.get(i).get(1)));
        }
        // todo deserialize and return
        return list;
    }

    @Override
    public void create(GameDTO game) {
        String sql = "INSERT INTO Game(gameId, gameInfo) VALUES(?,?)";
        List<String> info = new ArrayList<>();
        info.add(Integer.toString(game.getId()));
        info.add(game.getGame());
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        databaseAccess.create(createTable);
    }

    @Override
    public void update(GameDTO game) {
        String sql = "UPDATE Game SET gameInfo = ? WHERE gameId = ?";
        List<String> info = new ArrayList<>();
        info.add(game.getGame());
        info.add((Integer.toString(game.getId())));
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        databaseAccess.update(createTable);
    }

    @Override
    public void delete(int Id) {
        String sql = "DELETE FROM Game WHERE gameId = ?";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        deleteRow.add(Integer.toString(Id));
        databaseAccess.delete(deleteRow);
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM Game";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        databaseAccess.delete(deleteRow);
    }
}
