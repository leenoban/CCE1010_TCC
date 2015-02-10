/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import to.Recipe;

/**
 *
 * @author AngusLipsey
 */
public class TCC extends JFrame implements ActionListener {
    
    private static int list_count;
    private static ArrayList recipeList;
    
    private static String title = "Personal Recipe";
    private JButton btn_exit = new JButton("Exit");
    
    public TCC() {
        super(title);
        this.buildGUI();
    }
    
    private void buildGUI() {
        // TODO: Angus - buildGUI()
        this.setSize(800, 600);
    }
    
    public void refreshRecipeList(ArrayList m_list) {
        this.recipeList = m_list;
        // TODO: Angus - refresh the recipe list
    }
    
    // create mode
    private void showRecipeInputFrame() {
        // TODO: Angus :: Joe - Call the recipe input frame
    }
    
    // edit mode
    private void showRecipeInputFrame(Recipe m_recipe) {
        // TODO: Angus :: Joe - Call the recipe input frame
    }
    
    private void showModifyLanguageFrame() {
        // TODO: Angus :: Roy - Call the language modify input frame
    }
    
    private void showRegionListFrame() {
        // TODO: Angus :: Benny - Call the region list frame
    }
    
    private void showMethodListFrame() {
        // TODO: Angus :: Benny - Call the method list frame
    }
    
    private void showIntervalListFrame() {
        // TODO: Angus :: Benny - Call the interval list frame
    }
    
    private void exitTCC() {
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO: main program enter point
        TCC tcc = new TCC();
        tcc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tcc.setLocationRelativeTo(null);
        tcc.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj==btn_exit) {
            exitTCC();
        }
    }
    
}
