package JSONDatabase;

import java.util.List;

import Plugin.ICommandDao;
import dto.CommandDTO;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONCommandDao implements ICommandDao {
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
}
