package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Util;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Department;
import model.SC;
import toolkit.ComboBoxSearch;
import toolkit.PanelComboBox;
//import toolkit.TestUnit;
import toolkit.Utility;

/**
 * Created by Jordan on 2017/12/19.
 */

public class Item342Act extends Item3 implements ActionListener{

    public Item342Act(){
        List<ComboBoxSearch> searchList = new ArrayList<>();
        ComboBoxSearch search = new ComboBoxSearch("ѧ��", SC.TABLE,SC.AYEAR);
        searchList.add(search);
        searchList.add(new ComboBoxSearch("ϵ��", Department.TABLE,Department.NAME));
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
        String sql="select S_name as Name, sum(SC.gpa * C.credit)/sum(C.credit) as GPA     from Student as S, SC, Course as C where " ;
        if(upper.getSelected(1)!=null)sql=sql +"      S.Dept_id = (select Dept_id from Department where Dept_name = "+ Utility.quote(upper.getSelected(1).toString()) ;
        if(upper.getSelected(0)!=null)sql=sql+
                ")  and SC.Ayear =  "+Utility.quote(upper.getSelected(0).toString()) ;
        sql=sql+
                "and S.S_id = SC.S_id   and C.C_id = SC.C_id  group by SC.S_id, S.S_name  order by GPA desc;";
        //rs= TestUnit.getTestRS();
        //jsp=Utility.getJSPfromResultSet(rs);
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
        //lower.setViewportView(new JTable(5,5));
        //lower.updateUI();
        //TODO ���where��������ѭ���ķ�װ��
    }
}
