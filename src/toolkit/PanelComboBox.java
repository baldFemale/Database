package toolkit;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Jordan on 2017/12/18.
 */

public class PanelComboBox extends JPanel {
    List<RowCombo> listElement=new ArrayList<RowCombo>();
    JButton buttonQuery;
    List<GroupLayout.ParallelGroup> listVGroup = new ArrayList<>();

    public PanelComboBox(List<ComboBoxSearch> items){
        super();
        buttonQuery=new JButton("查询");
        GroupLayout layout=new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup();//.addComponent(labelYear).addComponent(labelCourse);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup();//.addComponent(comboBoxYear).addComponent(comboBoxCourse);
        //.addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
        for (ComboBoxSearch comboItem:items) {
            JLabel label=new JLabel(comboItem.title);
            JComboBox comboBox=new JComboBox<String>(Utility.simpleUniqueQuery(comboItem.table,comboItem.attr));
            listElement.add(new RowCombo(label,comboBox));
            hGroup1.addComponent(label);
            hGroup2.addComponent(comboBox);
            GroupLayout.ParallelGroup vGroupItem = layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label).addComponent(comboBox);
            listVGroup.add(vGroupItem);
        }
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);
        listVGroup.get(listVGroup.size()-1).addComponent(buttonQuery);
        for(GroupLayout.ParallelGroup vg:listVGroup){
            vGroup.addGroup(vg);
        }
        layout.setVerticalGroup(vGroup);

        this.setLayout(layout);
    }
    public class ComboBoxSearch {
        String title;
        String table;
        String attr;

        /**
         * @param title the content of the JLabel
         * @param table The table to select item from.
         * @param attr The attribute to select.
         */
        public ComboBoxSearch(String title, String table, String attr){
            this.title=title;
            this.table=table;
            this.attr=attr;
        }
    }

    /**
     * The label and a combo box.
     */
    class RowCombo{
        JLabel label;
        JComboBox comboBox;
        public RowCombo(JLabel label,JComboBox comboBox){
            this.label=label;this.comboBox=comboBox;
        }
    }
}
