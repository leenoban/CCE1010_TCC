/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author AngusLipsey
 */
public class TccTableModel extends AbstractTableModel {
    
    public static final String TBL_RECIPE = "recipe";
    public static final String TBL_COUNTRY = "country";
    public static final String TBL_METHOD = "method";
    public static final String TBL_INTERVAL = "interval";
    private final static String[] tbl_header_recipe = {"#", "Recipe Id", "Recipe Name", "Country", "Cooking Method", "Time Required", "Difficulity"};
    private final static String[] tbl_header_country = {"#","Country Id", "Country Name"};
    private final static String[] tbl_header_method = {"#", "Method Id", "Method Name"};
    private final static String[] tbl_header_interval = {"#", "Interval Id", "Time", "Unit"};
    
    private static String[] tbl_header;
    private Object[][] tbl_data;
    
    private static final int FAKE_ID = -1;
    private static final String NO_REC_FOUND = "(No record found)";
    public static final Object[][] NO_REC_FOUND_RECIPE = new Object[][] {{Boolean.FALSE, FAKE_ID, NO_REC_FOUND, "", "", "", ""}};
    
    public TccTableModel(String m_table_name, Object[][] m_data) {
        switch (m_table_name) {
            case TBL_RECIPE:
                tbl_header = tbl_header_recipe;
                break;
            case TBL_COUNTRY:
                tbl_header = tbl_header_country;
                break;
            case TBL_METHOD:
                tbl_header = tbl_header_method;
                break;
            case TBL_INTERVAL:
                tbl_header = tbl_header_interval;
                break;
        }
        this.tbl_data = m_data;
    }

    @Override
    public int getRowCount() {
        return tbl_data.length;
    }

    @Override
    public int getColumnCount() {
        return tbl_header.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return tbl_header[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tbl_data[rowIndex][columnIndex];
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        tbl_data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }
}
