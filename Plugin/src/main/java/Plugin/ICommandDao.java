package Plugin;

import dto.CommandDTO;


import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface ICommandDao {
    List<CommandDTO> read();
    void create(CommandDTO command);//todo should we return a database access result or something to see if theres an error?
    void update(CommandDTO command);
    void delete(int Id);
    void clear();
}
