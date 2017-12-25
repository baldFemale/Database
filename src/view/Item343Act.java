package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.DBConnection;
import toolkit.Table;

/**
 * Created by Jordan on 2017/12/24.
 */

public class Item343Act extends Item3 {
    JLabel top=new JLabel("请输入所查询的分数下限");
    JLabel title;
    JTextField input;
    JButton jb=new JButton("查询");
    JPanel mid=new JPanel();
    public Item343Act(){
        top.setHorizontalAlignment(SwingConstants.LEFT);
       this.add(top);
       title=new JLabel("分数");
       input=new JTextField();
       mid.add(title);
       mid.add(input);
       mid.add(jb);
       this.add(mid);
       this.add(lower);
       jb.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               int min = checkInput(input);
               if(min<0) JOptionPane.showMessageDialog(null,"Invalid input");
               else {
                   getSscore(min);
               }
           }
       });
    }
    private int checkInput(JTextField jtf){
        int x = Integer.valueOf(jtf.getText());
        if(x<0||x>100)return -1;
        return x;
    }
    private void getSscore(int min){
        String sql="select S.S_id as ID, S.S_name as Name " +
                "from Student as S " +
                "where S.S_id in " +
                "(select S_id from SC " +
                "group by S_id " +
                "having min(score) > "+min+")";
        Statement statement= null;
        try {
            statement = DBConnection.getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            Table t=new Table(resultSet);
            this.jsp=new JScrollPane(t.jt);
            lower.removeAll();
            lower.add(jsp);
            lower.updateUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
