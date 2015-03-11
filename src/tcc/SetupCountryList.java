package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import to.Country;
import to.Recipe;
import utils.DBConfig;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AngusLipsey
 */
public class SetupCountryList extends JDialog implements ActionListener {

    JButton jbtAdd = new JButton(Constants.LBL_BTN_ADD);
    JButton jbtModify = new JButton(Constants.LBL_BTN_MODIFY);
    JButton jbtDelete = new JButton(Constants.LBL_BTN_DELETE);
    JButton jbtCancel = new JButton(Constants.LBL_BTN_CANCEL);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JTable countryTable;
    TccTableModel model;
    TCC parent;

    public SetupCountryList(TCC m_parent) {
        this.parent = m_parent;
        buildGUI();
        this.addListenerToObject();
    }

    // create mode
    private void showCountryInputFrame() {
        // TODO: Benny :: Roy - call the country input frame
        SetupCountryInput countryInput = new SetupCountryInput(this);
        countryInput.setLocationRelativeTo(null);
        countryInput.setVisible(true);

    }

    // edit mode
    private void showCountryInputFrame(Country m_country) {
        // TODO: Benny :: Roy - call the country input frame
        SetupCountryInput countryModify = new SetupCountryInput(m_country, this);
        countryModify.setLocationRelativeTo(null);
        countryModify.setVisible(true);

    }

    // for cancel button call
    private void cancel() {
        parent.refreshRecipeList(Recipe.getRecipeList());
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SetupCountryList srl = new SetupCountryList(new TCC());
        srl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        srl.setLocationRelativeTo(null);
        srl.setVisible(true);
    }

    private void buildGUI() {
        // TODO: Benny - build GUI

        jbtAdd.addActionListener(this);
        jbtModify.addActionListener(this);
        jbtDelete.addActionListener(this);
        jbtCancel.addActionListener(this);
        
        
        

        //Create EmptyBorder
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);

        // Panel#1 hosts button of "Add', "Modify", "Delete"  
        p1.setSize(200, 50);
        p1.setLayout(new GridLayout(5, 1, 0, 10));
        p1.add(jbtAdd);
        p1.add(jbtModify);
        p1.add(jbtDelete);
        p1.setBorder(emptyBorder);

        // Create JTable
        countryTable = new JTable();
        countryTable.setGridColor(Color.LIGHT_GRAY);

        refreshCountryList(Country.getCountryList());

        countryTable.setAutoCreateRowSorter(true);

        // Create ScrollPane
        JScrollPane scrollPane = new JScrollPane(countryTable);
        scrollPane.setPreferredSize(new Dimension(200, 5));
        //scrollPane.setBorder(emptyBorder);
        countryTable.setFillsViewportHeight(true);

        // Panel#2 hosts button of "Cancel" 
        p2.setLayout(new BorderLayout());
        p2.add(jbtCancel, BorderLayout.SOUTH);
        p2.setBorder(emptyBorder);

        //Panel#3 hosts title information in table
        p3.setLayout(new BorderLayout());
        p3.add(scrollPane, BorderLayout.CENTER);
        p3.setBorder(emptyBorder);

        // Group panel#1 & panel#2 into panel#4
        p4.setLayout(new BorderLayout());
        p4.add(p1, BorderLayout.CENTER);
        p4.add(p2, BorderLayout.SOUTH);

        // Create JFrame, add panel#3 & #4 into a frame
        //JFrame frame = new JFrame();
        this.setLayout(new BorderLayout());
        this.add(p3, BorderLayout.CENTER);
        this.add(p4, BorderLayout.EAST);

