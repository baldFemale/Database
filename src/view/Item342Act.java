package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Department;
import model.SC;
import toolkit.ComboBoxSearch;
import toolkit.PanelComboBox;
import toolkit.TestUnit;
import toolkit.Utility;

/**
 * Created by Jordan on 2017/12/19.
 */

public class Item342Act extends Item3 implements ActionListener{

    public Item342Act(){
        List<ComboBoxSearch> searchList = new ArrayList<>();
        ComboBoxSearch search = new ComboBoxSearch("学年", SC.TABLE,SC.AYEAR);
        searchList.add(search);
        searchList.add(new ComboBoxSearch("系别", Department.TABLE,Department.NAME));
        //ComboBoxSearch[] listSearch=new ComboBoxSearch[]{new ComboBoxSearch("id","ta","dd")};
        this.upper=new PanelComboBox(searchList);
        //lower.updateUI();
        this.add(upper);
        this.add(lower);
        //this.add(new JTable(3,5));
        this.setVisible(true);
        upper.buttonQuery.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //lower.removeAll();
        String sql="select S_name as Name, sum(SC.gpa * C.score)/sum(C.score) as GPA, rank() over(order by GPA)" +
                "from Student as S, SC, Course as C" +
                "where ";
        if(upper.getSelected(1)!=null)sql=sql +"S.Dept_id = (select Dept_id from Department where Dept_name = '"+upper.getSelected(1).toString()+"') and ";
        if(upper.getSelected(0)!=null)sql=sql+
                "SC.Ayear = "+Utility.quote(upper.getSelected(0).toString()) +" and ";
        sql=sql+
                "and S.S_id = SC.S_id" +
                "and C.C_id = SC.C_id" +
                "group by SC.S_id" +
                "order by GPA";
        //rs= TestUnit.getTestRS();
        //jsp=Utility.getJSPfromResultSet(rs);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
        //lower.setViewportView(new JTable(5,5));
        //lower.updateUI();
        //TODO 添加where后条件的循环的封装。
    }
}
