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

import Plugin.IGameDao;
import dto.GameDTO;
import dto.PlayerDTO;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONGameDao implements IGameDao {
    @Override
    public List<GameDTO> read() {
        List<GameDTO> gameList = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("game.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            gameList = (List<GameDTO>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameList;
    }

    @Override
    public void create(GameDTO game) {
        List<GameDTO> gameList = read();
        if(gameList==null)
            gameList = new ArrayList<>();

        gameList.add(game);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("game.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(gameList);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameDTO game) throws Exception {
        List<GameDTO> gameList = read();
        if(gameList == null)
            throw new Exception();

        for(int i = 0; i < gameList.size(); i++){
            if(gameList.get(i).getId() == game.getId())
                gameList.set(i,game);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("game.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(gameList);
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
        File file = new File("game.ser");
        file.delete();
    }

    public static void main(String[] args) {

        GameDTO dto = new GameDTO(2, "fkdl;a");
        JSONGameDao dao = new JSONGameDao();
        dao.create(dto);

        try {
            dao.update(new GameDTO(2, "fdafdafdafda"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        dao.clear();
    }
}
