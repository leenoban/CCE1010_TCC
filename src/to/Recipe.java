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
public class Recipe {
    
    private int recipe_id;
    private String recipe_name;
    private int level;
    private String material;
    private String steps;
    private Country country;
    private Method method;
    private Interval interval;
    
    // get all list
    public static ArrayList getRecipeList() {
        return getRecipeList(null);
    }
    
    // get list of recipes by criteria "keywords"
    public static ArrayList getRecipeList(String m_keyword) {
        ArrayList list = new ArrayList();
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "";
            sql += "SELECT ";
            sql += "    a.*, ";
            sql += "    b." + DBConfig.DB_FIELD_COUNTRY_NAME + ", ";
            sql += "    c." + DBConfig.DB_FIELD_METHOD_NAME + ", ";
            sql += "    d." + DBConfig.DB_FIELD_INTERVAL + ", d." + DBConfig.DB_FIELD_UNIT + " ";
            sql += "FROM ";
            sql += "    " + DBConfig.DB_TBL_RECIPE + " a, " + DBConfig.DB_TBL_COUNTRY + " b, " + DBConfig.DB_TBL_METHOD + " c, " + DBConfig.DB_TBL_INTERVAL + " d ";
            sql += "WHERE 1=1 ";
            sql += "    AND b." + DBConfig.DB_FIELD_COUNTRY_ID + " = a." + DBConfig.DB_FIELD_COUNTRY_ID;
            sql += "    AND c." + DBConfig.DB_FIELD_METHOD_ID + " = a." + DBConfig.DB_FIELD_METHOD_ID;
            sql += "    AND d." + DBConfig.DB_FIELD_INTERVAL_ID + " = a." + DBConfig.DB_FIELD_INTERVAL_ID;
            
            if(m_keyword!=null) { // select record by criteria
                sql += " AND a." + DBConfig.DB_FIELD_RECIPE_NAME + " LIKE '%" + m_keyword + "%'";
            }
            sql += ";"; //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    Recipe recipe = buildRecipe(rs);
                    list.add(recipe);
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // retrieve recipe from DB by recipe id
    public static Recipe getRecipe(int m_recipe_id) {
        Recipe recipe = new Recipe();
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "";
            sql += "SELECT ";
            sql += "    a.*, ";
            sql += "    b." + DBConfig.DB_FIELD_COUNTRY_NAME + ", ";
            sql += "    c." + DBConfig.DB_FIELD_METHOD_NAME + ", ";
            sql += "    d." + DBConfig.DB_FIELD_INTERVAL + ", d." + DBConfig.DB_FIELD_UNIT + " ";
            sql += "FROM ";
            sql += "    " + DBConfig.DB_TBL_RECIPE + " a, " + DBConfig.DB_TBL_COUNTRY + " b, " + DBConfig.DB_TBL_METHOD + " c, " + DBConfig.DB_TBL_INTERVAL + " d ";
            sql += "WHERE 1=1 ";
            sql += "    AND b." + DBConfig.DB_FIELD_COUNTRY_ID + " = a." + DBConfig.DB_FIELD_COUNTRY_ID;
            sql += "    AND c." + DBConfig.DB_FIELD_METHOD_ID + " = a." + DBConfig.DB_FIELD_METHOD_ID;
            sql += "    AND d." + DBConfig.DB_FIELD_INTERVAL_ID + " = a." + DBConfig.DB_FIELD_INTERVAL_ID;
            sql += " AND a." + DBConfig.DB_FIELD_RECIPE_ID + " = " + m_recipe_id + ";";
            //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                rs.next();
                recipe = buildRecipe(rs);
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }
    
    private static Recipe buildRecipe(ResultSet rs) {
        Recipe recipe = new Recipe();
        try {
            recipe.setRecipe_id(rs.getInt(DBConfig.DB_FIELD_RECIPE_ID));
            recipe.setRecipe_name(rs.getString(DBConfig.DB_FIELD_RECIPE_NAME));
            recipe.setLevel(rs.getInt(DBConfig.DB_FIELD_RECIPE_LEVEL));
            recipe.setMaterial(rs.getString(DBConfig.DB_FIELD_RECIPE_MATERIAL));
            recipe.setSteps(rs.getString(DBConfig.DB_FIELD_RECIPE_STEPS));

            Country country = new Country();
            country.setCountry_id(rs.getInt(DBConfig.DB_FIELD_COUNTRY_ID));
            country.setCountry_name(rs.getString(DBConfig.DB_FIELD_COUNTRY_NAME));
            recipe.setCountry(country);

            Method method = new Method();
            method.setMethod_id(rs.getInt(DBConfig.DB_FIELD_METHOD_ID));
            method.setMethod_name(rs.getString(DBConfig.DB_FIELD_METHOD_NAME));
            recipe.setMethod(method);

            Interval interval = new Interval();
            interval.setInterval_id(rs.getInt(DBConfig.DB_FIELD_INTERVAL_ID));
            interval.setInterval(rs.getInt(DBConfig.DB_FIELD_INTERVAL));
            interval.setUnit(rs.getString(DBConfig.DB_FIELD_UNIT));
            recipe.setInterval(interval);
        } catch(SQLException e) {
            e.printStackTrace();;
        }
        return recipe;
    }
    
    // insert recipe to DB
    public static void insertRecipe(Recipe m_recipe) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "INSERT INTO " + DBConfig.DB_TBL_RECIPE + " ("
                                                  + DBConfig.DB_FIELD_RECIPE_ID + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_NAME + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_COUNTRY_ID + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_METHOD_ID + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_INTERVAL_ID + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_LEVEL + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_MATERIAL + ", " 
                                                  + DBConfig.DB_FIELD_RECIPE_STEPS + ")" +
            "VALUES " +
            "(" 
                    + DBUtil.getNextId(DBConfig.DB_TBL_RECIPE, DBConfig.DB_FIELD_RECIPE_ID) + ", " 
                    + "'" + m_recipe.getRecipe_name() + "', " 
                    + m_recipe.getCountry().getCountry_id() + ", "
                    + m_recipe.getMethod().getMethod_id() + ", "
                    + m_recipe.getInterval().getInterval_id() + ", "
                    + m_recipe.getLevel() + ", "
                    + "'" + m_recipe.getMaterial() + "', "
                    + "'" + m_recipe.getSteps() + "'" + 
            "); ";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update recipe
    public static void updateRecipe(Recipe m_recipe) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "UPDATE " + DBConfig.DB_TBL_RECIPE + " ";
            sql += "SET ";
            sql +=      DBConfig.DB_FIELD_RECIPE_NAME + "='" + m_recipe.getRecipe_name() + "', " ;
            sql +=      DBConfig.DB_FIELD_RECIPE_COUNTRY_ID + "=" + m_recipe.getCountry().getCountry_id() + ", " ;
            sql +=      DBConfig.DB_FIELD_RECIPE_METHOD_ID + "=" + m_recipe.getMethod().getMethod_id() + ", " ;
            sql +=      DBConfig.DB_FIELD_RECIPE_INTERVAL_ID + "=" + m_recipe.getInterval().getInterval_id() + ", " ;
            sql +=      DBConfig.DB_FIELD_RECIPE_LEVEL + "=" + m_recipe.getLevel() + ", " ;
            sql +=      DBConfig.DB_FIELD_RECIPE_MATERIAL + "='" + m_recipe.getMaterial() + "', " ;
            sql +=      DBConfig.DB_FIELD_RECIPE_STEPS + "='" + m_recipe.getSteps() + "' " ;
            sql += "WHERE " + DBConfig.DB_FIELD_RECIPE_ID + "=" + m_recipe.getRecipe_id() + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    // delete recipe from DB
    public static void deleteRecipe(int m_recipe_id) {
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "";
            sql += "DELETE FROM " + DBConfig.DB_TBL_RECIPE + " ";
            sql += "WHERE " + DBConfig.DB_FIELD_RECIPE_ID + "=" + m_recipe_id + ";";
            //System.out.println("sql: " + sql);
            
            stmt.executeUpdate(sql);
            conn.commit();
            db.closeConnection(stmt, null, conn);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * check foreign key of recipe inuse
     * 
     * USAGE:
     * - check country_id inuse : isForeignKeyInuse(DBConfig.DB_FIELD_COUNTRY_ID, 2);
     * - check method_id inuse  : isForeignKeyInuse(DBConfig.DB_FIELD_METHOD_ID, 3);
     * - check method_id inuse  : isForeignKeyInuse(DBConfig.DB_FIELD_INTERVAL_ID, 4);
     * 
     * @param m_foreign_key
     * @param m_foreign_key_value
     * @return 
     */
    
    public static boolean isForeignKeyInuse(String m_foreign_key, int m_foreign_key_value) {
        boolean isInuse = false;
        ArrayList list = new ArrayList();
        
        try {
            DBUtil db = new DBUtil();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql = "";
            sql += "SELECT * FROM " + DBConfig.DB_TBL_RECIPE;
            sql += " WHERE 1=1 AND " + m_foreign_key + " = " + m_foreign_key_value;
            sql += ";"; //System.out.println("sql: " + sql);
            
            rs = stmt.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    list.add(new Object());
                }
            }
            
            db.closeConnection(stmt, rs, conn);
                
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0) isInuse = true;
        return isInuse;
    }

