package RelationDatabase;

import java.util.ArrayList;
import java.util.List;

import Plugin.IUserDao;
import dto.PlayerDTO;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelUserDao implements IUserDao {
    private RelDatabaseAccess database;
    public RelUserDao(){
         database = new RelDatabaseAccess();
    }
    @Override
    public List<PlayerDTO> read() {
        String sql = "SELECT username, password, userId, currentGame FROM User";
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        Object ret = database.read(createTable);
        // todo deserialize and return
        return (List<PlayerDTO>) ret;
    }

    @Override
    public void create(PlayerDTO user) {
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
    public void update(PlayerDTO user) {
        String sql = "UPDATE User SET currentGame = ? WHERE username = ?, password = ?, userId = ?";
        List<String> info = new ArrayList<>();
        info.add((Integer.toString(user.getGameId())));
        info.add(user.getUsername());
        info.add(user.getPassword());
        info.add(user.getId());
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        database.update(createTable);
    }

    @Override
    public void delete(int Id) {
        String sql = "DELETE FROM User WHERE currentGame = ?";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        deleteRow.add(Integer.toString(Id));
        database.delete(deleteRow);
    }
}
