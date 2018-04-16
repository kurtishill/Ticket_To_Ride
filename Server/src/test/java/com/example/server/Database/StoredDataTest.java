package com.example.server.Database;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;
import com.example.server.Serializer;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

import RelationDatabase.RelCommandDao;
import RelationDatabase.RelGameDao;
import RelationDatabase.RelUserDao;
import dto.CommandDTO;
import dto.GameDTO;
import dto.PlayerDTO;

import static org.junit.Assert.*;

public class StoredDataTest {

    @Test
    public void store() {


    }

    @Test
    public void readCom() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(command);
            out.flush();
            commandByteArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        List<CommandDTO> list = commandDao.read();
        assertEquals(list.size(), 1);
        ByteArrayInputStream bis = new ByteArrayInputStream(list.get(0).getCommand());
        ObjectInput in = null;
        Object obj = null;
        try {
            in = new ObjectInputStream(bis);
            obj = in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        ICommand commandRet = ((ICommand) obj);
        commandDao.delete(1);
    }

    @Test
    public void createCom() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(command);
            out.flush();
            commandByteArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        commandDao.delete(1);
    }

    @Test
    public void updateCom() {
    }

    @Test
    public void deleteCom() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(command);
            out.flush();
            commandByteArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        commandDao.delete(1);
        List<CommandDTO> list = commandDao.read();
        assertEquals(list.size(), 0);
    }

    @Test
    public void clearCom() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(command);
            out.flush();
            commandByteArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        commandDao.clear();
        List<CommandDTO> list = commandDao.read();
        assertEquals(list.size(), 0);
    }

    @Test
    public void readGame() {
    }

    @Test
    public void createGame() {
        RelGameDao gameDao = new RelGameDao();
        TicketToRideGame game = new TicketToRideGame();
        Player player = new Player();
        game.addPlayer(player);
        String gameS = Serializer.encode(game);
        GameDTO dto = new GameDTO(1, gameS);
        gameDao.create(dto);
        gameDao.delete(1);
    }

    @Test
    public void updateGame() {
        RelGameDao gameDao = new RelGameDao();
        TicketToRideGame game = new TicketToRideGame();
        Player player = new Player();
        game.addPlayer(player);
        String gameS = Serializer.encode(game);
        GameDTO dto = new GameDTO(1, gameS);
        gameDao.create(dto);
        Player player2 = new Player();
        game.addPlayer(player2);
        gameS = Serializer.encode(game);
        dto = new GameDTO(1, gameS);
        gameDao.update(dto);
        List<GameDTO> gameDTOS = gameDao.read();
        Object obj = Serializer.decode(gameDTOS.get(0).getGame(), TicketToRideGame.class);
        TicketToRideGame retGame = (TicketToRideGame) obj;
        assertEquals(retGame.getPlayers().size(), 2);
        gameDao.delete(1);
    }

    @Test
    public void deleteGame() {
        RelGameDao gameDao = new RelGameDao();
        TicketToRideGame game = new TicketToRideGame();
        Player player = new Player();
        game.addPlayer(player);
        String gameS = Serializer.encode(game);
        GameDTO dto = new GameDTO(1, gameS);
        gameDao.create(dto);
        gameDao.delete(1);
        List<GameDTO> gameDTOS = gameDao.read();
        assertEquals(0, gameDTOS.size());
    }
    @org.junit.Test
    public void read() throws Exception {
        RelUserDao userDao = new RelUserDao();
        PlayerDTO playerDTO = new PlayerDTO("test", "username", "password", 1);
        userDao.create(playerDTO);
        List<PlayerDTO> list = userDao.read();
        assertEquals(list.size(), 1);
        userDao.delete(1);
    }
    @Test
    public void clearGame() {
    }
}