package JSONDatabase;

import Plugin.ICommandDao;
import Plugin.IGameDao;
import Plugin.IPlugin;
import Plugin.IUserDao;
import RelationDatabase.RelCommandDao;
import RelationDatabase.RelGameDao;
import RelationDatabase.RelUserDao;

/**
 * Created by frytime on 4/12/18.
 */

public class JSONPlugin implements IPlugin {
    IGameDao gameDao;
    IUserDao userDao;
    ICommandDao commandDao;
    public JSONPlugin(){
        gameDao = new JSONGameDao();
        userDao = new JSONUserDao();
        commandDao = new JSONCommandDao();
    }

    @Override
    public IPlugin instance() {
        return this;
    }

    @Override
    public IGameDao getGameDao() {
        return gameDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public ICommandDao getCommandDao() {
        return commandDao;
    }

    @Override
    public void test() {
        System.out.println("JSON");
    }
}
