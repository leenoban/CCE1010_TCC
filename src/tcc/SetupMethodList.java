/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import to.Method;

/**
 *
 * @author AngusLipsey
 */
public class SetupMethodList extends JDialog implements ActionListener {
    
    JButton jbtAdd = new JButton(Constants.LBL_BTN_ADD);
    JButton jbtModify = new JButton(Constants.LBL_BTN_MODIFY);
    JButton jbtDelete = new JButton(Constants.LBL_BTN_DELETE);
    JButton jbtCancel = new JButton(Constants.LBL_BTN_CANCEL);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JTable methodTable;
    TccTableModel model;
    
    public SetupMethodList() {
        buildGUI();
    }
    
    // create mode
    private void showMethoInputFrame() {
        // TODO: Benny :: Roy - call the method input frame
        SetupMethodInput methodInput = new SetupMethodInput(this);
        methodInput.setLocationRelativeTo(null);
        methodInput.setVisible(true);
        
    }
    
    // edit mode
    private void showMethoInputFrame(Method m_method) {
        // TODO: Benny :: Roy - call the method input frame
        SetupMethodInput methodModify = new SetupMethodInput(m_method, this);
        methodModify.setLocationRelativeTo(null);
        methodModify.setVisible(true);
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        SetupMethodList sml = new SetupMethodList();
        sml.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sml.setLocationRelativeTo(null);
        sml.setVisible(true);
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
        methodTable = new JTable();
        methodTable.setGridColor(Color.LIGHT_GRAY);

        refreshMethodList(Method.getMethodList());
        
        methodTable.setAutoCreateRowSorter(true);
        
         // Create ScrollPane
        JScrollPane scrollPane = new JScrollPane(methodTable);
        scrollPane.setPreferredSize(new Dimension(200, 5));
        //scrollPane.setBorder(emptyBorder);
        methodTable.setFillsViewportHeight(true);
        
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
        this.setTitle(Constants.TITLE_METHOD);
        //this.setSize(800, 600);
        //pack();
        this.pack();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == jbtAdd) {
            showMethoInputFrame();
            refreshMethodList(Method.getMethodList());

        } else if (e.getSource() == jbtModify) {
            if (getTableCheckedCount() == 0 || getTableCheckedCount() > 1) {
                JOptionPane.showMessageDialog(this, Constants.NOT_ONE_ITEM_SELECTED);
            } else {
                Method m = new Method();
                for (int i = 0; i < methodTable.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean) methodTable.getModel().getValueAt(i, 0);
                    if (checked) {
                        m.setMethod_id((int) methodTable.getModel().getValueAt(i, 1));
                    }
                }
                SetupMethodInput mi = new SetupMethodInput(m, this);
                mi.setLocationRelativeTo(null);
                mi.setVisible(true);
            }
        } else if (e.getSource() == jbtDelete) {
            if (getTableCheckedCount() < 1) {
                JOptionPane.showMessageDialog(this, Constants.NO_ITEM_SELECTED);
            } else {
                // get selected item
                ArrayList list = new ArrayList();
                for (int i = 0; i < methodTable.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean) methodTable.getModel().getValueAt(i, 0);
                    if (checked) {
                        Method m = new Method();
                        m.setMethod_id((int) methodTable.getModel().getValueAt(i, 1));
                        list.add(m);
                    }
                }
                deleteMethods(list);

            }
        } else if (e.getSource() == jbtCancel) {
            cancel();
        }
    } //actionPerform  

        
 

    
    public void refreshMethodList(ArrayList c_list) { //System.out.println("c_list.size(): " + c_list.size());

        if (c_list.size() > 0) {
            Object[][] data = new Object[c_list.size()][TccTableModel.TBL_METHOD.length()];
            for (int i = 0; i < c_list.size(); i++) {
                Method m = (Method) c_list.get(i);
                data[i][0] = Boolean.FALSE;
                data[i][1] = m.getMethod_id();
                data[i][2] = m.getMethod_name();

            }
            model = new TccTableModel(TccTableModel.TBL_METHOD, data);

        } else {
            model = new TccTableModel(TccTableModel.TBL_METHOD, TccTableModel.NO_REC_FOUND_RECIPE);
            methodTable.setModel(model);
        }

        methodTable.setModel(model);
        model.fireTableStructureChanged();
        methodTable.setGridColor(Color.LIGHT_GRAY);
        TableCellRenderer rendererFromHeader = methodTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < methodTable.getColumnCount(); i++) {

            if (i != 0 && i != 2) {
                methodTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            if (i == 0) {
                methodTable.getColumnModel().getColumn(i).setPreferredWidth(10);
            } else if (i == 1) {
                methodTable.getColumnModel().getColumn(i).setMinWidth(0);
                methodTable.getColumnModel().getColumn(i).setMaxWidth(0);
            } else if (i == 2) {
                methodTable.getColumnModel().getColumn(i).setPreferredWidth(120);
            }

        }
    }
    
    private int getTableCheckedCount() {
        int count = 0;
        for (int i = 0; i < methodTable.getModel().getRowCount(); i++) {
            boolean checked = (Boolean) methodTable.getModel().getValueAt(i, 0);
            if (checked) {
                count++;
            }
        }
        return count;
    }
    
    private void deleteMethods(ArrayList c_list) {

        int n = JOptionPane.showConfirmDialog(this, Constants.CONFIRM_DELETE_ITEM, Constants.TITLE_CONFIRM_DELETE, JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            for (int i = 0; i < c_list.size(); i++) {
                int method_id = ((Method) c_list.get(i)).getMethod_id();
                Method.deleteMethod(method_id);
            }
        }

        // refresh table
        refreshMethodList(Method.getMethodList());
    }
    
}