    /**
     * @return the recipe_id
     */
    public int getRecipe_id() {
        return recipe_id;
    }

    /**
     * @param recipe_id the recipe_id to set
     */
    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    /**
     * @return the recipe_name
     */
    public String getRecipe_name() {
        return recipe_name;
    }

    /**
     * @param recipe_name the recipe_name to set
     */
    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the steps
     */
    public String getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(String steps) {
        this.steps = steps;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * @return the interval
     */
    public Interval getInterval() {
        return interval;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(Interval interval) {
        this.interval = interval;
    }
    
    public static void main(String[] args) {
        
        // test: ArrayList getRecipeList(String m_criteria)
        //showRecipeContent((Recipe)(getRecipeList()).get(0));
        
        // test: ArrayList getRecipeList(String m_criteria)
        //showRecipeContent((Recipe)(getRecipeList("BB")).get(0));
        
        // test: Recipe getRecipe(int m_recipe_id)
        //showRecipeContent(getRecipe(3));
        
        // test: insertRecipe(Recipe m_recipe)
        //insertRecipe(getTestingRecipe());
        
        // test: updateRecipe(Recipe m_recipe)
        //updateRecipe(getTestingRecipe());
        
        // test: deleteRecipe(int m_recipe_id)
        //deleteRecipe(4);
        
        // test: isForeignKeyInuse()
        System.out.println("Country id inuse: " + isForeignKeyInuse(DBConfig.DB_FIELD_COUNTRY_ID, 2));
        System.out.println("Method id inuse: " + isForeignKeyInuse(DBConfig.DB_FIELD_METHOD_ID, 3));
        System.out.println("Interval id inuse: " + isForeignKeyInuse(DBConfig.DB_FIELD_INTERVAL_ID, 4));
    }
    
    // for checking
    private static void showRecipeContent(Recipe r) {
        System.out.println(r.getRecipe_id());
        System.out.println(r.getRecipe_name());
        System.out.println(r.getCountry().getCountry_id());
        System.out.println(r.getCountry().getCountry_name());
        System.out.println(r.getLevel());
        System.out.println(r.getMethod().getMethod_id());
        System.out.println(r.getMethod().getMethod_name());
        System.out.println(r.getInterval().getInterval_id());
        System.out.println(r.getInterval().getInterval());
        System.out.println(r.getInterval().getUnit());
        System.out.println(r.getMaterial());
        System.out.println(r.getSteps());
    }
    
    // for testing
    private static Recipe getTestingRecipe() {
        Recipe recipe = new Recipe();
        recipe.setRecipe_id(4);
        recipe.setRecipe_name("yyy");
        Country region = new Country();
        region.setCountry_id(1);
        recipe.setCountry(region);
        Method method = new Method();
        method.setMethod_id(1);
        recipe.setMethod(method);
        Interval interval = new Interval();
        interval.setInterval_id(1);
        recipe.setInterval(interval);
        recipe.setLevel(1);
        recipe.setMaterial("yyy");
        recipe.setSteps("yyy");
        return recipe;
    }
    
}
