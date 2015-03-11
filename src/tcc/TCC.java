/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import to.Recipe;

/**
 *
 * @author AngusLipsey
 */
public class TCC extends JFrame implements ActionListener {
   
    private JPanel pl_top = new JPanel();
    private JPanel pl_top_center = new JPanel();
    private ImageIcon logo = new ImageIcon();
    private JTextField txt_search = new JTextField(20);
    private JButton btn_search = new JButton(Constants.LBL_BTN_SEARCH);
    private JPanel pl_top_right = new JPanel();
    private JLabel lbl_count = new JLabel();
    
    private JPanel pl_middle = new JPanel();
    private JPanel pl_middle_center = new JPanel();
    private JTable tbl_recipe;
    private JPanel pl_middle_right = new JPanel();
    private JLabel lbl_setting = new JLabel(Constants.LBL_SETTING);
    private JButton btn_country = new JButton(Constants.LBL_COUNTRY);
    private JButton btn_method = new JButton(Constants.LBL_METHOD);
    private JButton btn_interval = new JButton(Constants.LBL_INTERVAL);
    
    private JPanel pl_bottom = new JPanel();
    private JPanel pl_bottom_center = new JPanel();
    private JButton btn_add = new JButton(Constants.LBL_BTN_ADD);
    private JButton btn_modify = new JButton(Constants.LBL_BTN_MODIFY);
    private JButton btn_delete = new JButton(Constants.LBL_BTN_DELETE);
    private JPanel pl_bottom_right = new JPanel();
    private JButton btn_exit = new JButton(Constants.LBL_BTN_EXIT);
    
    TccTableModel model;
    
    public TCC() {
        super(Constants.TITLE_RECIPE);
        this.buildGUI();
        this.addListenerToObject();
    }
    
