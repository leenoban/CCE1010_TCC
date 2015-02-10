/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import javax.swing.JFrame;
import to.Method;

/**
 *
 * @author AngusLipsey
 */
public class SetupMethodList extends JFrame {
    
    private static String title = "Setting - Cooking Method";
    
    public SetupMethodList() {
        super(title);
        buildGUI();
    }
    
    // create mode
    private void showMethoInputFrame() {
        // TODO: Benny :: Roy - call the method input frame
    }
    
    // edit mode
    private void showMethoInputFrame(Method m_method) {
        // TODO: Benny :: Roy - call the method input frame
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        SetupMethodList sml = new SetupMethodList();
        sml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sml.setLocationRelativeTo(null);
        sml.setVisible(true);
    }
    
    private void buildGUI() {
        // TODO: Benny - build GUI
        this.setSize(800, 600);
    }
    
}
