package RelationDatabase;

import Plugin.ICommandDao;
import Plugin.IGameDao;
import Plugin.IPlugin;
import Plugin.IUserDao;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelPlugin implements IPlugin {
    IGameDao gameDao;
    IUserDao userDao;
    ICommandDao commandDao;
    public RelPlugin(){
        gameDao = new RelGameDao();
        userDao = new RelUserDao();
        commandDao = new RelCommandDao();
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
        System.out.println("hey");
    }
}