        this.setModal(true);
        this.setTitle(Constants.TITLE_COUNTRY);
        this.setResizable(false);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbtAdd) {
            showCountryInputFrame();
            refreshCountryList(Country.getCountryList());

        } else if (e.getSource() == jbtModify) {
            if (getTableCheckedCount() == 0 || getTableCheckedCount() > 1) {
                JOptionPane.showMessageDialog(this, Constants.NOT_ONE_ITEM_SELECTED);
            } else {
                Country c = new Country();
                for (int i = 0; i < countryTable.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean) countryTable.getModel().getValueAt(i, 0);
                    if (checked) {
                        c.setCountry_id((int) countryTable.getModel().getValueAt(i, 1));
                    }
                }
                SetupCountryInput ci = new SetupCountryInput(c, this);
                ci.setLocationRelativeTo(null);
                ci.setVisible(true);
            }
        } else if (e.getSource() == jbtDelete) {
            if (getTableCheckedCount() < 1) {
                JOptionPane.showMessageDialog(this, Constants.NO_ITEM_SELECTED);
            } else {
                 // get selected item
                ArrayList list = new ArrayList();
                for (int i = 0; i < countryTable.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean) countryTable.getModel().getValueAt(i, 0);
                    if (checked) {
                        Country c = new Country();
                        c.setCountry_id((int) countryTable.getModel().getValueAt(i, 1));
                        String country_Name = (String)countryTable.getModel().getValueAt(i, 2);
                        int countryID = c.getCountry_id(); //new
                        boolean isForeignKeyInuse = Recipe.isForeignKeyInuse(DBConfig.DB_FIELD_COUNTRY_ID, countryID); //new
                        if (isForeignKeyInuse){ //new "if" statement
                           JOptionPane.showMessageDialog(this, "Country " + "\"" + country_Name + "\"" + " is using recently, cannot be deleted");
                        }
                            else { //new
                                list.add(c);
                            }
                    }
                }
                if (list.size() > 0) //new
                    deleteCountries(list);

            }
        } else if (e.getSource() == jbtCancel) {
            cancel();
        }
    } //actionPerform
    
    private void addListenerToObject(){
        
        countryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int selectedRowIndex = target.getSelectedRow();
                    //int selectedColumnIndex = target.getSelectedColumn(); //System.out.println("selected: (" + selectedRowIndex + "),(" + selectedColumnIndex + ")");
                    //Object selectedObject = (Object) tbl_recipe.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
                    int country_id = Integer.parseInt(((Object)countryTable.getModel().getValueAt(selectedRowIndex, 1)).toString());
                    
                    if(country_id!=-1) {
                        Country country = new Country();
                        country.setCountry_id(country_id);
                        showCountryInputFrame(country);
                    }
                }
            }
        });
        
    }
    

    public void refreshCountryList(ArrayList c_list) { //System.out.println("c_list.size(): " + c_list.size());

        if (c_list.size() > 0) {
            Object[][] data = new Object[c_list.size()][TccTableModel.TBL_COUNTRY.length()];
            for (int i = 0; i < c_list.size(); i++) {
                Country c = (Country) c_list.get(i);
                data[i][0] = Boolean.FALSE;
                data[i][1] = c.getCountry_id();
                data[i][2] = c.getCountry_name();

            }
            model = new TccTableModel(TccTableModel.TBL_COUNTRY, data);

        } else {
            model = new TccTableModel(TccTableModel.TBL_COUNTRY, TccTableModel.NO_REC_FOUND_RECIPE);
            countryTable.setModel(model);
        }

        countryTable.setModel(model);
        model.fireTableStructureChanged();
        countryTable.setGridColor(Color.LIGHT_GRAY);
        TableCellRenderer rendererFromHeader = countryTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < countryTable.getColumnCount(); i++) {

            if (i != 0 && i != 2) {
                countryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            if (i == 0) {
                countryTable.getColumnModel().getColumn(i).setPreferredWidth(10);
            } else if (i == 1) {
                countryTable.getColumnModel().getColumn(i).setMinWidth(0);
                countryTable.getColumnModel().getColumn(i).setMaxWidth(0);
            } else if (i == 2) {
                countryTable.getColumnModel().getColumn(i).setPreferredWidth(120);
            }

        }
    }

    private int getTableCheckedCount() {
        int count = 0;
        for (int i = 0; i < countryTable.getModel().getRowCount(); i++) {
            boolean checked = (Boolean) countryTable.getModel().getValueAt(i, 0);
            if (checked) {
                count++;
            }
        }
        return count;
    }

    private void deleteCountries(ArrayList c_list) {

        int n = JOptionPane.showConfirmDialog(this, Constants.CONFIRM_DELETE_ITEM, Constants.TITLE_CONFIRM_DELETE, JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            for (int i = 0; i < c_list.size(); i++) {
                int country_id = ((Country) c_list.get(i)).getCountry_id();
                Country.deleteCountry(country_id);
            }
        }

        // refresh table
        refreshCountryList(Country.getCountryList());
    }

}
