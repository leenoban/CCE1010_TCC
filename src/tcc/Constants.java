/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

/**
 *
 * @author AngusLipsey
 */
public class Constants {
    
    // General Constants
    public static final String MODE_CREATE      = "Create";
    public static final String MODE_MODIFY      = "Modify";
    
    public static final String TITLE_RECIPE     = "Personal Recipe";
    public static final String TITLE_COUNTRY    = "Setting - Country";
    public static final String TITLE_METHOD     = "Setting - Cooking Method";
    public static final String TITLE_INTERVAL   = "Setting - Required Time";
    
    public static final String TITLE_CONFIRM_DELETE = "Confirm Delete";
    
    public static final String LBL_BTN_SEARCH   = "Search";
    public static final String LBL_BTN_ADD      = "Add";
    public static final String LBL_BTN_MODIFY   = "Modify";
    public static final String LBL_BTN_DELETE   = "Delete";
    public static final String LBL_BTN_CANCEL   = "Cancel";
    public static final String LBL_BTN_SAVE     = "Save";
    public static final String LBL_BTN_CONFIRM  = "Confirm";
    public static final String LBL_BTN_EXIT     = "Exit";
    
    public static final String LBL_SETTING      = "Setting";
    public static final String LBL_COUNTRY      = "Country";
    public static final String LBL_METHOD       = "Method";
    public static final String LBL_INTERVAL     = "Interval";
    
    
    // Recipe Constants
    public static final int[] LEVEL = {1,2,3,4,5};
    
    // DB Constants
    public static final char[] SQL_INVALID_CHAR = {'|','-','*','/','<','>',',','=','~','!','^','(', ')','\'','`',';'};
    
    // Table Constants
    public static final String NOT_ONE_ITEM_SELECTED    = "Please select ONE item.";
    public static final String NO_ITEM_SELECTED         = "No item selected.";
    public static final String CONFIRM_DELETE_ITEM      = "Are you confirm to delete the selected item(s)?";
}
