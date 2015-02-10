/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBConfig;
import utils.DBUtil;

/**
 *
 * @author AngusLipsey
 */
public class Method {
    
    private int method_id;
    private String method_name;
    
    // get method list from DB
    public static ArrayList getMethodList() {
        ArrayList list = new ArrayList();
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM " + DBConfig.DB_TBL_METHOD + " ORDER BY " + DBConfig.DB_FIELD_METHOD_ID + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    Method method = new Method();
                    method.setMethod_id(rs.getInt(DBConfig.DB_FIELD_METHOD_ID));
                    method.setMethod_name(rs.getString(DBConfig.DB_FIELD_METHOD_NAME));
                    list.add(method);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // retrieve method from DB by method id
    private static Method getMethod(int m_method_id) {
        Method method = new Method();
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "";
            sql += "SELECT * FROM " + DBConfig.DB_TBL_METHOD + " WHERE " + DBConfig.DB_FIELD_METHOD_ID + "=" + m_method_id + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                rs.next();
                method = buildMethod(rs);
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return method;
    }
    
    private static Method buildMethod(ResultSet rs) {
        Method method = new Method();
        
        try {
            method.setMethod_id(rs.getInt(DBConfig.DB_FIELD_METHOD_ID));
            method.setMethod_name(rs.getString(DBConfig.DB_FIELD_METHOD_NAME));
        } catch(SQLException e) {
            e.printStackTrace();;
        }
        return method;
    }
    
    // insert method to DB
    public static void insertMethod(Method m_method) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "INSERT INTO " + DBConfig.DB_TBL_METHOD + " ("
                                                  + DBConfig.DB_FIELD_METHOD_ID + ", "
                                                  + DBConfig.DB_FIELD_METHOD_NAME + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_METHOD, DBConfig.DB_FIELD_METHOD_ID) + ", "
                    + "'" + m_method.getMethod_name() + "'" + 
            "); ";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update method
    public static void updateMethod(Method m_method) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_METHOD + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_METHOD_NAME + "='" + m_method.getMethod_name() + "' " ;
            sql += "WHERE " + DBConfig.DB_FIELD_METHOD_ID + "=" + m_method.getMethod_id() + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // delete method from DB
    public static void deleteMethod(int m_method_id) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "DELETE FROM " + DBConfig.DB_TBL_METHOD + " ";
            sql += "WHERE " + DBConfig.DB_FIELD_METHOD_ID + "=" + m_method_id + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the method_id
     */
    public int getMethod_id() {
        return method_id;
    }

    /**
     * @param method_id the method_id to set
     */
    public void setMethod_id(int method_id) {
        this.method_id = method_id;
    }

    /**
     * @return the method_name
     */
    public String getMethod_name() {
        return method_name;
    }

    /**
     * @param method_name the method_name to set
     */
    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }
    
    public static void main(String args[]) {
        // test: getMethodList()
        //System.out.println("getMethodList().size(): " + getMethodList().size());
        //showMethodContent((Method)getMethodList().get(3));
        
        //test: getMethod(int m_method_id)
        //showMethodContent(getMethod(4));
        
        // test: insertMethod(Method m_method)
        //insertMethod(getTestingMethod());
        
        // test: updateMethod(Region m_method)
        //updateMethod(getTestingMethod());
        
        // test: deleteMethod(int m_method_id)
        //deleteMethod(6);
    }
    
    // for checking
    private static void showMethodContent(Method m) {
        System.out.println(m.getMethod_id());
        System.out.println(m.getMethod_name());
    }
    
    // for testing
    private static Method getTestingMethod() {
        Method method = new Method();
        method.setMethod_id(6);
        method.setMethod_name("xxx");
        return method;
    }
    
}
