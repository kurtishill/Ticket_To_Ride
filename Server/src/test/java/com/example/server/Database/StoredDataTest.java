package com.example.server.Database;

import com.example.server.Results.GenericCommand;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.util.List;

import RelationDatabase.RelCommandDao;
import dto.CommandDTO;

import static org.junit.Assert.*;

public class StoredDataTest {

    @Test
    public void store() {


    }

    @Test
    public void read() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        try {
            commandByteArray = SerializationUtils.serialize(command);
        }
        catch (Exception ex) {
            System.out.println("Error while serializing command");
            ex.printStackTrace();
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        List<CommandDTO> list = commandDao.read();
        assertEquals(list.size(), 1);
        assertEquals(commandByteArray, list.get(0).getCommand());
        commandDao.delete(1);
    }

    @Test
    public void create() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        try {
            commandByteArray = SerializationUtils.serialize(command);
        }
        catch (Exception ex) {
            System.out.println("Error while serializing command");
            ex.printStackTrace();
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        commandDao.delete(1);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        try {
            commandByteArray = SerializationUtils.serialize(command);
        }
        catch (Exception ex) {
            System.out.println("Error while serializing command");
            ex.printStackTrace();
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        commandDao.delete(1);
        List<CommandDTO> list = commandDao.read();
        assertEquals(list.size(), 0);
    }

    @Test
    public void clear() {
        RelCommandDao commandDao = new RelCommandDao();
        GenericCommand command = new GenericCommand("test", "test1", new Class[]{String.class}, new Object[]{"test"} );
        byte[] commandByteArray = {};
        try {
            commandByteArray = SerializationUtils.serialize(command);
        }
        catch (Exception ex) {
            System.out.println("Error while serializing command");
            ex.printStackTrace();
        }
        CommandDTO commandDTO = new CommandDTO(1, commandByteArray, 1);
        commandDao.create(commandDTO);
        commandDao.clear();
        List<CommandDTO> list = commandDao.read();
        assertEquals(list.size(), 0);
    }
}