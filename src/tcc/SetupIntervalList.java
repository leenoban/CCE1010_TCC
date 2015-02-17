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
public class SetupIntervalList extends JDialog implements ActionListener {
    
    public SetupIntervalList() {
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
        sml.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sml.setLocationRelativeTo(null);
        sml.setVisible(true);
    }
    
    private void buildGUI() {
        // TODO: Benny - build GUI
        this.setModal(true);
        this.setTitle(Constants.TITLE_INTERVAL);
        this.setSize(800, 600);
        //pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }
    
}
