package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import toolkit.Utility;

/**
 * Created by Jordan on 2017/12/25.
 */

public class Item347Act extends Item3 implements ActionListener{
    JLabel instruct;
    JButton jb;
    public Item347Act(){
        instruct=new JLabel("最牛学生");
        jb=new JButton("查询");
        this.add(instruct);
        this.add(jb);
        this.add(lower);
        jb.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql="select top 5 SC.S_id as ID, S.s_name as Name, " +
                "sum(SC.gpa * Course.credit)/sum(Course.credit) as gpa " +
                "from Student as S, SC, Course where S.S_id = SC.S_id and " +
                "Course.C_id = SC.C_id group by SC.S_id, S.S_name having min(SC.score) >= 60 order by gpa desc;";
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
