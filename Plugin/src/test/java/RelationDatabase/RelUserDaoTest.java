package RelationDatabase;

import java.util.List;

import dto.PlayerDTO;

import static org.junit.Assert.*;

/**
 * Created by claytonkingsbury on 4/13/18.
 */
public class RelUserDaoTest {
    @org.junit.Test
    public void read() throws Exception {
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        List<PlayerDTO> list = userDao.read();
        assertEquals(list.size(), 1);
        userDao.delete(1);
    }

    @org.junit.Test
    public void create() throws Exception {
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        userDao.delete(1);
    }

    @org.junit.Test
    public void update() throws Exception { // theres an error here
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        playerDTO.setGameId(2);
        userDao.update(playerDTO);
        List<PlayerDTO> list = userDao.read();
        assertEquals(list.get(0).getGameId(), 2);
        userDao.delete(2);
    }

    @org.junit.Test
    public void delete() throws Exception {
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        userDao.delete(1);
        List<PlayerDTO> list = userDao.read();
        assertEquals(list.size(), 0);
    }

    @org.junit.Test
    public void clear() throws Exception {
    }

}