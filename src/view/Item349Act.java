package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.SC;
import toolkit.ComboBoxSearch;
import toolkit.PanelComboBox;
import toolkit.Utility;

/**
 * Created by Jordan on 2017/12/25.
 */

public class Item349Act extends Item3 implements ActionListener{
    public Item349Act(){
        List<ComboBoxSearch> searchList = new ArrayList<>();
        searchList.add(new ComboBoxSearch("ϵ��", Department.TABLE,Department.NAME));
        this.upper=new PanelComboBox(searchList);
        this.add(upper);
        this.add(lower);
        upper.buttonQuery.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql = "select S_prov as Province, count(*) as count from Student " +
                " where dept_id = (select Dept_id from Department where dept_name =  " + Utility.quote(upper.getSelected(0).toString())+
                ") group by S_prov;";
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
