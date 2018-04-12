package JSONDatabase;

import java.util.List;

import Plugin.IUserDao;
import dto.PlayerDTO;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONUserDao implements IUserDao {
    @Override
    public List<PlayerDTO> read() {
        return null;
    }

    @Override
    public void create(PlayerDTO user) {

    }

    @Override
    public void update(PlayerDTO user) {

    }

    @Override
    public void delete(int Id) {

    }
}
