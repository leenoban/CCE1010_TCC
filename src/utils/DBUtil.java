/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author AngusLipsey
 */
public class DBUtil {
    
    private Connection conn = null;
    private static int conn_count = 0;
    private static int conn_close = 0;
    
    private String getDbPath() {
        String current_path = null;
        try {
            current_path = new File(".").getCanonicalPath(); //System.out.println("current_path: " + current_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return current_path;
    }
    
    public Connection getConnection() {
        try {
            
            // check if db exist
            String db_path = getDbPath() + "/tcc.db"; //System.out.println("db_path: " + db_path);
            File db = new File(db_path);
            boolean isDbExist = db.exists(); //System.out.println("isDbExist: " + isDbExist);
            
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:tcc.db");
            //System.out.println("Opened database successfully");
            conn.setAutoCommit(false);
            
            if(!isDbExist) initDB(conn);
            
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        conn_count++;
        return conn;
    }
    
    private void initDB(Connection m_conn) {
        try {
            Statement stmt = m_conn.createStatement();
            
            // create table "RECIPE"
            stmt.executeUpdate(DBConfig.SQL_CREATE_TBL_RECIPE);
            
            // create table "COUNTRY"
            stmt.executeUpdate(DBConfig.SQL_CREATE_TBL_COUNTRY);
            
            // create table "METHOD"
            stmt.executeUpdate(DBConfig.SQL_CREATE_TBL_METHOD);
            
            // create table "INTERVAL"
            stmt.executeUpdate(DBConfig.SQL_CREATE_TBL_INTERVAL);
            
            // init country data
            stmt.executeUpdate(DBConfig.SQL_INSERT_COUNTRY);
            
            // init method data
            stmt.executeUpdate(DBConfig.SQL_INSERT_METHOD);
            
            // init interval data
            stmt.executeUpdate(DBConfig.SQL_INSERT_INTERVAL);
            
            // init sys recipe data
            stmt.executeUpdate(DBConfig.SQL_INSERT_RECIPE);
            
            stmt.close();
            m_conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection(Statement m_stmt, ResultSet m_rs, Connection m_conn) {
        try {
        } finally {
            try {
                if (m_stmt != null) m_stmt.close(); m_stmt  = null;
                if (m_rs   != null) m_rs.close();   m_rs    = null;
                if (m_conn != null) m_conn.close(); m_conn  = null;
                conn_close++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static int getConnectionCount() {
        return conn_count;
    }
    
    public static int getConnectionClosed() {
        return conn_close;
    }
    
    // get next recipe id from DB (max+1)
    public static int getNextId(String m_table_name, String m_table_id) {
        int id = -1;
        String sql = "SELECT MAX(" + m_table_id + ") AS " + DBConfig.DB_FIELD_NEXT_ID + " FROM " + m_table_name + ";";
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs!=null) {
                id = (int)rs.getInt(DBConfig.DB_FIELD_NEXT_ID);
                id++;
            }
            db.closeConnection(stmt, rs, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public static void main(String args[]) {
        
        //DBUtil db = new DBUtil(); //System.out.println("Conn: " + db.getConnection());
        System.out.println("Next Recipe Id: " + getNextId(DBConfig.DB_TBL_RECIPE, DBConfig.DB_FIELD_RECIPE_ID));
        System.out.println("Next Country Id: " + getNextId(DBConfig.DB_TBL_COUNTRY, DBConfig.DB_FIELD_COUNTRY_ID));
        System.out.println("Next Method Id: " + getNextId(DBConfig.DB_TBL_METHOD, DBConfig.DB_FIELD_METHOD_ID));
        System.out.println("Next Interval Id: " + getNextId(DBConfig.DB_TBL_INTERVAL, DBConfig.DB_FIELD_INTERVAL_ID));
    }
    
}
