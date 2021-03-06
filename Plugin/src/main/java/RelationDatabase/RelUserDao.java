package RelationDatabase;

import java.sql.SQLException;
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
        createTable.add(4);
        Object ret = database.read(createTable);
        List<List<Object>> des = (List<List<Object>>)ret;
        List<PlayerDTO> list = new ArrayList<>();
        try {
            for (int i = 0; i < des.size(); i++) {
                list.add(new PlayerDTO((String) des.get(i).get(2), (String) des.get(i).get(0), (String) des.get(i).get(1), Integer.parseInt((String) des.get(i).get(3))));
            }
        }
        catch (Exception e){}
        // todo deserialize and return
        return list;
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
        String sql = "UPDATE User SET currentGame = ? WHERE userId = ? and username = ? and password = ?"; //WHERE username = ?, password = ?, userId = ?"
        List<String> info = new ArrayList<>();
        info.add((Integer.toString(user.getGameId())));
        info.add(user.getId());
        info.add(user.getUsername());
        info.add(user.getPassword());
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

    @Override
    public void clear() {
        String sql = "DELETE FROM User";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        database.delete(deleteRow);
    }
}
