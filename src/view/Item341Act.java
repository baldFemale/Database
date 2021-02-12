package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Department;
import model.SC;
import model.Student;
import toolkit.ComboBoxSearch;
import toolkit.PanelComboBox;
import toolkit.Table;
import toolkit.Utility;

public final class Item341Act extends Item3 implements  ActionListener{
    public Item341Act (){
        List<ComboBoxSearch> searchList = new ArrayList<>();
        searchList.add(new ComboBoxSearch("学年", SC.TABLE,SC.AYEAR));
        searchList.add(new ComboBoxSearch("课程", Course.TABLE,Course.NAME));
        this.upper=new PanelComboBox(searchList);
        this.add(upper);
        this.add(lower);
        upper.buttonQuery.addActionListener(this);
        //this.setFont(new Font("????",Font.ITALIC,30));//TODo ?????????????????????????о??С?
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql="select top 5 S.S_id, S.S_name, SC.score from SC, Student as S where SC.S_id = S.S_id ";
        if(upper.getSelected(0)!=null){
            sql=sql.concat(" and SC."+SC.AYEAR+" = "+upper.getSelected(0).toString());
        }
        if(upper.getSelected(1)!=null)
            sql=sql.concat(" and SC."+SC.C_ID+" = ( select C_id from Course where C_name = '"+upper.getSelected(1).toString()+"')");//TODO SQL????д??
        sql=sql+" order by SC.score desc;";
        System.out.println(sql);
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}

