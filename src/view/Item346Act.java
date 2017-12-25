package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import toolkit.PanelSearchInput;
import toolkit.Utility;

/**
 * Created by Jordan on 2017/12/25.
 */

public class Item346Act extends Item3 implements ActionListener{
    PanelSearchInput input;
    public Item346Act(){
        input=new PanelSearchInput("姓名","请输入学生姓名");
        this.add(input);
        this.add(lower);
        this.input.jb.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql="select s_id as ID, s_name as Name from Student as x where not exists (select * from SC as y where" +
                "  y.s_id = (select s_id from user1.Student where S_name = "
                +Utility.quote(this.input.jtf.getText().toString()) +
                ") and y.c_id not in ( select z.c_id from SC as z where z.s_id = x.s_id)";
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
