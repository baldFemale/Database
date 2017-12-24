package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import toolkit.PanelComboBox;

/**
 * Created by Jordan on 2017/12/19.
 */

public class Item3 extends JPanel {
    public JPanel lower=new JPanel();
    public JScrollPane jsp;
    public PanelComboBox upper;
    public ResultSet rs;

    public Item3(){
        LayoutManager layout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(layout);
        lower.setAlignmentX((float) 0.5);
        lower.setMinimumSize(new Dimension(10,20));
        //add(upper);
        //add(lower);
    }
}
