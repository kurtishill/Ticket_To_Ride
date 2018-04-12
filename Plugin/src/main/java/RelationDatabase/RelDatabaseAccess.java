package RelationDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Plugin.IDatabaseAccess;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelDatabaseAccess implements IDatabaseAccess {
    private Connection conn;
    @Override
    public Object access(Object object) { // create function?
        try {
            openConnection();
            PreparedStatement pstmt = null;
            List<Object> info = (ArrayList) object;
            String sql = (String) info.get(0);
            try {
                pstmt = conn.prepareStatement(sql);
                List<String> storeInfo = (ArrayList) info.get(1);
                for (int i = 0; i < storeInfo.size(); i++){
                    pstmt.setString(i+1, storeInfo.get(i));
                }
                if (pstmt.executeUpdate() != 1) {
                    throw new DatabaseException("Error in create", new SQLException());
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        catch(DatabaseException e){
            e.printStackTrace();
        }
        try {
            closeConnection(true);
        }
        catch(DatabaseException e){
            e.printStackTrace();
        }
        return null;
    }

    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public RelDatabaseAccess()throws DatabaseException{
        openConnection();
    }
    public void openConnection() throws DatabaseException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:fmsDatabase.db"; // todo what is the name of our database

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new DatabaseException("openConnection failed", e);
        }
    }

    public void closeConnection(boolean commit) throws DatabaseException {
        try {
            if (commit) {
                conn.commit();
            }
            else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        }
        catch (SQLException e) {
            throw new DatabaseException("closeConnection failed", e);
        }
    }






    private class DatabaseException extends Throwable {
        public DatabaseException(String s, SQLException e) {
        }
    }
}
