package RelationDatabase;

import java.util.List;

import Plugin.ICommandDao;
import dto.CommandDTO;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelCommandDao implements ICommandDao {
    @Override
    public List<CommandDTO> read() {
        return null;
    }

    @Override
    public void create(CommandDTO command) {

    }

    @Override
    public void update(CommandDTO command) {

    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public void clear() {

    }
}
