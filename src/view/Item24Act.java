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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.DBConnection;
import model.Course;
import model.Department;
import model.SC;
import model.Student;
import toolkit.Table;
import toolkit.Utility;

public final class Item24Act extends JPanel implements  ActionListener{
	
	private JPanel upper,top, forTable;
    private JButton buttonQuery;
    private JLabel labelYear,labelSemester,labelStudentID,labelCourse,labelScore,labelGpa,labelHeading,labelTo,labelTo1,labelTo2;
    private JComboBox comboBoxYear,comboBoxSemester,comboBoxCourse;
    private JTextField textIDMin, textIDMax, textScoreMin, textScoreMax, textGpaMin, textGpaMax;
    private JTable table;
    private JScrollPane jsp1;
    private ResultSet resultSet=null;
    
    public Item24Act (){
    	super();
        labelYear=new JLabel("学年");
        labelSemester=new JLabel("学期");
        labelStudentID = new JLabel("学号");
        labelCourse = new JLabel("课程名称");
        labelScore = new JLabel("成绩");
        labelGpa = new JLabel("绩点");
        labelTo = new JLabel("至");
        labelTo1 = new JLabel("至");
        labelTo2 = new JLabel("至");

        upper=new JPanel();
        buttonQuery= new JButton("查询");
        comboBoxYear=new JComboBox(Utility.simpleUniqueQuery(SC.TABLE, SC.AYEAR));
        comboBoxSemester=new JComboBox(Utility.simpleUniqueQuery(SC.TABLE,SC.SEMESTER));
        comboBoxCourse=new JComboBox(Utility.simpleUniqueQuery(Course.TABLE,Course.NAME));
        textIDMin=new JTextField(30);
        textIDMax=new JTextField(30);
        textScoreMin=new JTextField(30);
        textScoreMax=new JTextField(30);
        textGpaMin=new JTextField(30);
        textGpaMax=new JTextField(30);
        this.upper.setLayout(createLayout());

        top=new JPanel();
        forTable = new JPanel();
        jsp1 = new JScrollPane();
        labelHeading=new JLabel("请输入需要查询的条件");
        //labelHeading.setHorizontalAlignment(SwingConstants.LEFT);
        top.add(labelHeading);

        buttonQuery.addActionListener(this);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.top);
        this.add(this.upper);
        table=new JTable(1,3);//todo What should be presented when no result has been acquired.
        

        this.setVisible(true);
        this.setFont(new Font("宋体",Font.ITALIC,30));
    }
	
    private LayoutManager createLayout(){
        GroupLayout layout=new GroupLayout(upper);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelYear).addComponent(labelSemester).addComponent(labelStudentID).addComponent(labelCourse).addComponent(labelScore).addComponent(labelGpa);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(comboBoxYear).addComponent(comboBoxSemester).addComponent(textIDMin).addComponent(comboBoxCourse).addComponent(textScoreMin).addComponent(textGpaMin);
        GroupLayout.ParallelGroup hGroup3=layout.createParallelGroup().addComponent(labelTo).addComponent(labelTo1).addComponent(labelTo2);
        GroupLayout.ParallelGroup hGroup4=layout.createParallelGroup().addComponent(textIDMax).addComponent(textScoreMax).addComponent(textGpaMax);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addGroup(hGroup3).addGroup(hGroup4).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelYear).addComponent(comboBoxYear);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSemester).addComponent(comboBoxSemester);
        GroupLayout.ParallelGroup vGroup3=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelStudentID).addComponent(textIDMin).addComponent(labelTo).addComponent(textIDMax);
        GroupLayout.ParallelGroup vGroup4=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelCourse).addComponent(comboBoxCourse);
        GroupLayout.ParallelGroup vGroup5=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelScore).addComponent(textScoreMin).addComponent(labelTo1).addComponent(textScoreMax);
        GroupLayout.ParallelGroup vGroup6=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelGpa).addComponent(textGpaMin).addComponent(labelTo2).addComponent(textGpaMax).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addGroup(vGroup3).addGroup(vGroup4).addGroup(vGroup5).addGroup(vGroup6);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
    
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String sqlString = "select * from " + SC.TABLE + ", " + Course.TABLE + " where " + SC.TABLE + "."+ SC.C_ID + " = " + Course.TABLE + "."+ Course.ID;
		//System.out.print(sqlString);
		//String sqlString="";
		if(this.comboBoxYear.getSelectedItem() != null)
			sqlString = sqlString + (" and " + SC.AYEAR + " = " + comboBoxYear.getSelectedItem());
		if(this.comboBoxSemester.getSelectedItem() != null)
			sqlString = sqlString + (" and " + SC.SEMESTER + " = '" + comboBoxSemester.getSelectedItem() + "'");
		if(this.textIDMin.getText().equals("")){}
		else{
			float idMin = Float.parseFloat(textIDMin.getText());
			sqlString = sqlString + (" and " + SC.S_ID + " >= " + idMin);
		}
		if(this.textIDMax.getText().equals("")){}
		else{
			float idMax = Float.parseFloat(textIDMax.getText());
			sqlString = sqlString + (" and" + SC.S_ID + " <= " + idMax);
		}
		if(this.comboBoxCourse.getSelectedItem() != null)
			sqlString = sqlString + (" and " + Course.NAME + " = '" + comboBoxCourse.getSelectedItem() + "'");
		if(this.textScoreMin.getText().equals("")){}
		else{
			float scoreMin = Float.parseFloat(textScoreMin.getText());
			sqlString = sqlString + (" and " + SC.SCORE + " >= " + scoreMin);
		}
		if(this.textScoreMax.getText().equals("")){}
		else{
			float scoreMax = Float.parseFloat(textScoreMax.getText());
			sqlString = sqlString + (" and" + SC.SCORE + " <= " + scoreMax);
		}
		if(this.textGpaMin.getText().equals("")){}
		else{
			float gpaMin = Float.parseFloat(textScoreMin.getText());
			sqlString = sqlString + (" and " + SC.GPA + " >= " + gpaMin);
		}
		if(this.textGpaMax.getText().equals("")){}
		else{
			float gpaMax = Float.parseFloat(textGpaMax.getText());
			sqlString = sqlString + (" and" + SC.GPA+ " <= " + gpaMax);
		}
		System.out.println(sqlString);
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlString);
            forTable.removeAll();
            jsp1 = new Table(resultSet).jsp1;
            forTable.add(jsp1);
            this.add(forTable);
            this.updateUI();
			statement.close();
			resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
