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
public class Country {
    
    private int country_id;
    private String country_name;
    
    // get country list from DB
    public static ArrayList getCountryList() {
        ArrayList list = new ArrayList();
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM " + DBConfig.DB_TBL_COUNTRY + " ORDER BY " + DBConfig.DB_FIELD_COUNTRY_ID + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    Country country = new Country();
                    country.setCountry_id(rs.getInt(DBConfig.DB_FIELD_COUNTRY_ID));
                    country.setCountry_name(rs.getString(DBConfig.DB_FIELD_COUNTRY_NAME));
                    list.add(country);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // retrieve country from DB by country id
    private static Country getCountry(int m_country_id) {
        Country country = new Country();
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "";
            sql += "SELECT * FROM " + DBConfig.DB_TBL_COUNTRY + " WHERE " + DBConfig.DB_FIELD_COUNTRY_ID + "=" + m_country_id + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                rs.next();
                country = buildCountry(rs);
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
    
    private static Country buildCountry(ResultSet rs) {
        Country country = new Country();
        
        try {
            country.setCountry_id(rs.getInt(DBConfig.DB_FIELD_COUNTRY_ID));
            country.setCountry_name(rs.getString(DBConfig.DB_FIELD_COUNTRY_NAME));
        } catch(SQLException e) {
            e.printStackTrace();;
        }
        return country;
    }
    
    // insert country to DB
    public static void insertCountry(Country m_country) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "INSERT INTO " + DBConfig.DB_TBL_COUNTRY + " ("
                                                  + DBConfig.DB_FIELD_COUNTRY_ID + ", "
                                                  + DBConfig.DB_FIELD_COUNTRY_NAME + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_COUNTRY, DBConfig.DB_FIELD_COUNTRY_ID) + ", " 
                    + "'" + m_country.getCountry_name() + "'" + 
            "); ";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update country
    public static void updateCountry(Country m_country) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_COUNTRY + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_COUNTRY_NAME + "='" + m_country.getCountry_name() + "' " ;
            sql += "WHERE " + DBConfig.DB_FIELD_COUNTRY_ID + "=" + m_country.getCountry_id() + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // delete country from DB
    public static void deleteCountry(int m_country_id) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "DELETE FROM " + DBConfig.DB_TBL_COUNTRY + " ";
            sql += "WHERE " + DBConfig.DB_FIELD_COUNTRY_ID + "=" + m_country_id + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the country_id
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * @param country_id the country_id to set
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * @return the country_name
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     * @param country_name the country_name to set
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
    
    public static void main(String args[]) {
        // test: getCountryList()
        //System.out.println("getCountryList().size(): " + getCountryList().size());
        //showCountryContent((Country)getCountryList().get(3));
        
        //test: getCountry(int m_country_id)
        //showCountryContent(getCountry(4));
        
        // test: insertCountry(Country m_country)
        //insertCountry(getTestingCountry());
        
        // test: updateCountry(Country m_country)
        //updateCountry(getTestingCountry());
        
        // test: deleteCountry(int m_country_id)
        //deleteCountry(5);
    }
    
    // for checking
    private static void showCountryContent(Country c) {
        System.out.println(c.getCountry_id());
        System.out.println(c.getCountry_name());
    }
    
    // for testing
    private static Country getTestingCountry() {
        Country country = new Country();
        country.setCountry_id(5);
        country.setCountry_name("888");
        return country;
    }
}
