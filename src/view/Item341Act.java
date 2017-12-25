package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;

import control.DBConnection;
import model.Course;
import model.SC;
import model.Student;
import toolkit.Table;
import toolkit.Utility;

/**
 * ����ĳ�ſγ�ĳ����߷ֵ���λͬѧ��
 */

public final class Item341Act extends JPanel implements  ActionListener{
    private JPanel upper,top;
    private JButton buttonQuery;
    private JLabel labelYear,labelCourse,labelHeading;
    private JComboBox comboBoxYear,comboBoxCourse;
    private JTable table;
    private ResultSet resultSet=null;
    private Vector<String> v1,v2;
    public Item341Act (){
        super();
        v1=new Vector<>();
        v2=new Vector<>();//TODO table not shown.
        //prepareCombox();
        labelYear=new JLabel("ѧ��");
        labelCourse=new JLabel("�γ�");

        upper=new JPanel();
        buttonQuery= new JButton("��ѯ");
        comboBoxYear=new JComboBox(Utility.simpleUniqueQuery(SC.TABLE,SC.AYEAR));
        comboBoxCourse=new JComboBox(Utility.simpleUniqueQuery(Course.TABLE,Course.NAME));
        this.upper.setLayout(createLayout());

        top=new JPanel();
        labelHeading=new JLabel("��������Ҫ��ѯ��ѧ��Ϳγ����");
        //labelHeading.setHorizontalAlignment(SwingConstants.LEFT);
        top.add(labelHeading);

        buttonQuery.addActionListener(this);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.top);
        this.add(this.upper);
        table=new JTable(1,3);//todo What should be presented when no result has been acquired.
        this.add(this.table);

        //this.setVisible(true);
        this.setFont(new Font("����",Font.ITALIC,30));//TODo �������⻹�ڣ���һ����ʾ����û�о��С�
        /*
        * ���Խ��GUI�������������⡣
        * */
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sql="select top 5 S.S_id, S.S_name, SC.score from SC, Student as S where SC.S_id = S.S_id ";
        if(this.comboBoxYear.getSelectedItem()!=null){
            sql=sql.concat(" and SC."+SC.AYEAR+" = "+comboBoxYear.getSelectedItem().toString());
        }
        if(this.comboBoxCourse.getSelectedItem()!=null)
            sql=sql.concat(" and SC."+SC.C_ID+" = ( select C_id from Course where C_name = '"+comboBoxCourse.getSelectedItem().toString()+"')");//TODO SQL���ûд�ꡣ
        System.out.println(sql);
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            table = new Table(resultSet).jt;
            this.add(table);
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

