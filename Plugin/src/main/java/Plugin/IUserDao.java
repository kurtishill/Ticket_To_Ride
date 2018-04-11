package Plugin;

import dto.PlayerDTO;

import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IUserDao {
    List<PlayerDTO> read();
    void create(PlayerDTO user);//todo should we return a database access result or something to see if theres an error?
    void update(PlayerDTO user);
    void delete(int Id);
}
