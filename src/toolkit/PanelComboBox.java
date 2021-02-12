package toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Create a panel with a series of combo box and titled with an instruction.
 */

public class PanelComboBox extends JPanel {
    List<RowCombo> listElement=new ArrayList<RowCombo>();
    public JButton buttonQuery;
    List<GroupLayout.ParallelGroup> listVGroup = new ArrayList<>();
    JLabel instruction = new JLabel("请选择查询项目");

    /**
     * @param items A list of the combo of title, table and attr.
     */
    public PanelComboBox(List<ComboBoxSearch> items){
        super();
        buttonQuery=new JButton("查询");
        GroupLayout layout=new GroupLayout(this);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup();///.addComponent(instruction);//.addComponent(labelYear).addComponent(labelCourse);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(instruction);//.addComponent(comboBoxYear).addComponent(comboBoxCourse);
        //.addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addComponent(instruction);
        for (ComboBoxSearch comboItem:items) {
            JLabel label=new JLabel(comboItem.title);
            Vector vec=Utility.simpleUniqueQuery(comboItem.table,comboItem.attr);
            //vec.add(0,"");
            JComboBox comboBox=new JComboBox<String>(vec);
            comboBox.setSelectedIndex(0);
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

    /**
     * @param i the index of the combo box. start from 0.
     * @return an Object. need to be converted to String.
     */
    public Object getSelected(int i){
        return listElement.get(i).comboBox.getSelectedItem();
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
