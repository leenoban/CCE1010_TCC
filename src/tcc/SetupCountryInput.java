/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import to.Country;
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
public class SetupCountryInput extends JDialog implements ActionListener {

    private SetupCountryList parent;
    private String mode;
    private int country_id;

    JLabel jlblCountryName = new JLabel(Constants.LBL_COUNTRY + " Name");
    JTextField jtfInput = new JTextField("", 20);
    JButton jbtConfirm = new JButton(Constants.LBL_BTN_CONFIRM);
    JButton jbtCancel = new JButton(Constants.LBL_BTN_CANCEL);
    JPanel pBase = new JPanel();
    JPanel pContent = new JPanel();
    JPanel pButtons = new JPanel();

    public SetupCountryInput(SetupCountryList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }

    public SetupCountryInput(Country m_country, SetupCountryList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_MODIFY;
        this.country_id = m_country.getCountry_id();
        buildGUI();
        fillData(m_country);
    }

    private void buildGUI() {
        // TODO: Roy - build GUI

        pContent.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.BASELINE_TRAILING;
        grid.gridx = 0;
        grid.gridy = 0;

        pContent.add(jlblCountryName, grid);
        grid.gridy++;
        pContent.add(jtfInput, grid);
        this.add(pContent, BorderLayout.CENTER);

        // Another JPanel to hold the two JButtons
        pButtons.add(jbtConfirm);
        pButtons.add(jbtCancel);
        jbtConfirm.addActionListener(this);
        jbtCancel.addActionListener(this);

        // JDialog to hold the two JPanels and set the layout
        this.setModal(true);
        this.setTitle(Constants.TITLE_COUNTRY + " - " + this.mode);
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

    // fill data to the country form from Country object (for update purpose)
    private void fillData(Country m_country) {
        // TODO: Roy - fill data
        int chkCountryID = m_country.getCountry_id();
        Country c = Country.getCountry(chkCountryID);
        jtfInput.setText(c.getCountry_name());
    }

    // Update DB record - for save button call
    private void updateCountry(Country m_country) {
        if (this.mode.equals(Constants.MODE_CREATE)) {
            Country.insertCountry(m_country);
        } else if (this.mode.equals(Constants.MODE_MODIFY)) {
            Country.updateCountry(m_country);
        }
    }

    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SetupCountryInput sri = new SetupCountryInput(new SetupCountryList(new TCC()));
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

        // Roy new add
        Object obj = e.getSource();
        if (obj == jbtCancel) {
            cancel();
        } else if (obj == jbtConfirm) {
            if (jtfInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a country name!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Country c = new Country();
                c.setCountry_id(this.country_id);
                c.setCountry_name(jtfInput.getText());
                updateCountry(c);
                this.parent.refreshCountryList(Country.getCountryList());
                cancel();
            }
        }
    }

}
