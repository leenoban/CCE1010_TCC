/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import javax.swing.JFrame;
import to.Recipe;

/**
 *
 * @author AngusLipsey
 */
public class RecipeInput extends JFrame {
    
    private static String title = "Personal Recipe - Add / Modify";
    private String mode;
    
    public RecipeInput() {
        super(title);
        this.mode = Constants.MODE_CREATE;
        buildGUI();
    }
    
    public RecipeInput(Recipe m_recipe) {
        super(title);
        this.mode = Constants.MODE_MODIFY;
        buildGUI();
        fillData(m_recipe);
    }
    
    private void buildGUI() {
        // TODO: Joe - build GUI
        this.setSize(800, 600);
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
        RecipeInput ri = new RecipeInput();
        ri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ri.setLocationRelativeTo(null);
        ri.setVisible(true);
    }
    
}
