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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.DBConnection;
import model.Course;
import model.Department;
import model.SC;
import model.Student;
import model.Teacher;
import model.TC;
import toolkit.Table;
import toolkit.Utility;

public final class Item26Act extends JPanel implements ActionListener{
	private JPanel upper,top;
    private JButton buttonQuery;
    private JLabel labelYear,labelSemester,labelTeacherName,labelCourse,labelRating,labelHeading,labelTo;
    private JComboBox comboBoxYear,comboBoxSemester,comboBoxCourse,comboBoxRating;
    private JTextField textTeacherName; //用教师名字比教师号查询更便利
    private JTable table;
    private ResultSet resultSet=null;
    
    public Item26Act (){
        super();
        labelYear=new JLabel("学年");
        labelSemester=new JLabel("学期");
        labelTeacherName = new JLabel("教师姓名");
        labelCourse = new JLabel("课程名称");
        labelRating = new JLabel("评教等级");
        labelTo = new JLabel("至");

        upper=new JPanel();
        buttonQuery= new JButton("查询");
        comboBoxYear=new JComboBox(Utility.simpleUniqueQuery(TC.TABLE, TC.AYEAR));
        comboBoxSemester=new JComboBox(Utility.simpleUniqueQuery(TC.TABLE, TC.SEMESTER));
        comboBoxCourse=new JComboBox(Utility.simpleUniqueQuery(Course.TABLE, Course.NAME));
        comboBoxRating=new JComboBox(Utility.simpleUniqueQuery(TC.TABLE, TC.RATING));
        textTeacherName=new JTextField(30);
        this.upper.setLayout(createLayout());

        top=new JPanel();
        labelHeading=new JLabel("请输入需要查询的条件");
        //labelHeading.setHorizontalAlignment(SwingConstants.LEFT);
        top.add(labelHeading);

        buttonQuery.addActionListener(this);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.top);
        this.add(this.upper);
        table=new JTable(1,3);//todo What should be presented when no result has been acquired.
        

        this.setVisible(true);
        this.setFont(new Font("宋体",Font.ITALIC,30));//TODo 乱码问题还在；第一行提示文字没有居中。
        /*
        * 尝试解决GUI的中文乱码问题。
        * */
    }
    
    private LayoutManager createLayout(){
        GroupLayout layout=new GroupLayout(upper);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelYear).addComponent(labelSemester).addComponent(labelTeacherName).addComponent(labelCourse).addComponent(labelRating);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(comboBoxYear).addComponent(comboBoxSemester).addComponent(textTeacherName).addComponent(comboBoxCourse).addComponent(comboBoxRating);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelYear).addComponent(comboBoxYear);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSemester).addComponent(comboBoxSemester);
        GroupLayout.ParallelGroup vGroup3=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelTeacherName).addComponent(textTeacherName);
        GroupLayout.ParallelGroup vGroup4=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelCourse).addComponent(comboBoxCourse);
        GroupLayout.ParallelGroup vGroup5=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelRating).addComponent(comboBoxRating).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addGroup(vGroup3).addGroup(vGroup4).addGroup(vGroup5);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		String sqlString = "select * from " + TC.TABLE + ", " + Course.TABLE + ", " + Teacher.TABLE 
				+ " where " + TC.TABLE + "." + TC.C_ID + " = " + Course.TABLE + "." + Course.ID 
				+ " and " +  Teacher.TABLE + "." +Teacher.ID + " = " +  TC.TABLE + "." +TC.T_ID;
		System.out.print(sqlString);
		if(this.comboBoxYear.getSelectedItem() != null)
			sqlString = sqlString + (" and " + TC.AYEAR + " = " + comboBoxYear.getSelectedItem());
		if(this.comboBoxSemester.getSelectedItem() != null)
			sqlString = sqlString + (" and " + TC.SEMESTER + " = '" + comboBoxSemester.getSelectedItem() + "'");
		if(this.textTeacherName.getText() != null) {
			//WHERE TN LIKE ‘张%’
			sqlString = sqlString + (" and " + Teacher.NAME + " like '%" + textTeacherName.getText() + "%'");
		}
		if(this.comboBoxCourse.getSelectedItem() != null)
			sqlString = sqlString + (" and " + Course.NAME + " = '" + comboBoxCourse.getSelectedItem() + "'");
		if(this.comboBoxRating.getSelectedItem() != null)
			sqlString = sqlString + (" and " + TC.RATING + " = '" + comboBoxRating.getSelectedItem() + "'");
		System.out.println(sqlString);
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlString);
            table = (new Table(resultSet)).jt;
	    this.add(this.table);
            this.updateUI();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	

}
