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
import javax.swing.JLabel;
import javax.swing.JTextField;
import to.Country;

/**
 *
 * @author AngusLipsey
 */
public class SetupCountryInput extends JFrame implements ActionListener {
    
    private static String title = "Setting - Country - Add / Modify";
    private String mode;
    
    public SetupCountryInput() {
        super(title);
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }
    
    public SetupCountryInput(Country m_country) {
        super(title);
        this.mode = Constants.MODE_MODIFY;
        buildGUI();
        fillData(m_country);
    }
    
    private void buildGUI() {
        // TODO: Roy - build GUI
        JFrame frame = new JFrame("Setting - Country - Add / Modify");
        JButton jbtConfirm = new JButton("Confirm");
        JButton jbtCancel = new JButton("Cancel");
        JLabel jlbCountryName = new JLabel("Country Name");
        //JTextField jtfCountryName = new JTextField();
        
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(jbtConfirm);
        frame.getContentPane().add(jbtCancel);
        frame.getContentPane().add(jlbCountryName);
        //frame.getContentPane().add(jtfCountryName);
        
        //jbtConfirm.setHorizontalAlignment(20);
        //jbtConfirm.setVerticalAlignment(40);
        

        this.setSize(800, 600);
    }
    
    // fill data to the country form from Country object (for update purpose)
    private void fillData(Country m_country) {
        // TODO: Roy - fill data
    }
    
    // Update DB record - for save button call
    private void updateCountry(Country m_country) {
        if(this.mode.equals(Constants.MODE_CREATE)) {
            Country.insertCountry(m_country);
        } else if(this.mode.equals(Constants.MODE_MODIFY)) {
            Country.updateCountry(m_country);
        }
    }
    
    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        SetupCountryInput sri = new SetupCountryInput();
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
