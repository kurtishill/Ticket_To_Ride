package com.example.server.Plugin;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IPlugin {
     IPlugin instance();
     IGameDao getGameDao();
     IUserDao getUserDao();
     ICommandDao getCommandDao();
}
