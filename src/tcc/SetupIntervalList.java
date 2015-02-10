/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import javax.swing.JFrame;
import to.Interval;

/**
 *
 * @author AngusLipsey
 */
public class SetupIntervalList extends JFrame {
    
    private static String title = "Setting - Required Time";
    
    public SetupIntervalList() {
        super(title);
        buildGUI();
    }
    
    // create mode
    private void showIntervalInputFrame() {
        // TODO: Benny :: Roy - call the interval input frame
    }
    
    // edit mode
    private void showIntervalInputFrame(Interval m_interval) {
        // TODO: Benny :: Roy - call the interval input frame
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        SetupIntervalList sml = new SetupIntervalList();
        sml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sml.setLocationRelativeTo(null);
        sml.setVisible(true);
    }
    
    private void buildGUI() {
        // TODO: Benny - build GUI
        this.setSize(800, 600);
    }
    
}
