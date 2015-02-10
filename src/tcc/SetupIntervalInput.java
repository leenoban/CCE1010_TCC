/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import to.Interval;

/**
 *
 * @author AngusLipsey
 */
public class SetupIntervalInput extends JFrame implements ActionListener {
    
    private static String title = "Setting - Required Time - Add / Modify";
    private String mode;
    
    public SetupIntervalInput() {
        super(title);
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }
    
    public SetupIntervalInput(Interval m_interval) {
        super(title);
        this.mode = Constants.MODE_MODIFY;
        buildGUI();
        fillData(m_interval);
    }
    
    private void buildGUI() {
        // TODO: Roy - build GUI
        this.setSize(800, 600);
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
        SetupIntervalInput sri = new SetupIntervalInput();
        sri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sri.setLocationRelativeTo(null);
        sri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
    
}
