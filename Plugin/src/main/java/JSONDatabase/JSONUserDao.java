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

import Plugin.IUserDao;
import dto.PlayerDTO;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONUserDao implements IUserDao {

    @Override
    public List<PlayerDTO> read() {
        List<PlayerDTO> playerList = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("users.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            playerList = (List<PlayerDTO>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    @Override
    public void create(PlayerDTO user) {
        List<PlayerDTO> playerList = read();
        if(playerList==null)
            playerList = new ArrayList();

        playerList.add(user);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("users.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(playerList); //change to list of users
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlayerDTO user) throws Exception {
        List<PlayerDTO> playerList = read();
        if(playerList==null)
            throw new Exception();

        for(int i=0; i<playerList.size(); i++){
            if( playerList.get(i).getId().equals(user.getId()))
                playerList.set(i,user);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("users.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(playerList); //change to list of users
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public void clear() {

        File file = new File("users.ser");
        file.delete();
    }

    public static void main(String[]args){
        PlayerDTO player = new PlayerDTO("IDsdf", "Victory", "rodham", 5);
        JSONUserDao dao = new JSONUserDao();
        dao.create(player);
        try {
            dao.update(new PlayerDTO("ID", "Success", "rodham",5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.clear();
    }
}


