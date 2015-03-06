/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import to.Method;

/**
 *
 * @author AngusLipsey
 */
public class SetupMethodInput extends JDialog implements ActionListener {
    
    private SetupMethodList parent;
    private String mode;
    
    public SetupMethodInput() {
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }
    
    public SetupMethodInput(Method m_method, SetupMethodList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_MODIFY;
        buildGUI();
        fillData(m_method);
    }
    
    private void buildGUI() {
        // TODO: Roy - build GUI
        this.setModal(true);
        this.setTitle(Constants.TITLE_METHOD + " - " + this.mode);
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
        sri.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sri.setLocationRelativeTo(null);
        sri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }
    
}
