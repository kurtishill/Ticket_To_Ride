package RelationDatabase;

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

    }

    @Override
    public void update(GameDTO game) {

    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public void clear() {

    }
}
