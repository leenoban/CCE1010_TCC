/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import to.Recipe;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author AngusLipsey
 */
//public class RecipeInput extends JFrame {
public class RecipeInput extends JDialog implements ActionListener {

    private TCC parent;
    private String mode;
    // Joe new added
    JPanel panelNew = new JPanel();
    JPanel panelButton = new JPanel();
    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();
    JPanel panelRadioButton = new JPanel();
    JPanel panelOne = new JPanel();
    JPanel panelTop1 = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel panelTop2 = new JPanel();
    JPanel panelThree = new JPanel();
    JTabbedPane tbd1 = new JTabbedPane();
    JButton btnCancel = new JButton("Cancel");
    JButton btnSave = new JButton("Save");
    JLabel lblRecipeName = new JLabel("Recipe Name");
    JTextField txtRecipeName = new JTextField(8);
    JLabel lblCountry = new JLabel("Country");
    JComboBox cboCountry = new JComboBox();
    JLabel lblCookingMethod = new JLabel("Cooking Method");
    JComboBox cboCookingMethod = new JComboBox();
    JLabel lblTimeRequire = new JLabel("Time Require");
    JComboBox cboTimeRequire = new JComboBox();
    JLabel lblDifficulties = new JLabel("Difficulties");
    JRadioButton radio5 = new JRadioButton("\t5");
    JRadioButton radio4 = new JRadioButton("\t4");
    JRadioButton radio3 = new JRadioButton("\t3");
    JRadioButton radio2 = new JRadioButton("\t2");
    JRadioButton radio1 = new JRadioButton("\t1");
    ButtonGroup radioButtonGroup = new ButtonGroup();
    JTextArea txtMaterial = new JTextArea();
    JTextArea txtStep = new JTextArea();
    JScrollPane scrollMaterial = new JScrollPane(txtMaterial, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane scrollStep = new JScrollPane(txtStep, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    int chkRecipeID;
    //Joe new add

    public RecipeInput(TCC m_parent) {
        this.parent = m_parent;
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }

    public RecipeInput(Recipe m_recipe, TCC m_parent) { //System.out.println("m_recipe.getRecipe_id(): " + m_recipe.getRecipe_id());
        this.parent = m_parent;
        this.mode = Constants.MODE_MODIFY;
        buildGUI();
        fillData(m_recipe);
    }

    private void buildGUI() {
        // TODO: Joe - buildGUI
        // Joe new add
        //panel to hold tabs and button
        panelButton.setLayout(new GridLayout(1, 2));
        panelButton.add(btnCancel);
        panelButton.add(btnSave);
        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);
        //Tab 1 - top panel

        panelLeft.setLayout(new GridLayout(5, 0));
        panelLeft.add(lblRecipeName);
        panelLeft.add(lblCountry);
        panelLeft.add(lblCookingMethod);
        panelLeft.add(lblTimeRequire);
        panelLeft.add(lblDifficulties);

        panelRight.setLayout(new GridLayout(5, 0));
        panelRight.add(txtRecipeName);
        panelRight.add(cboCountry);
        panelRight.add(cboCookingMethod);
        panelRight.add(cboTimeRequire);
           
        ArrayList list = to.Country.getCountryList();
        for(int i=0; i<list.size(); i++) {
            to.Country c = (to.Country)list.get(i);
            cboCountry.addItem(c.getCountry_name());
        }
        
        ArrayList mlist = to.Method.getMethodList();
        for(int i=0; i<mlist.size(); i++) {
            to.Method m = (to.Method)mlist.get(i);
            cboCookingMethod.addItem(m.getMethod_name());
        }
        
        ArrayList IntervalList = to.Interval.getIntervalList();
        for(int i=0; i<IntervalList.size(); i++) {
            to.Interval t = (to.Interval)IntervalList.get(i);
            cboTimeRequire.addItem(t.getInterval());
        }
            //Tab 1 - panel to hold radio button with different layout  
        panelRadioButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        radioButtonGroup.add(radio5);
        radioButtonGroup.add(radio4);
        radioButtonGroup.add(radio3);
        radioButtonGroup.add(radio2);
        radioButtonGroup.add(radio1);
        panelRadioButton.add(radio5);
        panelRadioButton.add(radio4);
        panelRadioButton.add(radio3);
        panelRadioButton.add(radio2);
        panelRadioButton.add(radio1);
        radio1.setSelected(true);
        
        //create a layout to hold the label,combox,textfield and radio button panel for Tab1
        panelOne.setLayout(new GridLayout(1, 1));
        panelOne.add(panelLeft);
        panelOne.add(panelRight);
        panelRight.add(panelRadioButton);

        //Tab 2 - create text area
        panelTop1.setLayout(new GridLayout(1, 0));
        txtMaterial.setLineWrap(true);
        txtMaterial.setWrapStyleWord(true);
        panelTop1.add(scrollMaterial);

        //create a layout to hold the text area for tab2
        panelTwo.setLayout(new BorderLayout(0, 0));
        panelTwo.add(panelTop1, BorderLayout.CENTER);
        panelTwo.add(scrollMaterial);

        //Tab3 - create text area          
        panelTop2.setLayout(new BorderLayout(1, 0));
        txtStep.setLineWrap(true);
        txtStep.setWrapStyleWord(true);
        panelTop2.add(scrollStep);

        //create a layout to hold the text area for tab2
        panelThree.setLayout(new BorderLayout(5, 10));
        panelThree.add(panelTop2, BorderLayout.CENTER);

        //Create tabs
        tbd1.addTab("Basic Information", panelOne);
        tbd1.addTab("Material", panelTwo);
        tbd1.addTab("Steps", panelThree);

        //Panel to hold the tabs and button
        add(tbd1, BorderLayout.NORTH);
        add(panelButton, BorderLayout.SOUTH);
        
        //Joe new add    

        this.setModal(true);
        this.setTitle(Constants.TITLE_RECIPE + " - " + this.mode);
        this.setSize(800, 600);
        pack();
    }

    // fill data to the recipe form from Recipe object (for update purpose)
    private void fillData(Recipe m_recipe) {
        // TODO: Joe - fill data
        //Joe new add

        chkRecipeID = m_recipe.getRecipe_id();
        Recipe r = Recipe.getRecipe(chkRecipeID);

        int chkLevel = r.getLevel();
        txtRecipeName.setText(r.getRecipe_name());
        cboCountry.addItem(r.getCountry().getCountry_name());
        cboCookingMethod.addItem(r.getMethod().getMethod_name());
        cboTimeRequire.addItem(r.getInterval().getInterval());

        switch (chkLevel) {
            case 5:
                radio5.setSelected(true);
                break;
            case 4:
                radio4.setSelected(true);
                break;
            case 3:
                radio3.setSelected(true);
                break;
            case 2:
                radio2.setSelected(true);
                break;
            case 1:
                radio1.setSelected(true);
                break;
            default:
                break;
        }

        txtMaterial.setText(r.getMaterial());
        txtStep.setText(r.getSteps());
        //Joe new add
    }

    // Update DB record - for save button call
    private void updateRecipe(Recipe m_recipe) {
        if (this.mode.equals(Constants.MODE_CREATE)) {
            Recipe.insertRecipe(m_recipe);
        } else if (this.mode.equals(Constants.MODE_MODIFY)) {
            Recipe.updateRecipe(m_recipe);
        }
        parent.refreshRecipeList(Recipe.getRecipeList());
        this.cancel();
    }

    // for cancel button call
    private void cancel() {
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) {
        RecipeInput ri = new RecipeInput(null);
        ri.setLocationRelativeTo(null);
        ri.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ri.setVisible(true);

    }
public  void update(){
      if (mode.equals(Constants.MODE_CREATE)) {
                
                Recipe r = new Recipe();

                to.Country c = new to.Country();
                to.Method m = new to.Method();
                to.Interval i = new to.Interval();

                String txtR = txtRecipeName.getText();
                String txtM = txtMaterial.getText();
                String txtS = txtStep.getText();

                r.setRecipe_id(chkRecipeID);
                if ( txtR.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "Please enter a Recipe Name","Error",JOptionPane.ERROR_MESSAGE);
                        }
                else {
                
                r.setRecipe_name(txtR);
            
                int setCountryID = cboCountry.getSelectedIndex();
                setCountryID = setCountryID +1;
                c.setCountry_id(setCountryID);  
                r.setCountry(c);
                
                int setMethodID = cboCookingMethod.getSelectedIndex();
                setMethodID = setMethodID +1;
                m.setMethod_id(setMethodID);   
                r.setMethod(m);
                
                int setIntervalID = cboTimeRequire.getSelectedIndex();
                setIntervalID = setIntervalID +1;
                i.setInterval_id(setIntervalID);  
                r.setInterval(i);

                if (radio1.isSelected()) {
                    r.setLevel(1);
                } else if (radio2.isSelected()) {
                    r.setLevel(2);
                } else if (radio3.isSelected()) {
                    r.setLevel(3);
                } else if (radio4.isSelected()) {
                    r.setLevel(4);
                } else if (radio5.isSelected()) {
                    r.setLevel(5);
                }

                r.setMaterial(txtM);
                r.setSteps(txtS);

                updateRecipe(r);
                
                }   
    }
                
                
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        // joe new add
        Object obj = e.getSource();
        if (obj == btnCancel) {

            cancel();
        } else if (obj == btnSave) {
                    //Add
            
            if (mode.equals(Constants.MODE_CREATE)) {
                update();
            } //Modify Recipe
            else if (mode.equals(Constants.MODE_MODIFY)) {
            update();

            }
        //Joe new add
        }
    }
}
