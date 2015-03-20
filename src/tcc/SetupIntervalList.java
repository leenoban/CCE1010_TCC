/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import to.Interval;
import to.Recipe;
import utils.DBConfig;

/**
 *
 * @author AngusLipsey
 */
public class SetupIntervalList extends JDialog implements ActionListener {

    JButton jbtAdd = new JButton(Constants.LBL_BTN_ADD);
    JButton jbtModify = new JButton(Constants.LBL_BTN_MODIFY);
    JButton jbtDelete = new JButton(Constants.LBL_BTN_DELETE);
    JButton jbtCancel = new JButton(Constants.LBL_BTN_CANCEL);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JTable intervalTable;
    TccTableModel model;
    TCC parent;

    public SetupIntervalList(TCC m_parent) {
        this.parent = m_parent;
        buildGUI();
        this.addListenerToObject();
    }

    // create mode
    private void showIntervalInputFrame() {
        // TODO: Benny :: Roy - call the interval input frame
        SetupIntervalInput intervalInput = new SetupIntervalInput(this);
        intervalInput.setLocationRelativeTo(null);
        intervalInput.setVisible(true);

    }

    // edit mode
    private void showIntervalInputFrame(Interval m_interval) {
        // TODO: Benny :: Roy - call the interval input frame
        SetupIntervalInput intervalModify = new SetupIntervalInput(m_interval, this);
        intervalModify.setLocationRelativeTo(null);
        intervalModify.setVisible(true);

    }

