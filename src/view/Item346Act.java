package view;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.DBConnection;
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
        String sName=Utility.quote(this.input.jtf.getText().toString());
        try{
            Statement st=DBConnection.getConnection().createStatement();
            ResultSet rs=st.executeQuery("select * from Student where S_name = "+sName);
            if(!rs.next()){//指针一开始位于第一行之前，若下一个无内容则返回false。
                Utility.reportErrorEmptyTable();
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        String sql="select s_id as ID, s_name as Name from Student as x where not exists (select * from SC as y where" +
                "  y.s_id in (select s_id from user1.Student where S_name = "
                + sName +//TODO 输错内容的时候仍然有结果。
                ") and y.c_id not in ( select z.c_id from SC as z where z.s_id = x.s_id))";
        jsp = Utility.jspFromSQL(sql);//TODO 尝试下SQL中的if语句。
        lower.removeAll();
        lower.add(jsp);
        lower.updateUI();
    }
}
