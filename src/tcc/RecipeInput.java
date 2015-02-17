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

/**
 *
 * @author AngusLipsey
 */
//public class RecipeInput extends JFrame {
public class RecipeInput extends JDialog implements ActionListener {
    
    private TCC parent;
    private String mode;
    
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
        // TODO: Joe - build GUI
        this.setModal(true);
        this.setTitle(Constants.TITLE_RECIPE + " - " + this.mode);
        this.setSize(800, 600);
        //pack();
    }
    
    // fill data to the recipe form from Recipe object (for update purpose)
    private void fillData(Recipe m_recipe) {
        // TODO: Joe - fill data
    }
    
    // Update DB record - for save button call
    private void updateRecipe(Recipe m_recipe) {
        if(this.mode.equals(Constants.MODE_CREATE)) {
            Recipe.insertRecipe(m_recipe);
        } else if(this.mode.equals(Constants.MODE_MODIFY)) {
            Recipe.updateRecipe(m_recipe);
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }
    
}
