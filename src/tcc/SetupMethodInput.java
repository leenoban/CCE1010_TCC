/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import to.Method;

/**
 *
 * @author AngusLipsey
 */
public class SetupMethodInput extends JFrame implements ActionListener {
    
    private static String title = "Setting - Cooking Method - Add / Modify";
    private String mode;
    
    public SetupMethodInput() {
        super(title);
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }
    
    public SetupMethodInput(Method m_method) {
        super(title);
        this.mode = Constants.MODE_MODIFY;
        buildGUI();
        fillData(m_method);
    }
    
    private void buildGUI() {
        // TODO: Roy - build GUI
        this.setSize(800, 600);
    }
    
    // fill data to the method form from Method object (for update purpose)
    private void fillData(Method m_method) {
        // TODO: Roy - fill data
    }
    
    // Update DB record - for save button call
    private void updateMethod(Method m_method) {
        if(this.mode.equals(Constants.MODE_CREATE)) {
            Method.insertMethod(m_method);
        } else if(this.mode.equals(Constants.MODE_MODIFY)) {
            Method.updateMethod(m_method);
        }
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        SetupMethodInput sri = new SetupMethodInput();
        sri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sri.setLocationRelativeTo(null);
        sri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("test")) {
            cancel();
        }
    }
    
}
