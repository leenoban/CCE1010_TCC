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
public class Region {
    
    private int region_id;
    private String region_name_tw;
    private String region_name_cn;
    
    // get region list from DB
    public static ArrayList getRegionList() {
        ArrayList list = new ArrayList();
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM " + DBConfig.DB_TBL_REGION + " ORDER BY " + DBConfig.DB_FIELD_REGION_ID + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    Region region = new Region();
                    region.setRegion_id(rs.getInt(DBConfig.DB_FIELD_REGION_ID));
                    region.setRegion_name_tw(rs.getString(DBConfig.DB_FIELD_REGION_NAME_TW));
                    region.setRegion_name_cn(rs.getString(DBConfig.DB_FIELD_REGION_NAME_CN));
                    list.add(region);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // insert region to DB
    public static void insertRegion(Region m_region) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "INSERT INTO " + DBConfig.DB_TBL_REGION + " ("
                                                  + DBConfig.DB_FIELD_REGION_ID + ", " 
                                                  + DBConfig.DB_FIELD_REGION_NAME_TW + ", " 
                                                  + DBConfig.DB_FIELD_REGION_NAME_CN + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_REGION, DBConfig.DB_FIELD_REGION_ID) + ", " 
                    + "'" + m_region.getRegion_name_tw() + "', " 
                    + "'" + m_region.getRegion_name_cn() + "'" + 
            "); ";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update region
    private static void updateRegion(Region m_region) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_REGION + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_REGION_NAME_TW + "='" + m_region.getRegion_name_tw() + "', " ;
            sql +=      DBConfig.DB_FIELD_REGION_NAME_CN + "='" + m_region.getRegion_name_cn() + "' " ;
            sql += "WHERE " + DBConfig.DB_FIELD_REGION_ID + "=" + m_region.getRegion_id() + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // delete region from DB
    public static void deleteRegion(int m_region_id) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "DELETE FROM " + DBConfig.DB_TBL_REGION + " ";
            sql += "WHERE " + DBConfig.DB_FIELD_REGION_ID + "=" + m_region_id + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the region_id
     */
    public int getRegion_id() {
        return region_id;
    }

    /**
     * @param region_id the region_id to set
     */
    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    /**
     * @return the region_name_tw
     */
    public String getRegion_name_tw() {
        return region_name_tw;
    }

    /**
     * @param region_name_tw the region_name_tw to set
     */
    public void setRegion_name_tw(String region_name_tw) {
        this.region_name_tw = region_name_tw;
    }

    /**
     * @return the region_name_cn
     */
    public String getRegion_name_cn() {
        return region_name_cn;
    }

    /**
     * @param region_name_cn the region_name_cn to set
     */
    public void setRegion_name_cn(String region_name_cn) {
        this.region_name_cn = region_name_cn;
    }
    
    public static void main(String args[]) {
        // test: getRegionList()
        //System.out.println("getRegionList().size(): " + getRegionList().size());
        //showRegionContent((Region)getRegionList().get(3));
        
        // test: insertRegion(Region m_region)
        //insertRegion(getTestingRegion());
        
        // test: updateRegion(Region m_region)
        //updateRegion(getTestingRegion());
        
        // test: deleteRegion(int m_region_id)
        //deleteRegion(5);
    }
    
    // for checking
    private static void showRegionContent(Region r) {
        System.out.println(r.getRegion_id());
        System.out.println(r.getRegion_name_tw());
        System.out.println(r.getRegion_name_cn());
    }
    
    // for testing
    private static Region getTestingRegion() {
        Region region = new Region();
        region.setRegion_id(6);
        region.setRegion_name_tw("7777");
        region.setRegion_name_cn("777");
        return region;
    }
}
