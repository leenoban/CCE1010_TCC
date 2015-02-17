package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import to.Country;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AngusLipsey
 */
public class SetupCountryList extends JDialog implements ActionListener {
    
    public SetupCountryList() {
        buildGUI();
    }
    
    // create mode
    private void showCountryInputFrame() {
        // TODO: Benny :: Roy - call the country input frame
    }
    
    // edit mode
    private void showCountryInputFrame(Country m_country) {
        // TODO: Benny :: Roy - call the country input frame
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);this.
        dispose();
    }
    
    public static void main(String args[]) {
        SetupCountryList srl = new SetupCountryList();
        srl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        srl.setLocationRelativeTo(null);
        srl.setVisible(true);
        srl.showCountryInputFrame();
    }
    
    private void buildGUI() {
        // TODO: Benny - build GUI
        this.setModal(true);
        this.setTitle(Constants.TITLE_COUNTRY);
        this.setSize(800, 600);
        //pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }
    
}
