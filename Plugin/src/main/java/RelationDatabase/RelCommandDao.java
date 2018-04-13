package RelationDatabase;

import java.util.ArrayList;
import java.util.List;

import Plugin.ICommandDao;
import dto.CommandDTO;
import dto.PlayerDTO;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelCommandDao implements ICommandDao {
    private RelDatabaseAccess databaseAccess;
    public RelCommandDao(){
        databaseAccess = new RelDatabaseAccess();
    }
    @Override
    public List<CommandDTO> read() {
        String sql = "SELECT commandId, command, gameId FROM GameCommands";
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        Object ret = databaseAccess.read(createTable);
        // todo deserialize and return
        return (List<CommandDTO>) ret;
    }

    @Override
    public void create(CommandDTO command) {
        String sql = "INSERT INTO GameCommands(commandId, gameId, command) VALUES(?,?,?)";
        List<String> info = new ArrayList<>();
        info.add(Integer.toString(command.getId()));
        info.add(command.getCommand());
        info.add(Integer.toString(command.getGameId()));
        List<Object> createTable = new ArrayList<>();
        createTable.add(sql);
        createTable.add(info);
        databaseAccess.create(createTable);
    }

    @Override
    public void update(CommandDTO command) {

    }

    @Override
    public void delete(int Id) {
        String sql = "DELETE FROM GameCommands WHERE commandId = ?";
        List<Object> deleteRow = new ArrayList<>();
        deleteRow.add(sql);
        deleteRow.add(Integer.toString(Id));
        databaseAccess.delete(deleteRow);
    }
}
