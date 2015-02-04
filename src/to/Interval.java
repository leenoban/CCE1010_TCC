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
    private String unit_tw;
    private String unit_cn;
    
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
                    interval.setUnit_tw(rs.getString(DBConfig.DB_FIELD_UNIT_TW));
                    interval.setUnit_cn(rs.getString(DBConfig.DB_FIELD_UNIT_CN));
                    list.add(interval);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
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
                                                  + DBConfig.DB_FIELD_UNIT_TW + ", " 
                                                  + DBConfig.DB_FIELD_UNIT_CN + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_INTERVAL, DBConfig.DB_FIELD_INTERVAL_ID) + ", " 
                    + "'" + m_interval.getInterval() + "', " 
                    + "'" + m_interval.getUnit_tw()+ "', " 
                    + "'" + m_interval.getUnit_cn()+ "'" + 
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
    private static void updateInterval(Interval m_interval) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_INTERVAL + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_INTERVAL + "=" + m_interval.getInterval() + ", " ;
            sql +=      DBConfig.DB_FIELD_UNIT_TW + "='" + m_interval.getUnit_tw() + "', " ;
            sql +=      DBConfig.DB_FIELD_UNIT_CN + "='" + m_interval.getUnit_cn() + "' " ;
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
     * @return the unit_tw
     */
    public String getUnit_tw() {
        return unit_tw;
    }

    /**
     * @param unit_tw the unit_tw to set
     */
    public void setUnit_tw(String unit_tw) {
        this.unit_tw = unit_tw;
    }

    /**
     * @return the unit_cn
     */
    public String getUnit_cn() {
        return unit_cn;
    }

    /**
     * @param unit_cn the unit_cn to set
     */
    public void setUnit_cn(String unit_cn) {
        this.unit_cn = unit_cn;
    }
    
    public static void main(String args[]) {
        // test: getIntevalList()
        //System.out.println("getIntevalList().size(): " + getIntevalList().size());
        //showIntervalContent((Interval)getIntervalList().get(2));
        
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
        System.out.println(i.getUnit_tw());
        System.out.println(i.getUnit_cn());
    }
    
    // for testing
    private static Interval getTestingInterval() {
        Interval interval = new Interval();
        interval.setInterval_id(7);
        interval.setInterval(60);
        interval.setUnit_tw("yyy");
        interval.setUnit_cn("yyy");
        return interval;
    }
}
