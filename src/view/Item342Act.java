package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import toolkit.ComboBoxSearch;
import toolkit.PanelComboBox;

/**
 * Created by Jordan on 2017/12/19.
 */

public class Item342Act extends Item3 implements ActionListener{

    public Item342Act(){
        List<ComboBoxSearch> searchList = new ArrayList<>();
        ComboBoxSearch search = new ComboBoxSearch("id","ta","dd");
        searchList.add(search);
        this.upper=new PanelComboBox(searchList);
        this.lower=new JScrollPane();
        lower.add(new JTable(3,5));
        this.add(upper);
        //this.add(lower);
        this.add(new JTable(3,5));
        this.setVisible(true);
        upper.buttonQuery.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        lower.add(new JTable(3,5));
        this.updateUI();
    }
}
