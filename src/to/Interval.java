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
public class Interval {
    
    private int interval_id;
    private int interval;
    private String unit;
    
    // get interval list from DB
    public static ArrayList getIntervalList() {
        ArrayList list = new ArrayList();
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM " + DBConfig.DB_TBL_INTERVAL + " ORDER BY " + DBConfig.DB_FIELD_INTERVAL_ID + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    Interval interval = new Interval();
                    interval.setInterval_id(rs.getInt(DBConfig.DB_FIELD_INTERVAL_ID));
                    interval.setInterval(rs.getInt(DBConfig.DB_FIELD_INTERVAL));
                    interval.setUnit(rs.getString(DBConfig.DB_FIELD_UNIT));
                    list.add(interval);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // retrieve interval from DB by interval id
    private static Interval getInterval(int m_interval_id) {
        Interval interval = new Interval();
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "";
            sql += "SELECT * FROM " + DBConfig.DB_TBL_INTERVAL + " WHERE " + DBConfig.DB_FIELD_INTERVAL_ID + "=" + m_interval_id + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                rs.next();
                interval = buildInterval(rs);
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return interval;
    }
    
    private static Interval buildInterval(ResultSet rs) {
        Interval interval = new Interval();
        
        try {
            interval.setInterval_id(rs.getInt(DBConfig.DB_FIELD_INTERVAL_ID));
            interval.setInterval(rs.getInt(DBConfig.DB_FIELD_INTERVAL));
            interval.setUnit(rs.getString(DBConfig.DB_FIELD_UNIT));
        } catch(SQLException e) {
            e.printStackTrace();;
        }
        return interval;
    }
    
    // insert interval to DB
    public static void insertInterval(Interval m_interval) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "INSERT INTO " + DBConfig.DB_TBL_INTERVAL + " ("
                                                  + DBConfig.DB_FIELD_INTERVAL_ID + ", " 
                                                  + DBConfig.DB_FIELD_INTERVAL + ", "
                                                  + DBConfig.DB_FIELD_UNIT + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_INTERVAL, DBConfig.DB_FIELD_INTERVAL_ID) + ", " 
                    + "'" + m_interval.getInterval() + "', "
                    + "'" + m_interval.getUnit()+ "'" + 
            "); ";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update interval
    public static void updateInterval(Interval m_interval) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_INTERVAL + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_INTERVAL + "=" + m_interval.getInterval() + ", " ;
            sql +=      DBConfig.DB_FIELD_UNIT + "='" + m_interval.getUnit() + "' " ;
            sql += "WHERE " + DBConfig.DB_FIELD_INTERVAL_ID + "=" + m_interval.getInterval_id() + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // delete interval from DB
    public static void deleteInterval(int m_interval_id) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "DELETE FROM " + DBConfig.DB_TBL_INTERVAL + " ";
            sql += "WHERE " + DBConfig.DB_FIELD_INTERVAL_ID + "=" + m_interval_id + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the interval_id
     */
    public int getInterval_id() {
        return interval_id;
    }

    /**
     * @param interval_id the interval_id to set
     */
    public void setInterval_id(int interval_id) {
        this.interval_id = interval_id;
    }

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public static void main(String args[]) {
        // test: getIntevalList()
        //System.out.println("getIntevalList().size(): " + getIntervalList().size());
        //showIntervalContent((Interval)getIntervalList().get(2));
        
        // test: getInterval(int m_interval_id)
        //showIntervalContent(getInterval(6));
        
        // test: insertInterval(Interval m_interval)
        //insertInterval(getTestingInterval());
        
        // test: updateInterval(Interval m_interval)
        //updateInterval(getTestingInterval());
        
        // test: deleteInterval(int m_interval)
        //deleteInterval(7);
    }
    
    // for checking
    private static void showIntervalContent(Interval i) {
        System.out.println(i.getInterval_id());
        System.out.println(i.getInterval());
        System.out.println(i.getUnit());
    }
    
    // for testing
    private static Interval getTestingInterval() {
        Interval interval = new Interval();
        interval.setInterval_id(7);
        interval.setInterval(60);
        interval.setUnit("bbb");
        return interval;
    }
}
