package RelationDatabase;

import java.sql.Array;
import java.sql.Blob;
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
import sun.security.util.Cache;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class RelDatabaseAccess implements IDatabaseAccess {

    private Connection conn;
    @Override
    public Object create(Object object) { // create function?
        try {
            openConnection();
            PreparedStatement pstmt = null;
            List<Object> info = (ArrayList) object;
            String sql = (String) info.get(0);
            try {
                pstmt = conn.prepareStatement(sql);
                List<Object> storeInfo = (ArrayList) info.get(1);
                //List<Class> classes = (ArrayList) info.get(2);
                for (int i = 0; i < storeInfo.size(); i++){
                    try {
                        pstmt.setString(i + 1, (String)storeInfo.get(i)); // might need to check type
                    }
                    catch (Exception e){
                        pstmt.setBytes(i + 1, (byte[])storeInfo.get(i));
                    }
                }
                if (pstmt.executeUpdate() != 1) {
                    throw new DatabaseException("Error in create", new SQLException());
                }
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                return (Object) true;
            }
            catch (SQLException e){
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                e.printStackTrace();
            }
            finally{
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                }
                catch (SQLException e){
                    return null;
                }
            }
        }
        catch(DatabaseException e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Object read(Object object) {
        ResultSet rs = null;
        try {
            openConnection();
            PreparedStatement pstmt = null;
            List<Object> info = (ArrayList) object;
            String sql = (String) info.get(0);
            try {
                pstmt = conn.prepareStatement(sql);
//                List<String> storeInfo = (ArrayList) info.get(1);
//                for (int i = 0; i < storeInfo.size(); i++){
//                    pstmt.setString(i+1, storeInfo.get(i));
//                }
                rs = pstmt.executeQuery();
               // int size = (Integer) info.get(2);
                List<Object> found = new ArrayList<>();
//                for (int i = 0; i < size; i++){
//
//                }

                int size = (Integer)info.get(1);
                while(rs.next()){
                    List<Object> retInfo = new ArrayList<>();
                    for (int i = 0; i < size; i++){
                        if(size == 3 && i == 1){
                            retInfo.add(rs.getBytes(i+1));
                        }
                        else {
                            retInfo.add(rs.getString(i + 1));
                        }

                    }
                    found.add(retInfo); // dont know if this will work //todo fix this
                }
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                return found;
            }
            catch (SQLException e){
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                e.printStackTrace();
            }
            finally{
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                }
                catch (SQLException e){
                //    return null;
                }
            }

        }
        catch(DatabaseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object delete(Object object) {
        try {
            openConnection();
            PreparedStatement pstmt = null;
            List<Object> info = (ArrayList) object;
            String sql = (String) info.get(0);
            try {
                pstmt = conn.prepareStatement(sql);
                try{
                    String id = (String) info.get(1);
                    pstmt.setString(1, id); // might need to check type
               }
                catch(Exception e){
                    //do nothing
                }

                pstmt.executeUpdate();
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                return (Object) true;
            }
            catch (SQLException e){
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                e.printStackTrace();
            }
            finally{
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                }
                catch (SQLException e){
                    return null;
                }
            }
        }
        catch(DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object update(Object object) {
        try {
            openConnection();
            PreparedStatement pstmt = null;
            List<Object> info = (ArrayList) object;
            String sql = (String) info.get(0);
            try {
                pstmt = conn.prepareStatement(sql);
                List<String> storeInfo = (ArrayList) info.get(1);
                for (int i = 0; i < storeInfo.size(); i++){
                    pstmt.setString(i+1, storeInfo.get(i)); // might need to check type
                }
                if (pstmt.executeUpdate() != 1) {
                    throw new DatabaseException("Error in create", new SQLException());
                }
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                return (Object) true;
            }
            catch (SQLException e){
                try {
                    closeConnection(true);
                }
                catch(DatabaseException b){
                    b.printStackTrace();
                }
                e.printStackTrace();
            }
            finally{
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                }
                catch (SQLException e){
                    return null;
                }
            }
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


    public void openConnection() throws DatabaseException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:ticket_to_ride.db"; //todo have to put the absolute path here

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
