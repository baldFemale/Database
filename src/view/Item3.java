package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import toolkit.PanelComboBox;

/**
 * Created by Jordan on 2017/12/19.
 */

public class Item3 extends JPanel {
    public JScrollPane lower;
    public PanelComboBox upper;

    public Item3(){
        super(new GridLayout(2,1));

    }
}
