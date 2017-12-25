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

public class Item348Act extends Item3 implements ActionListener{
    JLabel instruct;
    JButton jb;
    public Item348Act(){
        instruct=new JLabel("查询最强学生五人");
        jb=new JButton("查询");
        this.add(instruct);
        this.add(jb);
        this.add(lower);
        jb.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql="select top 5 S.S_id as ID, S_name as Name, " +
                "sum(SC.gpa * Course.credit)/sum(Course.credit) as gpa " +
                "from Student as S, SC, Course group by SC.S_id having min(SC.score) >= 60 ";
        jsp = Utility.jspFromSQL(sql);
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
