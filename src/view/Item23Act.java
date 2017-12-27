package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import toolkit.Table;
import toolkit.Utility;

public final class Item23Act extends JPanel implements  ActionListener{
	private JPanel upper,top, forTable;
    private JButton buttonQuery;
    private JLabel labelID,labelName,labelSex,labelBirth,labelProv,labelInto,labelDept,labelHeading,labelTo,labelTo1,labelTo2;
    private JComboBox comboBoxSex,comboBoxProv,comboBoxDept;
    private JTextField textIDMin, textIDMax, textName, textBirthMin, textBirthMax, textIntoMin, textIntoMax;
    private JTable table;
    private JScrollPane jsp1;
    private ResultSet resultSet=null;
	
    public Item23Act (){
    	super();
        labelID=new JLabel("学号");
        labelName=new JLabel("姓名");
        labelSex = new JLabel("性别");
        labelBirth = new JLabel("出生日期");
        labelProv = new JLabel("来自省份");
        labelInto = new JLabel("入校年份");
        labelDept = new JLabel("所属系别名称");
        labelTo = new JLabel("至");
        labelTo1 = new JLabel("至");
        labelTo2 = new JLabel("至");

        upper=new JPanel();
        buttonQuery= new JButton("查询");
        comboBoxSex=new JComboBox(Utility.simpleUniqueQuery(Student.TABLE, Student.SEX));
        comboBoxProv=new JComboBox(Utility.simpleUniqueQuery(Student.TABLE,Student.PROV));
        comboBoxDept=new JComboBox(Utility.simpleUniqueQuery(Department.TABLE,Department.NAME));
        textIDMin=new JTextField(30);
        textIDMax=new JTextField(30);
        textName=new JTextField(30);
        textBirthMin=new JTextField(30);
        textBirthMax=new JTextField(30);
        textIntoMin=new JTextField(30);
        textIntoMax=new JTextField(30);
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
        table=new JTable(1,3);
        
        this.setVisible(true);
        this.setFont(new Font("宋体",Font.ITALIC,30));
    }
    
    private LayoutManager createLayout(){
        GroupLayout layout=new GroupLayout(upper);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelName).addComponent(labelSex).addComponent(labelProv).addComponent(labelDept).addComponent(labelID).addComponent(labelBirth).addComponent(labelInto);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(textName).addComponent(comboBoxSex).addComponent(comboBoxProv).addComponent(comboBoxDept).addComponent(textIDMin).addComponent(textBirthMin).addComponent(textIntoMin);
        GroupLayout.ParallelGroup hGroup3=layout.createParallelGroup().addComponent(labelTo).addComponent(labelTo1).addComponent(labelTo2);
        GroupLayout.ParallelGroup hGroup4=layout.createParallelGroup().addComponent(textIDMax).addComponent(textBirthMax).addComponent(textIntoMax);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addGroup(hGroup3).addGroup(hGroup4).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelID).addComponent(textIDMin).addComponent(labelTo).addComponent(textIDMax);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelName).addComponent(textName);
        GroupLayout.ParallelGroup vGroup3=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSex).addComponent(comboBoxSex);
        GroupLayout.ParallelGroup vGroup4=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelBirth).addComponent(textBirthMin).addComponent(labelTo1).addComponent(textBirthMax);
        GroupLayout.ParallelGroup vGroup5=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelProv).addComponent(comboBoxProv);
        GroupLayout.ParallelGroup vGroup6=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelInto).addComponent(textIntoMin).addComponent(labelTo2).addComponent(textIntoMax);
        GroupLayout.ParallelGroup vGroup7=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelDept).addComponent(comboBoxDept).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addGroup(vGroup3).addGroup(vGroup4).addGroup(vGroup5).addGroup(vGroup6).addGroup(vGroup7);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
    
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String sqlString = "select * from " + Student.TABLE + ", " + Department.TABLE + " where " + Student.TABLE + "." + Student.DEPT + " = " + Department.TABLE + "." + Department.ID;
		if (this.textIDMin.getText().equals("") ){}
		else{
			int idMin = Integer.parseInt(textIDMin.getText());
			sqlString = sqlString+(" and " + Student.ID + " >= " + idMin);
		}
		if(this.textIDMax.getText().equals("")){}
		else{
			int idMax = Integer.parseInt(textIDMax.getText());
			sqlString = sqlString+(" and " + Student.ID + " <= " + idMax);
		}
		if(this.textName.getText().equals("")) {}
		else{
			sqlString = sqlString+(" and " + Student.NAME + " like '%" + textName.getText() + "%'");
		}
		if(this.comboBoxSex.getSelectedItem() != null)
			sqlString = sqlString+(" and " + Student.SEX + " = '" + comboBoxSex.getSelectedItem() + "'");
		//TODO 日期  problems with date
		if(this.textBirthMin.getText().equals("")){} 
		else {
			StringTokenizer st = new  StringTokenizer(textBirthMin.getText(), "-");
			System.out.print("\n"+st+"\n");
			java.sql.Date dateMin = new java.sql.Date(Integer.parseInt(st.nextToken())); 
			System.out.print("\n"+dateMin+"\n");
			sqlString = sqlString+(" and " + Student.BIRTH + " >= " + dateMin);
		}
		if(this.textBirthMax.getText().equals("")){}
		else {
			StringTokenizer st = new  StringTokenizer(textBirthMax.getText(), "-");     
			java.sql.Date dateMax = new  java.sql.Date(Integer.parseInt(st.nextToken()));
			sqlString = sqlString+(" and" + Student.BIRTH + " <= " + dateMax);
		}
		if(this.comboBoxProv.getSelectedItem() != null)
			sqlString = sqlString+(" and " + Student.PROV + " = '" + comboBoxProv.getSelectedItem() + "'");
		if(this.textIntoMin.getText().equals("")){}
		else{
			float intoMin = Float.parseFloat(textIntoMin.getText());
			sqlString = sqlString+(" and " + Student.INTO + " >= " + intoMin);
		}
		if(this.textIntoMax.getText().equals("")){}
		else{
			float intoMax = Float.parseFloat(textIntoMax.getText());
			sqlString = sqlString+(" and " + Student.INTO + " <= " + intoMax);
		}
		if(this.comboBoxDept.getSelectedItem() != null) {
			sqlString = sqlString+(" and " + Department.NAME + " = '" + comboBoxDept.getSelectedItem() + "'");
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
