package view;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import control.DBConnection;
import model.Course;
import model.SC;

/**
 * Created by Jordan on 2017/12/11.
 */

public final class Item341Act extends JPanel {
    JPanel upper,panelYear,panelCourse,panelResult;
    JButton buttonQuery;
    JLabel labelYear,labelCourse;
    JComboBox comboBoxYear,comboBoxCourse;
    JTable table;
    ResultSet resultSet=null;
    Vector<String> v1,v2;
    public Item341Act (){
        super();
        v1=new Vector<>();
        v2=new Vector<>();
        //prepareCombox();
        labelYear=new JLabel("学年");
        labelCourse=new JLabel("课程");

        upper=new JPanel();
        buttonQuery= new JButton("查询");
        comboBoxYear=new JComboBox(v1);
        comboBoxCourse=new JComboBox(v2);
        this.upper.setLayout(createLayout());

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(new JLabel("请输入需要查询的学年和课程名称"));
        this.add(this.upper);
        table=new JTable(3,3);
        this.add(this.table);

        this.setVisible(true);
    }

    /**
     * @return Return a group layout.
     */
    private LayoutManager createLayout(){
        GroupLayout layout=new GroupLayout(upper);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelYear).addComponent(labelCourse);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(comboBoxYear).addComponent(comboBoxCourse);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelYear).addComponent(comboBoxYear);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelCourse).addComponent(comboBoxCourse).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
    private void prepareCombox(){
        String sql1="select unique "+ SC.AYEAR+" from "+SC.TABLE;
        String sql2="select unique "+ Course.NAME+" from "+Course.TABLE;
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()){
                this.v1.add(resultSet.getString(1));
            }
            resultSet.close();
            resultSet=statement.executeQuery(sql2);
            while (resultSet.next()){
                this.v2.add(resultSet.getString(1));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
