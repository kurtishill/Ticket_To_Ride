package RelationDatabase;

import dto.PlayerDTO;

import static org.junit.Assert.*;

/**
 * Created by claytonkingsbury on 4/13/18.
 */
public class RelUserDaoTest {
    @org.junit.Test
    public void read() throws Exception {
    }

    @org.junit.Test
    public void create() throws Exception {
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        userDao.delete(1);
    }

    @org.junit.Test
    public void update() throws Exception {
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        playerDTO.setGameId(2);
        userDao.update(playerDTO);
        userDao.delete(2);
    }

    @org.junit.Test
    public void delete() throws Exception {
    }

    @org.junit.Test
    public void clear() throws Exception {
    }

}