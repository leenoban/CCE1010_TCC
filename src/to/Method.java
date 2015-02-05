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
class Method {
    
    private int method_id;
    private String method_name_tw;
    private String method_name_cn;
    
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
                    method.setMethod_name_tw(rs.getString(DBConfig.DB_FIELD_METHOD_NAME_TW));
                    method.setMethod_name_cn(rs.getString(DBConfig.DB_FIELD_METHOD_NAME_TW));
                    list.add(method);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
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
                                                  + DBConfig.DB_FIELD_METHOD_NAME_TW + ", " 
                                                  + DBConfig.DB_FIELD_METHOD_NAME_CN + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_METHOD, DBConfig.DB_FIELD_METHOD_ID) + ", " 
                    + "'" + m_method.getMethod_name_tw() + "', " 
                    + "'" + m_method.getMethod_name_cn() + "'" + 
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
    private static void updateMethod(Method m_method) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_METHOD + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_METHOD_NAME_TW + "='" + m_method.getMethod_name_tw() + "', " ;
            sql +=      DBConfig.DB_FIELD_METHOD_NAME_CN + "='" + m_method.getMethod_name_cn() + "' " ;
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
     * @return the method_name_tw
     */
    public String getMethod_name_tw() {
        return method_name_tw;
    }

    /**
     * @param method_name_tw the method_name_tw to set
     */
    public void setMethod_name_tw(String method_name_tw) {
        this.method_name_tw = method_name_tw;
    }

    /**
     * @return the method_name_cn
     */
    public String getMethod_name_cn() {
        return method_name_cn;
    }

    /**
     * @param method_name_cn the method_name_cn to set
     */
    public void setMethod_name_cn(String method_name_cn) {
        this.method_name_cn = method_name_cn;
    }
    
    public static void main(String args[]) {
        // test: getMethodList()
        //System.out.println("getMethodList().size(): " + getMethodList().size());
        //showMethodContent((Method)getMethodList().get(3));
        
        // test: insertMethod(Method m_method)
        //insertMethod(getTestingMethod());
        
        // test: updateMethod(Region m_method)
        //updateMethod(getTestingMethod());
        
        // test: deleteMethod(int m_method_id)
        //deleteMethod(10);
    }
    
    // for checking
    private static void showMethodContent(Method m) {
        System.out.println(m.getMethod_id());
        System.out.println(m.getMethod_name_tw());
        System.out.println(m.getMethod_name_tw());
    }
    
    // for testing
    private static Method getTestingMethod() {
        Method method = new Method();
        method.setMethod_id(3);
        method.setMethod_name_tw("ROY");
        method.setMethod_name_cn("JOE");
        return method;
    }
    
}
