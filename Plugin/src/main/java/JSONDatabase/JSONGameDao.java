package JSONDatabase;

import java.util.List;

import Plugin.IGameDao;
import dto.GameDTO;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONGameDao implements IGameDao {
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
}
