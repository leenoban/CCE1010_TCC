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
public class SetupMethodInput extends JDialog implements ActionListener {

    private SetupMethodList parent;
    private String mode;
    private int method_id;

    JLabel jlblMethodName = new JLabel(Constants.LBL_METHOD + " Name");
    JTextField jtfInput = new JTextField("", 25);
    JButton jbtConfirm = new JButton(Constants.LBL_BTN_CONFIRM);
    JButton jbtCancel = new JButton(Constants.LBL_BTN_CANCEL);
    JPanel pBase = new JPanel();
    JPanel pContent = new JPanel();
    JPanel pButtons = new JPanel();

    public SetupMethodInput(SetupMethodList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }

    public SetupMethodInput(Method m_method, SetupMethodList m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_MODIFY;
        this.method_id = m_method.getMethod_id();
        buildGUI();
        fillData(m_method);
    }

    private void buildGUI() {
        // TODO: Roy - build GUI

        // One JPanel to hold the JLabel and JTextField
        pContent.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.BASELINE_TRAILING;
        grid.gridx = 0;
        grid.gridy = 0;

        pContent.add(jlblMethodName, grid);
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
        this.setTitle(Constants.TITLE_METHOD + " - " + this.mode);
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

    // fill data to the method form from Method object (for update purpose)
    private void fillData(Method m_method) {
        // TODO: Roy - fill data
        int chkMethodID = m_method.getMethod_id();
        Method m = Method.getMethod(chkMethodID);
        jtfInput.setText(m.getMethod_name());
    }

    // Update DB record - for save button call
    private void updateMethod(Method m_method) {
        if (this.mode.equals(Constants.MODE_CREATE)) {
            Method.insertMethod(m_method);
        } else if (this.mode.equals(Constants.MODE_MODIFY)) {
            Method.updateMethod(m_method);
        }
    }

    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        SetupMethodInput smi = new SetupMethodInput(new SetupMethodList(new TCC()));
        smi.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        smi.setLocationRelativeTo(null);
        smi.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == jbtCancel) {
            cancel();
        } else if (obj == jbtConfirm) {
            if (jtfInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a method name!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Method m = new Method();
                m.setMethod_id(this.method_id);
                m.setMethod_name(jtfInput.getText());
                updateMethod(m);
                this.parent.refreshMethodList(Method.getMethodList());
                cancel();
            }
        }

    }

}
