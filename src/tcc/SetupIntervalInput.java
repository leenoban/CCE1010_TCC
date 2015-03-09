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

/**
 *
 * @author AngusLipsey
 */
public class SetupIntervalInput extends JDialog implements ActionListener {

    private SetupIntervalList parent;
    private String mode;
    private int interval_id;
    
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
        this.setModal(true);
        this.setTitle(Constants.TITLE_INTERVAL + " - " + this.mode);
        this.setSize(800, 600);
        //pack();
    }
    
    // fill data to the interval form from Interval object (for update purpose)
    private void fillData(Interval m_interval) {
        // TODO: Roy - fill data
    }
    
    // Update DB record - for save button call
    private void updateInterval(Interval m_interval) {
        if(this.mode.equals(Constants.MODE_CREATE)) {
            Interval.insertInterval(m_interval);
        } else if(this.mode.equals(Constants.MODE_MODIFY)) {
            Interval.updateInterval(m_interval);
        }
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        SetupIntervalInput sri = new SetupIntervalInput(new SetupIntervalList());
        sri.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sri.setLocationRelativeTo(null);
        sri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }
    
}
