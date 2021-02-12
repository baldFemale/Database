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

public class Item344Act extends Item3 implements ActionListener{
    public Item344Act(){
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
        String sql="select S.S_name as Name, S.S_id as ID, count(*) as count from Student as S, user1.SC as SC where S.S_id = SC.S_id";
        if(upper.getSelected(0)!=null)sql=sql+ " and SC.score < 60 and Ayear = "+Utility.quote(upper.getSelected(0).toString());
        if(upper.getSelected(1)!=null)sql=sql+" and S.Dept_id = (select Dept_id from Department where Dept_name = "+Utility.quote(upper.getSelected(1).toString()) ;
        sql=sql+
                ") group by S.S_id, S.S_name having min(Score)<60";
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
