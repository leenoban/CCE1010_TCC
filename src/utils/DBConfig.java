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
            "(4, 'Italy'), " +
            "(5, 'Italy'), " +
            "(6, 'Italy'), " +
            "(7, 'Italy'), " +
            "(8, 'Italy'), " +
            "(9, 'Italy'), " +
            "(10, 'Italy'), " +
            "(11, 'France'); "
            
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
            "(1, 'Kung Pao chicken', 1, 1, 1, 1, '1 teaspoon soy sauce \n1 teaspoon hoisin sauce \n1 teaspoon sesame oil \n... ', '1. Marinate the chicken: In a medium bowl, stir together the soy sauce, rice wine, and cornstarch until the cornstarch is dissolved ...'), " +
            "(2, 'BBQ beef short ribs', 2, 2, 2, 2, '2 x 1.5 kg  beef short ribs \nsea salt \nfreshly ground black pepper \n...', 'Place the ribs in a snug-fitting roasting tray, season with salt and pepper, drizzle with olive oil, then rub all over. Cover tightly with a double layer of tin foil, then cook for 7 to 8 hours, or until cooked through and tender.....'), " +
            "(3, 'Oysters with chilli, ginger & rice wine vinegar', 3, 3, 3, 3, '½ thumb-sized piece peeled ginger \n6 tablespoons rice wine vinegar \n1  red chilli \n...', 'Oysters are funny old things. Now they''re considered a decadent aphrodisiac, when only 100 years ago they were the pigeons of the sea and would be chucked into pies as peasant food. Aphrodisiac? I''m not sure, but I do seem to have acquired a taste for them over the last 3 years......'); "
            ;
}
