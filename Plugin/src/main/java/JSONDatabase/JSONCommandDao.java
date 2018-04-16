package JSONDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Plugin.ICommandDao;
import dto.CommandDTO;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONCommandDao implements ICommandDao {
    @Override
    public List<CommandDTO> read() {
        List<CommandDTO> commandList = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("commands.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            commandList = (List<CommandDTO>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return commandList;
    }

    @Override
    public void create(CommandDTO command) {
        List<CommandDTO> commandList = read();
        if(commandList==null)
            commandList = new ArrayList<>();

        commandList.add(command);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("commands.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(commandList);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CommandDTO command) {

    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public void clear() {
        File file = new File("commands.ser");
        file.delete();
    }

    public static void main(String[]args){
        byte[] commandString = {};
        CommandDTO command = new CommandDTO(1, commandString, 5);
        JSONCommandDao dao = new JSONCommandDao();
        dao.create(command);
        try {
            dao.update(new CommandDTO(1, commandString, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.clear();
    }
}
