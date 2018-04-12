package RelationDatabase;

import java.util.List;

import Plugin.IUserDao;
import dto.PlayerDTO;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelUserDao implements IUserDao {
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