    // for cancel button call
    private void cancel() {
        parent.refreshRecipeList(Recipe.getRecipeList());
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SetupIntervalList sml = new SetupIntervalList(new TCC());
        sml.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
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
        //p1.setSize(200, 50);
        p1.setLayout(new GridLayout(5, 1, 0, 10));
        p1.add(jbtAdd);
        p1.add(jbtModify);
        p1.add(jbtDelete);
        p1.setBorder(emptyBorder);

        // Create JTable
        intervalTable = new JTable();
        intervalTable.setGridColor(Color.LIGHT_GRAY);

        refreshIntervalList(Interval.getIntervalList());

        intervalTable.setAutoCreateRowSorter(true);

        // Create ScrollPane
        JScrollPane scrollPane = new JScrollPane(intervalTable);
        scrollPane.setPreferredSize(new Dimension(200, 5));
        //scrollPane.setBorder(emptyBorder);
        intervalTable.setFillsViewportHeight(true);

        // Panel#2 hosts button of "Cancel" 
        //p2.setSize(200, 50); //Test
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
        this.setTitle(Constants.TITLE_INTERVAL);
        this.setResizable(false);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbtAdd) {
            showIntervalInputFrame();
            refreshIntervalList(Interval.getIntervalList());

        } else if (e.getSource() == jbtModify) {
            if (getTableCheckedCount() == 0 || getTableCheckedCount() > 1) {
                JOptionPane.showMessageDialog(this, Constants.NOT_ONE_ITEM_SELECTED);
            } else {
                Interval in = new Interval();
                for (int i = 0; i < intervalTable.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean) intervalTable.getModel().getValueAt(i, 0);
                    if (checked) {
                        in.setInterval_id((int) intervalTable.getModel().getValueAt(i, 1));
                    }
                }
                SetupIntervalInput ii = new SetupIntervalInput(in, this);
                ii.setLocationRelativeTo(null);
                ii.setVisible(true);
            }
        } else if (e.getSource() == jbtDelete) {
            if (getTableCheckedCount() < 1) {
                JOptionPane.showMessageDialog(this, Constants.NO_ITEM_SELECTED);
            } else {
                // get selected item
                ArrayList list = new ArrayList();
                for (int i = 0; i < intervalTable.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean) intervalTable.getModel().getValueAt(i, 0);
                    if (checked) {
                        Interval in = new Interval();
                        in.setInterval_id((int) intervalTable.getModel().getValueAt(i, 1));
                        int time_Interval = (int) intervalTable.getModel().getValueAt(i, 2);
                        int intervalID = in.getInterval_id(); //new
                        boolean isForeignKeyInuse = Recipe.isForeignKeyInuse(DBConfig.DB_FIELD_INTERVAL_ID, intervalID); //new
                        if (isForeignKeyInuse) { //new "if" statement
                            JOptionPane.showMessageDialog(this, "Time Interval " + "\"" + time_Interval + "\"" + " is already in use, cannot be deleted");
                        } else { //new
                            list.add(in);
                        }
                    }
                }
                if (list.size() > 0) //new
                {
                    deleteIntervals(list);
                }

            }
        } else if (e.getSource() == jbtCancel) {
            cancel();
        }

    }

    private void addListenerToObject() {

        intervalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int selectedRowIndex = target.getSelectedRow();
                    //int selectedColumnIndex = target.getSelectedColumn(); //System.out.println("selected: (" + selectedRowIndex + "),(" + selectedColumnIndex + ")");
                    //Object selectedObject = (Object) tbl_recipe.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
                    int interval_id = Integer.parseInt(((Object) intervalTable.getModel().getValueAt(selectedRowIndex, 1)).toString());

                    if (interval_id != -1) {
                        Interval interval = new Interval();
                        interval.setInterval_id(interval_id);
                        showIntervalInputFrame(interval);
                    }
                }
            }
        });

    }

    public void refreshIntervalList(ArrayList c_list) { //System.out.println("c_list.size(): " + c_list.size());

        if (c_list.size() > 0) {
            Object[][] data = new Object[c_list.size()][TccTableModel.TBL_INTERVAL.length()];
            for (int i = 0; i < c_list.size(); i++) {
                Interval in = (Interval) c_list.get(i);
                data[i][0] = Boolean.FALSE;
                data[i][1] = in.getInterval_id();
                data[i][2] = in.getInterval();
                data[i][3] = in.getUnit();

            }
            model = new TccTableModel(TccTableModel.TBL_INTERVAL, data);

        } else {
            model = new TccTableModel(TccTableModel.TBL_INTERVAL, TccTableModel.NO_REC_FOUND_RECIPE);
            intervalTable.setModel(model);
        }

        intervalTable.setModel(model);
        model.fireTableStructureChanged();
        intervalTable.setGridColor(Color.LIGHT_GRAY);
        TableCellRenderer rendererFromHeader = intervalTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < intervalTable.getColumnCount(); i++) {

            if (i != 0) {
                intervalTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            if (i == 0) {
                intervalTable.getColumnModel().getColumn(i).setPreferredWidth(10);
            } else if (i == 1) {
                intervalTable.getColumnModel().getColumn(i).setMinWidth(0);
                intervalTable.getColumnModel().getColumn(i).setMaxWidth(0);
            } else if (i == 2) {
                intervalTable.getColumnModel().getColumn(i).setPreferredWidth(50);
            } else if (i == 3) {
                intervalTable.getColumnModel().getColumn(i).setPreferredWidth(50);
            }

        }
    }

    private int getTableCheckedCount() {
        int count = 0;
        for (int i = 0; i < intervalTable.getModel().getRowCount(); i++) {
            boolean checked = (Boolean) intervalTable.getModel().getValueAt(i, 0);
            if (checked) {
                count++;
            }
        }
        return count;
    }

    private void deleteIntervals(ArrayList c_list) {

        int n = JOptionPane.showConfirmDialog(this, Constants.CONFIRM_DELETE_ITEM, Constants.TITLE_CONFIRM_DELETE, JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            for (int i = 0; i < c_list.size(); i++) {
                int interval_id = ((Interval) c_list.get(i)).getInterval_id();
                Interval.deleteInterval(interval_id);
            }
        }

        // refresh table
        refreshIntervalList(Interval.getIntervalList());
    }

}
