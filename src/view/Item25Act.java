package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
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
import model.Teacher;
import model.TC;
import toolkit.Table;
import toolkit.Utility;

public final class Item25Act extends JPanel implements ActionListener{
	private JPanel upper,top, forTable;
    private JButton buttonQuery;
    private JLabel labelID,labelName,labelSex,labelBirth,labelProv,labelRegion,labelDept,labelProf,labelSal,labelHeading,labelTo,labelTo1,labelTo2;
    private JComboBox comboBoxSex,comboBoxProv,comboBoxRegion,comboBoxDept,comboBoxProf;
    private JTextField textIDMin, textIDMax, textName, textBirthMin, textBirthMax, textSalMin, textSalMax;
    private JTable table;
    private JScrollPane jsp1;
    private ResultSet resultSet=null;
    
    public Item25Act (){
    	super();
        labelID=new JLabel("教师号");
        labelName=new JLabel("姓名");
        labelSex = new JLabel("性别");
        labelBirth = new JLabel("出生日期");
        labelProv = new JLabel("来自省份");
        labelRegion = new JLabel("来自地区（市县）");
        labelDept = new JLabel("所属系别名称");
        labelProf = new JLabel("职称");
        labelSal = new JLabel("薪水");
        labelTo = new JLabel("至");
        labelTo1 = new JLabel("至");
        labelTo2 = new JLabel("至");

        upper=new JPanel();
        buttonQuery= new JButton("查询");
        comboBoxSex=new JComboBox(Utility.simpleUniqueQuery(Teacher.TABLE, Teacher.SEX));
        comboBoxProv=new JComboBox(Utility.simpleUniqueQuery(Teacher.TABLE,Teacher.PROV));
        comboBoxRegion=new JComboBox(Utility.simpleUniqueQuery(Teacher.TABLE,Teacher.REGION));
        comboBoxDept=new JComboBox(Utility.simpleUniqueQuery(Department.TABLE, Department.NAME));
        comboBoxProf=new JComboBox(Utility.simpleUniqueQuery(Teacher.TABLE, Teacher.PROF));
        textIDMin=new JTextField(30);
        textIDMax=new JTextField(30);
        textName=new JTextField(30);
        textBirthMin=new JTextField(30);
        textBirthMax=new JTextField(30);
        textSalMin=new JTextField(30);
        textSalMax=new JTextField(30);
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

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelID).addComponent(labelName).addComponent(labelSex).addComponent(labelBirth).addComponent(labelProv).addComponent(labelRegion).addComponent(labelDept).addComponent(labelProf).addComponent(labelSal);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(textIDMin).addComponent(textName).addComponent(comboBoxSex).addComponent(textBirthMin).addComponent(comboBoxProv).addComponent(comboBoxRegion).addComponent(comboBoxDept).addComponent(comboBoxProf).addComponent(textSalMin);
        GroupLayout.ParallelGroup hGroup3=layout.createParallelGroup().addComponent(labelTo).addComponent(labelTo1).addComponent(labelTo2);
        GroupLayout.ParallelGroup hGroup4=layout.createParallelGroup().addComponent(textIDMax).addComponent(textBirthMax).addComponent(textSalMax);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addGroup(hGroup3).addGroup(hGroup4).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelID).addComponent(textIDMin).addComponent(labelTo).addComponent(textIDMax);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelName).addComponent(textName);
        GroupLayout.ParallelGroup vGroup3=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSex).addComponent(comboBoxSex);
        GroupLayout.ParallelGroup vGroup4=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelBirth).addComponent(textBirthMin).addComponent(labelTo1).addComponent(textBirthMax);
        GroupLayout.ParallelGroup vGroup5=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelProv).addComponent(comboBoxProv);
        GroupLayout.ParallelGroup vGroup6=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelRegion).addComponent(comboBoxRegion);
        GroupLayout.ParallelGroup vGroup7=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelDept).addComponent(comboBoxDept);
        GroupLayout.ParallelGroup vGroup8=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelProf).addComponent(comboBoxProf);
        GroupLayout.ParallelGroup vGroup9=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSal).addComponent(textSalMin).addComponent(labelTo2).addComponent(textSalMax).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addGroup(vGroup3).addGroup(vGroup4).addGroup(vGroup5).addGroup(vGroup6).addGroup(vGroup7).addGroup(vGroup8).addGroup(vGroup9);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String sqlString = "select * from " + Teacher.TABLE + ", " + Department.TABLE + " where " + Teacher.TABLE + "."+ Teacher.DEPT_ID + " = " + Department.TABLE + "."+ Department.ID; //TODO
		//System.out.print(sqlString);
		if(this.textIDMin.getText().equals("")){}
		else {
			float idMin = Float.parseFloat(textIDMin.getText());
			sqlString = sqlString+(" and " + Teacher.ID + " >= " + idMin);
		}
		if(this.textIDMax.getText().equals("")){}
		else {
			float idMax = Float.parseFloat(textIDMax.getText());
			sqlString = sqlString+(" and" + Teacher.ID + " <= " + idMax);
		}
		if(this.textName.getText().equals("")){}
		else{
			//WHERE TN LIKE ���寮�%���
			sqlString = sqlString+(" and " + Teacher.NAME + " like '%" + textName.getText() + "%'");
		}
		if(this.comboBoxSex.getSelectedItem() != null)
			sqlString = sqlString+(" and " + Teacher.SEX + " = '" + comboBoxSex.getSelectedItem() + "'");
		if(this.textBirthMin.getText().equals("")){}
		else {
			StringTokenizer st = new  StringTokenizer(textBirthMin.getText(), "-");     
			java.sql.Date dateMin = new  java.sql.Date(Integer.parseInt(st.nextToken()));  			
			sqlString = sqlString+(" and " + Teacher.BIRTH + " >= " + dateMin);
		}
		if(this.textBirthMax.getText().equals("")){}
		else {
			StringTokenizer st = new  StringTokenizer(textBirthMax.getText(), "-");     
			java.sql.Date dateMax = new  java.sql.Date(Integer.parseInt(st.nextToken()));
			sqlString = sqlString+(" and" + Teacher.BIRTH + " <= " + dateMax);
		}
		
		if(this.comboBoxProv.getSelectedItem() != null)
			sqlString = sqlString+(" and " + Teacher.PROV + " = '" + comboBoxProv.getSelectedItem() + "'");
		if(this.comboBoxRegion.getSelectedItem() != null)
			sqlString = sqlString+(" and " + Teacher.REGION + " = '" + comboBoxRegion.getSelectedItem() + "'");
		if(this.comboBoxDept.getSelectedItem() != null) {
			//TODO 杩�������纭�璁や�����琛ㄦ�兼�����涔����浠ヨ����峰��
			sqlString = sqlString+(" and " + Department.NAME + " = '" + comboBoxDept.getSelectedItem() + "'");
		}
		if(this.comboBoxProf.getSelectedItem() != null)
			sqlString = sqlString+(" and " + Teacher.PROF + " = '" + comboBoxProf.getSelectedItem() + "'");
		if(this.textSalMin.getText().equals("")){}
		else{
			float salMin = Float.parseFloat(textSalMin.getText());
			sqlString = sqlString+(" and " + Teacher.SAL + " >= " + salMin);
		}
		if(this.textSalMax.getText().equals("")){}
		else {
			float salMax = Float.parseFloat(textSalMax.getText());
			sqlString = sqlString+(" and" + Teacher.SAL + " <= " + salMax);
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
