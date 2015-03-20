/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import to.Interval;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author AngusLipsey
 */
public class SetupIntervalInput extends JDialog implements ActionListener {

    private SetupIntervalList parent;
    private String mode;
    private int interval_id;

    JLabel jlblTime = new JLabel("Time ");
    JLabel jlblUnit = new JLabel("Unit ");
    JTextField jtfTimeInput = new JTextField("", 5);
    JTextField jtfUnitInput = new JTextField("", 10);
    JButton jbtConfirm = new JButton(Constants.LBL_BTN_CONFIRM);
    JButton jbtCancel = new JButton(Constants.LBL_BTN_CANCEL);
    JPanel pBase = new JPanel();
    JPanel pContent = new JPanel();
    JPanel pButtons = new JPanel();

    public SetupIntervalInput(SetupIntervalList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }

    public SetupIntervalInput(Interval m_interval, SetupIntervalList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_MODIFY;
        this.interval_id = m_interval.getInterval_id();
        buildGUI();
        fillData(m_interval);
    }

    private void buildGUI() {
        // TODO: Roy - build GUI

        // One JPanel to hold the JLabel and JTextField
        pContent.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.BASELINE_TRAILING;
        grid.gridx = 0;
        grid.gridy = 0;

        // column 1
        pContent.add(jlblTime, grid);
        grid.gridx++;
        pContent.add(jtfTimeInput, grid);
        grid.gridx++;
        pContent.add(jlblUnit, grid);
        grid.gridx++;
        pContent.add(jtfUnitInput, grid);
        this.add(pContent, BorderLayout.CENTER);

        // Another JPanel to hold the two JButtons
        pButtons.add(jbtConfirm);
        pButtons.add(jbtCancel);
        jbtConfirm.addActionListener(this);
        jbtCancel.addActionListener(this);

        // JFrame to hold the two JPanels and set the layout
        this.setModal(true);
        this.setTitle(Constants.TITLE_INTERVAL + " - " + this.mode);
        this.setSize(800, 600);
        pBase.setBorder(new EmptyBorder(5, 5, 5, 5));
        pBase.setLayout(new BorderLayout());
        pBase.add(pContent, BorderLayout.CENTER);
        pBase.add(pButtons, BorderLayout.SOUTH);
        this.add(pBase);
        this.setResizable(false);
        this.pack();
        // FINISH: Roy - build GUI

    }

    // fill data to the interval form from Interval object (for update purpose)
    private void fillData(Interval m_interval) {
        // TODO: Roy - fill data
        int chkIntervalID = m_interval.getInterval_id();
        Interval i = Interval.getInterval(chkIntervalID);
        jtfTimeInput.setText(Integer.toString(i.getInterval()));
        jtfUnitInput.setText(i.getUnit());
    }

    // Update DB record - for save button call
    private void updateInterval(Interval m_interval) {
        if (this.mode.equals(Constants.MODE_CREATE)) {
            Interval.insertInterval(m_interval);
        } else if (this.mode.equals(Constants.MODE_MODIFY)) {
            Interval.updateInterval(m_interval);
        }
    }

    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SetupIntervalInput sri = new SetupIntervalInput(new SetupIntervalList(new TCC()));
        sri.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        sri.setLocationRelativeTo(null);
        sri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == jbtCancel) {
            cancel();
        } else if (obj == jbtConfirm) {
            if (jtfTimeInput.getText().equals("")
                    && jtfUnitInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter the required time and time unit!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else if (jtfTimeInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter the required time!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else if (jtfUnitInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter the time unit!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Interval i = new Interval();
                i.setInterval_id(this.interval_id);
                i.setInterval(Integer.parseInt(jtfTimeInput.getText()));
                i.setUnit(jtfUnitInput.getText());

                updateInterval(i);
                this.parent.refreshIntervalList(Interval.getIntervalList());
                cancel();
            }
        }

    }

}
