/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import tcc.Constants;

/**
 *
 * @author AngusLipsey
 */
public class DBConfig {
    
    public static final String DB_FIELD_NEXT_ID             = "NEXT_ID";
    
    public static final String DB_TBL_RECIPE                = "RECIPE";
    public static final String DB_FIELD_RECIPE_ID           = "RECIPE_ID";
    public static final String DB_FIELD_RECIPE_NAME         = "RECIPE_NAME";
    public static final String DB_FIELD_RECIPE_REGION_ID    = "REGION_ID";
    public static final String DB_FIELD_RECIPE_METHOD_ID    = "METHOD_ID";
    public static final String DB_FIELD_RECIPE_INTERVAL_ID  = "INTERVAL_ID";
    public static final String DB_FIELD_RECIPE_LEVEL        = "LEVEL_ID";
    public static final String DB_FIELD_RECIPE_MATERIAL     = "MATERIAL";
    public static final String DB_FIELD_RECIPE_STEPS        = "STEPS";
    
    public static final String DB_TBL_REGION                = "REGION";
    public static final String DB_FIELD_REGION_ID           = "REGION_ID";
    public static final String DB_FIELD_REGION_NAME_TW      = "REGION_NAME_TW";
    public static final String DB_FIELD_REGION_NAME_CN      = "REGION_NAME_CN";
    
    public static final String DB_TBL_METHOD                = "METHOD";
    public static final String DB_FIELD_METHOD_ID           = "METHOD_ID";
    public static final String DB_FIELD_METHOD_NAME_TW      = "METHOD_NAME_TW";
    public static final String DB_FIELD_METHOD_NAME_CN      = "METHOD_NAME_CN";
    
    public static final String DB_TBL_INTERVAL              = "INTERVAL";
    public static final String DB_FIELD_INTERVAL_ID         = "INTERVAL_ID";
    public static final String DB_FIELD_INTERVAL            = "INTERVAL";
    public static final String DB_FIELD_UNIT_TW             = "UNIT_TW";
    public static final String DB_FIELD_UNIT_CN             = "UNIT_CN";
    
    public static final String DB_TBL_SYSPARAM              = "SYSPARAM ";
    public static final String DB_FIELD_DEFAULT_LANG        = "DEFAULT_LANG";
    
    /* 
    ============================================================================
     Create Tables
    ============================================================================
    */
    public static final String SQL_CREATE_TBL_RECIPE = 
            "CREATE TABLE " + DB_TBL_RECIPE + "(" +
            "      " + DB_FIELD_RECIPE_ID          + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_RECIPE_NAME        + " TEXT            NOT NULL, " +
            "      " + DB_FIELD_RECIPE_REGION_ID   + " INT             NOT NULL, " +
            "      " + DB_FIELD_RECIPE_METHOD_ID   + " INT             NOT NULL, " +
            "      " + DB_FIELD_RECIPE_INTERVAL_ID + " INT             NOT NULL, " +
            "      " + DB_FIELD_RECIPE_LEVEL        + " INT                     , " +
            "      " + DB_FIELD_RECIPE_MATERIAL    + " TEXT                    , " +
            "      " + DB_FIELD_RECIPE_STEPS       + " TEXT                      " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_REGION = 
            "CREATE TABLE " + DB_TBL_REGION + " (" +
            "      " + DB_FIELD_REGION_ID      + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_REGION_NAME_TW + " TEXT            NOT NULL, " +
            "      " + DB_FIELD_REGION_NAME_CN + " TEXT            NOT NULL  " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_METHOD = 
            "CREATE TABLE " + DB_TBL_METHOD + " (" +
            "      " + DB_FIELD_METHOD_ID      + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_METHOD_NAME_TW + " TEXT            NOT NULL, " +
            "      " + DB_FIELD_METHOD_NAME_CN + " TEXT            NOT NULL  " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_INTERVAL = 
            "CREATE TABLE " + DB_TBL_INTERVAL + " (" +
            "      " + DB_FIELD_INTERVAL_ID + " INT PRIMARY KEY NOT NULL, " +
            "      " + DB_FIELD_INTERVAL    + " INT             NOT NULL, " +
            "      " + DB_FIELD_UNIT_TW     + " TEXT            NOT NULL, " +
            "      " + DB_FIELD_UNIT_CN     + " TEXT            NOT NULL  " +
            ");"
            ;
    
    public static final String SQL_CREATE_TBL_SYSPARAM = 
            "CREATE TABLE " +DB_TBL_SYSPARAM + " (" +
            "      " + DB_FIELD_DEFAULT_LANG + " INT NOT NULL  " +
            ");"
            ;
    
    /* 
    ============================================================================
     Init Data
    ============================================================================
    */
    public static final String SQL_INSERT_REGION =
            "INSERT INTO " + DB_TBL_REGION + " (" + DB_FIELD_REGION_ID + ", " 
                                                  + DB_FIELD_REGION_NAME_TW + ", " 
                                                  + DB_FIELD_REGION_NAME_CN + ")" +
            "VALUES " +
            "(1, '上海', '上海'), " +
            "(2, '北京', '北京'), " +
            "(3, '四川', '四川'), " +
            "(4, '廣東', '广东'); "
            ;
    
    public static final String SQL_INSERT_METHOD =
            "INSERT INTO " + DB_TBL_METHOD + " (" + DB_FIELD_METHOD_ID + ", " 
                                                  + DB_FIELD_METHOD_NAME_TW + ", " 
                                                  + DB_FIELD_METHOD_NAME_CN + ")" +
            "VALUES " +
            "(1, '煎', '煎'), " +
            "(2, '炒', '炒'), " +
            "(3, '炆', '炆'), " +
            "(4, '炖', '炖'), " +
            "(5, '煮', '煮'), " +
            "(6, '炸', '炸'), " +
            "(7, '焗', '焗'), " +
            "(8, '灼', '灼'), " +
            "(9, '蒸', '蒸'); "
            ;
    
    public static final String SQL_INSERT_INTERVAL =
            "INSERT INTO " + DB_TBL_INTERVAL + " (" + DB_FIELD_INTERVAL_ID + ", " 
                                                    + DB_FIELD_INTERVAL + ", " 
                                                    + DB_FIELD_UNIT_TW + ", " 
                                                    + DB_FIELD_UNIT_CN + ")" +
            "VALUES " +
            "(1, 5, '分鐘', '分钟'), " +
            "(2, 10, '分鐘', '分钟'), " +
            "(3, 15, '分鐘', '分钟'), " +
            "(4, 30, '分鐘', '分钟'), " +
            "(5, 45, '分鐘', '分钟'), " +
            "(6, 1, '小時', '小时'); "
            ;
    
    public static final String SQL_INSERT_SYSPARAM =
            "INSERT INTO " + DB_TBL_SYSPARAM + " (" + DB_FIELD_DEFAULT_LANG + ")" +
            "VALUES ('" + Constants.LANG_TW + "'); "
            ;
    
    public static final String SQL_INSERT_RECIPE =
            "INSERT INTO " + DB_TBL_RECIPE + " (" + DB_FIELD_RECIPE_ID + ", " 
                                                  + DB_FIELD_RECIPE_NAME + ", " 
                                                  + DB_FIELD_RECIPE_REGION_ID + ", " 
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
