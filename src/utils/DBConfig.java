/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author AngusLipsey
 */
public class DBConfig {
    
    public static final String DB_FIELD_NEXT_ID             = "NEXT_ID";
    
    public static final String DB_TBL_RECIPE                = "RECIPE";
    public static final String DB_FIELD_RECIPE_ID           = "RECIPE_ID";
    public static final String DB_FIELD_RECIPE_NAME         = "RECIPE_NAME";
    public static final String DB_FIELD_RECIPE_COUNTRY_ID   = "COUNTRY_ID";
    public static final String DB_FIELD_RECIPE_METHOD_ID    = "METHOD_ID";
    public static final String DB_FIELD_RECIPE_INTERVAL_ID  = "INTERVAL_ID";
    public static final String DB_FIELD_RECIPE_LEVEL        = "LEVEL_ID";
    public static final String DB_FIELD_RECIPE_MATERIAL     = "MATERIAL";
    public static final String DB_FIELD_RECIPE_STEPS        = "STEPS";
    
    public static final String DB_TBL_COUNTRY               = "COUNTRY";
    public static final String DB_FIELD_COUNTRY_ID          = "COUNTRY_ID";
    public static final String DB_FIELD_COUNTRY_NAME        = "COUNTRY_NAME";
    
    public static final String DB_TBL_METHOD                = "METHOD";
    public static final String DB_FIELD_METHOD_ID           = "METHOD_ID";
    public static final String DB_FIELD_METHOD_NAME         = "METHOD_NAME";
    
    public static final String DB_TBL_INTERVAL              = "INTERVAL";
    public static final String DB_FIELD_INTERVAL_ID         = "INTERVAL_ID";
    public static final String DB_FIELD_INTERVAL            = "INTERVAL";
    public static final String DB_FIELD_UNIT                = "UNIT";
    
    /* 
    ============================================================================
     Create Tables
    ============================================================================
    */
    public static final String SQL_CREATE_TBL_RECIPE = 
            "CREATE TABLE " + DB_TBL_RECIPE + "("  +
            "      " + DB_FIELD_RECIPE_ID          + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_RECIPE_NAME        + " TEXT            NOT NULL, " +
            "      " + DB_FIELD_RECIPE_COUNTRY_ID  + " INT             NOT NULL, " +
            "      " + DB_FIELD_RECIPE_METHOD_ID   + " INT             NOT NULL, " +
            "      " + DB_FIELD_RECIPE_INTERVAL_ID + " INT             NOT NULL, " +
            "      " + DB_FIELD_RECIPE_LEVEL       + " INT                     , " +
            "      " + DB_FIELD_RECIPE_MATERIAL    + " TEXT                    , " +
            "      " + DB_FIELD_RECIPE_STEPS       + " TEXT                      " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_COUNTRY = 
            "CREATE TABLE " + DB_TBL_COUNTRY + " (" +
            "      " + DB_FIELD_COUNTRY_ID          + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_COUNTRY_NAME        + " TEXT            NOT NULL " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_METHOD = 
            "CREATE TABLE " + DB_TBL_METHOD + " (" +
            "      " + DB_FIELD_METHOD_ID          + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_METHOD_NAME        + " TEXT            NOT NULL  " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_INTERVAL = 
            "CREATE TABLE " + DB_TBL_INTERVAL + " (" +
            "      " + DB_FIELD_INTERVAL_ID          + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_INTERVAL             + " INT             NOT NULL, " +
            "      " + DB_FIELD_UNIT                 + " TEXT            NOT NULL  " +
            ");"
            ;
    
    /* 
    ============================================================================
     Init Data
    ============================================================================
    */
    public static final String SQL_INSERT_COUNTRY =
            "INSERT INTO " + DB_TBL_COUNTRY + " (" + DB_FIELD_COUNTRY_ID + ", "
                                                  + DB_FIELD_COUNTRY_NAME + ")" +
            "VALUES " +
            "(1, 'China'), " +
            "(2, 'England'), " +
            "(3, 'Italy'), " +
            "(4, 'France'); "
            ;
    
    public static final String SQL_INSERT_METHOD =
            "INSERT INTO " + DB_TBL_METHOD + " (" + DB_FIELD_METHOD_ID + ", " 
                                                  + DB_FIELD_METHOD_NAME + ")" +
            "VALUES " +
            "(1, 'Fired'), " +
            "(2, 'Steamed'), " +
            "(3, 'Stew'), " +
            "(4, 'Slow-Cooked'), " +
            "(5, 'Water-Boiled'); "
            ;
    
    public static final String SQL_INSERT_INTERVAL =
            "INSERT INTO " + DB_TBL_INTERVAL + " (" + DB_FIELD_INTERVAL_ID + ", " 
                                                    + DB_FIELD_INTERVAL + ", "
                                                    + DB_FIELD_UNIT + ")" +
            "VALUES " +
            "(1, 5, 'Min(s)'), " +
            "(2, 10, 'Min(s)'), " +
            "(3, 15, 'Min(s)'), " +
            "(4, 30, 'Min(s)'), " +
            "(5, 45, 'Min(s)'), " +
            "(6, 1, 'Hr(s)'); "
            ;
            ;
    
    public static final String SQL_INSERT_RECIPE =
            "INSERT INTO " + DB_TBL_RECIPE + " (" + DB_FIELD_RECIPE_ID + ", " 
                                                  + DB_FIELD_RECIPE_NAME + ", " 
                                                  + DB_FIELD_RECIPE_COUNTRY_ID + ", " 
                                                  + DB_FIELD_RECIPE_METHOD_ID + ", " 
                                                  + DB_FIELD_RECIPE_INTERVAL_ID + ", " 
                                                  + DB_FIELD_RECIPE_LEVEL + ", " 
                                                  + DB_FIELD_RECIPE_MATERIAL + ", " 
                                                  + DB_FIELD_RECIPE_STEPS + ")" +
            "VALUES " +
            "(1, 'AAA', 1, 1, 1, 1, '(BlahBlahBlah)', '(BlahBlahBlah)'), " +
            "(2, 'BBB', 2, 2, 2, 2, '(BlahBlahBlah)', '(BlahBlahBlah)'), " +
            "(3, 'CCC', 3, 3, 3, 3, '(BlahBlahBlah)', '(BlahBlahBlah)'); "
            ;
}
