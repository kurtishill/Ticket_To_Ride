package Plugin;

import dto.GameDTO;

import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IGameDao {
    List<GameDTO> read();
    void create(GameDTO game);//todo should we return a database access result or something to see if theres an error?
    void update(GameDTO game);
    void delete(int Id);
}