    private void addListenerToObject() {
        
        txt_search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                String s = txt_search.getText();
                for(int i=0; i<Constants.SQL_INVALID_CHAR.length; i++) {
                    if(ke.getKeyChar()==Constants.SQL_INVALID_CHAR[i]) {
                        ke.consume();
                        break;
                    }
                }
            }
        });
        
        btn_search.addActionListener(this);
        
        tbl_recipe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int selectedRowIndex = target.getSelectedRow();
                    //int selectedColumnIndex = target.getSelectedColumn(); //System.out.println("selected: (" + selectedRowIndex + "),(" + selectedColumnIndex + ")");
                    //Object selectedObject = (Object) tbl_recipe.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
                    int recipe_id = Integer.parseInt(((Object)tbl_recipe.getModel().getValueAt(selectedRowIndex, 1)).toString());
                    //System.out.println("selected recipe_id: " + recipe_id);
                    
                    if(recipe_id!=-1) {
                        Recipe recipe = new Recipe();
                        recipe.setRecipe_id(recipe_id);
                        showRecipeInputFrame(recipe);
                    }
                }
            }
        });
        
        btn_country.addActionListener(this);
        btn_method.addActionListener(this);
        btn_interval.addActionListener(this);
        btn_add.addActionListener(this);
        btn_modify.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_exit.addActionListener(this);
    }
    
    private void buildGUI() {
        // Angus - buildGUI()
        this.setSize(800, 600);
        
        pl_top.setLayout(new BorderLayout());
        pl_top.setBorder(new TitledBorder("Search"));
        pl_top_center.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        BufferedImage bi = null;
        
        try {
            bi = ImageIO.read(new File("images/logo_28_28.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lbl_icon = new JLabel(new ImageIcon(bi));
        pl_top_center.add(lbl_icon);
        
        pl_top_center.add(txt_search);
        pl_top_center.add(btn_search);
        pl_top.add(pl_top_center, BorderLayout.CENTER);
        pl_top_right.add(lbl_count);
        pl_top.add(pl_top_right, BorderLayout.EAST);
        add(pl_top, BorderLayout.NORTH);
        
        pl_middle.setLayout(new BorderLayout());
        pl_middle_center.setBorder(new TitledBorder("Recipe List"));
        
        tbl_recipe = new JTable();
        tbl_recipe.setGridColor(Color.LIGHT_GRAY);
        
        refreshRecipeList(Recipe.getRecipeList());
        
        tbl_recipe.setAutoCreateRowSorter(true);
        JScrollPane jsp = new JScrollPane(tbl_recipe);
        jsp.setPreferredSize(new Dimension(800, 300));
        tbl_recipe.setFillsViewportHeight(true);
        pl_middle_center.add(jsp);
        pl_middle.add(pl_middle_center, BorderLayout.CENTER);
        
        pl_middle_right.setLayout(new BoxLayout(pl_middle_right, BoxLayout.PAGE_AXIS));
        pl_middle_right.setBorder(new TitledBorder("Setting"));
        pl_middle_right.add(btn_country);
        pl_middle_right.add(btn_method);
        pl_middle_right.add(btn_interval);
        pl_middle.add(pl_middle_right, BorderLayout.EAST);
        add(pl_middle, BorderLayout.CENTER);
        
        pl_bottom.setLayout(new BorderLayout());
        pl_bottom_center.setLayout(new FlowLayout(FlowLayout.LEFT));
        pl_bottom_center.add(btn_add);
        pl_bottom_center.add(btn_modify);
        pl_bottom_center.add(btn_delete);
        pl_bottom.add(pl_bottom_center, BorderLayout.CENTER);
        pl_bottom.add(pl_bottom_right, BorderLayout.EAST);
        pl_bottom_right.add(btn_exit);
        add(pl_bottom, BorderLayout.SOUTH);
        pack();
    }
    
    public void refreshRecipeList(ArrayList m_list) { //System.out.println("m_list.size(): " + m_list.size());
    
        if(m_list.size()>0) {
            Object[][] tbl_data = new Object[m_list.size()][TccTableModel.TBL_RECIPE.length()+1];
            for(int i=0; i<m_list.size(); i++) {
                Recipe r = (Recipe)m_list.get(i);
                tbl_data[i][0] = Boolean.FALSE;
                tbl_data[i][1] = r.getRecipe_id();
                tbl_data[i][2] = r.getRecipe_name();
                tbl_data[i][3] = r.getCountry().getCountry_name();
                tbl_data[i][4] = r.getMethod().getMethod_name();
                tbl_data[i][5] = r.getInterval().getInterval() + " " + r.getInterval().getUnit();
                int level = r.getLevel();
                
                String img_path = null;
                try {
                    img_path = new File(".").getCanonicalPath() + "/images/";
                    img_path += "star_" + level + "_80_18.png";
                } catch(IOException e) {
                    e.printStackTrace();
                }
                ImageIcon ic = new ImageIcon(img_path);
                tbl_data[i][6] = ic;
            }
            model = new TccTableModel(TccTableModel.TBL_RECIPE, tbl_data);
            
        } else {
            model = new TccTableModel(TccTableModel.TBL_RECIPE, TccTableModel.NO_REC_FOUND_RECIPE);
            tbl_recipe.setModel(model);
        }
        
        tbl_recipe.setModel(model);
        model.fireTableStructureChanged();
        tbl_recipe.setGridColor(Color.LIGHT_GRAY);
        TableCellRenderer rendererFromHeader = tbl_recipe.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<tbl_recipe.getColumnCount(); i++) {

            if(i!=0 && i!=2) tbl_recipe.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

            if(i==0) {
                tbl_recipe.getColumnModel().getColumn(i).setPreferredWidth(25);
            } else if(i==1) {
                tbl_recipe.getColumnModel().getColumn(i).setMinWidth(0);
                tbl_recipe.getColumnModel().getColumn(i).setMaxWidth(0);
            } else if(i==2) {
                tbl_recipe.getColumnModel().getColumn(i).setPreferredWidth(280);
            } else if(i==3) {
                tbl_recipe.getColumnModel().getColumn(i).setPreferredWidth(120);
            } else if(i==4) {
                tbl_recipe.getColumnModel().getColumn(i).setPreferredWidth(120);
            } else if(i==5) {
                tbl_recipe.getColumnModel().getColumn(i).setPreferredWidth(120);
            } else if(i==6) {
                tbl_recipe.getColumnModel().getColumn(i).setPreferredWidth(120);
                tbl_recipe.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
            }

        }
        
        // refresh counter label
        lbl_count.setText("Total " + Recipe.getRecipeList().size() + " piece(s)");
    }
    
    // create mode
    private void showRecipeInputFrame() {
        // Angus :: Joe - Call the recipe input frame
        RecipeInput r = new RecipeInput(this);
        r.setLocationRelativeTo(null);
        r.setVisible(true);
    }
    
    // edit mode
    private void showRecipeInputFrame(Recipe m_recipe) {
        // Angus :: Joe - Call the recipe input frame
        System.out.println("Pass Recipe(id:" + m_recipe.getRecipe_id() + ") and RecipeInput ......");
        
        RecipeInput ri = new RecipeInput(m_recipe, this);
        ri.setLocationRelativeTo(null);
        ri.setVisible(true);
    }
    
    private void showCountryListFrame() {
        // Angus :: Benny - Call the country list frame
        System.out.println("Show SetupCountryInput ......");
        
        SetupCountryList scl = new SetupCountryList(this);
        scl.setLocationRelativeTo(null);
        scl.setVisible(true);
    }
    
    private void showMethodListFrame() {
        // Angus :: Benny - Call the method list frame
        System.out.println("Show SetupMethodInput ......");
        
        SetupMethodList sml = new SetupMethodList(this);
        sml.setLocationRelativeTo(null);
        sml.setVisible(true);
    }
    
    private void showIntervalListFrame() {
        // Angus :: Benny - Call the interval list frame
        System.out.println("Show SetupIntervalInput ......");
        
        SetupIntervalList sil = new SetupIntervalList(this);
        sil.setLocationRelativeTo(null);
        sil.setVisible(true);
    }
    
    private void deleteRecipes(ArrayList m_list) {
        
        int n = JOptionPane.showConfirmDialog(this, Constants.CONFIRM_DELETE_ITEM, Constants.TITLE_CONFIRM_DELETE, JOptionPane.YES_NO_OPTION);
        if(n==JOptionPane.YES_OPTION) {
            for(int i=0; i<m_list.size(); i++) {
            int recipe_id = ((Recipe)m_list.get(i)).getRecipe_id();
                //System.out.println("delete recipe_id: " + recipe_id);
                Recipe.deleteRecipe(recipe_id);
            }
        }
        
        // refresh table
        refreshRecipeList(Recipe.getRecipeList());
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
        if(obj==btn_search) {
            //System.out.println(Recipe.getRecipeList(txt_search.getText()).size());
            refreshRecipeList(Recipe.getRecipeList(txt_search.getText().trim()));
        } else if(obj==btn_add) {
           showRecipeInputFrame();
        } else if(obj==btn_modify) {
            if(getTableCheckedCount()==0 || getTableCheckedCount()>1) {
                JOptionPane.showMessageDialog(this, Constants.NOT_ONE_ITEM_SELECTED);
            } else {
                // get selected item
                Recipe r = new Recipe();
                for(int i=0; i<tbl_recipe.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean)tbl_recipe.getModel().getValueAt(i, 0);
                    if(checked) {
                        r.setRecipe_id((int)tbl_recipe.getModel().getValueAt(i, 1));
                    }
                }
                RecipeInput ri = new RecipeInput(r, this);
                ri.setLocationRelativeTo(null);
                ri.setVisible(true);
            }
        } else if(obj==btn_delete) {
            if(getTableCheckedCount()<1) {
                JOptionPane.showMessageDialog(this, Constants.NO_ITEM_SELECTED);
            } else {
                // get selected item
                ArrayList list = new ArrayList();
                for(int i=0; i<tbl_recipe.getModel().getRowCount(); i++) {
                    boolean checked = (Boolean)tbl_recipe.getModel().getValueAt(i, 0);
                    if(checked) {
                        Recipe r = new Recipe();
                        r.setRecipe_id((int)tbl_recipe.getModel().getValueAt(i, 1));
                        list.add(r);
                    }
                }
                deleteRecipes(list);
            }
        } else if(obj==btn_country) {
            showCountryListFrame();
        } else if(obj==btn_method) {
            showMethodListFrame();
        } else if(obj==btn_interval) {
            showIntervalListFrame();
        } else if(obj==btn_exit) {
            exitTCC();
        }
    }
    
    private int getTableCheckedCount() {
        int count = 0;
        for(int i=0; i<tbl_recipe.getModel().getRowCount(); i++) {
            boolean checked = (Boolean)tbl_recipe.getModel().getValueAt(i, 0);
            if(checked) count++;
        }
        return count;
    }
    
}

class ImageRenderer extends DefaultTableCellRenderer {
 
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row, int column)
    {
        JLabel label = new JLabel();
 
        if (value!=null) {
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIcon(new ImageIcon(value.toString()));
        }
 
        return label;
    }
}