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
 * Course where more than 2 students fail.
 */

public class Item345Act extends Item3 implements ActionListener{
    public Item345Act(){
        List<ComboBoxSearch> searchList = new ArrayList<>();
        searchList.add(new ComboBoxSearch("学年", SC.TABLE,SC.AYEAR));
        searchList.add(new ComboBoxSearch("系别", Department.TABLE,Department.NAME));
        this.upper=new PanelComboBox(searchList);
        this.add(upper);
        this.add(lower);
        upper.buttonQuery.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql="select C.C_name as course, count(*) as count from Course as C, SC where C.C_id = SC.C_id and SC.score < 60 and SC.Ayear = "
                +Utility.quote(upper.getSelected(0).toString())+
                " and SC.S_id in (select S_id from student as S where S.Dept_id in(select Dept_id from Department where Dept_name = "
                +Utility.quote(upper.getSelected(1).toString())+
                ")) group by SC.C_id, C.C_name having count(*) > 2";
        System.out.println(sql);
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
