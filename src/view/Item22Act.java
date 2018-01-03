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

public final class Item22Act extends JPanel implements  ActionListener{
	private JPanel upper,top, forTable; 
    private JButton buttonQuery;
    private JLabel labelID,labelName,labelCredit,labelHeading,labelTo;
    private JComboBox comboBoxID,comboBoxName;
    private JTextField textCreditMin, textCreditMax;
    private JTable table;
    private JScrollPane jsp1; //TODO
    private ResultSet resultSet=null;
	
	public Item22Act (){
		super();
        labelID=new JLabel("课程代码");
        labelName=new JLabel("课程名称");
        labelCredit = new JLabel("学分");
        labelTo = new JLabel("至");

        upper=new JPanel();
        buttonQuery= new JButton("查询");
        comboBoxID=new JComboBox(Utility.simpleUniqueQuery(Course.TABLE, Course.ID));
        comboBoxName=new JComboBox(Utility.simpleUniqueQuery(Course.TABLE,Course.NAME));
        textCreditMin=new JTextField(30);
        textCreditMax=new JTextField(30);
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

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelID).addComponent(labelName).addComponent(labelCredit);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(comboBoxID).addComponent(comboBoxName).addComponent(textCreditMin);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addComponent(labelTo).addComponent(textCreditMax).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelID).addComponent(comboBoxID);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelName).addComponent(comboBoxName);
        GroupLayout.ParallelGroup vGroup3=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelCredit).addComponent(textCreditMin).addComponent(labelTo).addComponent(textCreditMax).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addGroup(vGroup3);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String sqlString = "select * from " + Course.TABLE+ " where 1 = 1";
		if(this.comboBoxID.getSelectedItem() != null)
			sqlString = sqlString + (" and " + Course.ID + " = " + comboBoxID.getSelectedItem());
		if(this.comboBoxName.getSelectedItem() != null)
			sqlString = sqlString + (" and " + Course.NAME + " = '" + comboBoxName.getSelectedItem() + "'");
		if(this.textCreditMin.getText().equals("")){}
		else {
			float creditMin = Float.parseFloat(textCreditMin.getText());
			sqlString = sqlString + (" and " + Course.CREDIT + " >= " + creditMin);
		}
		if(this.textCreditMax.getText().equals("")){}
		else{
			float creditMax = Float.parseFloat(textCreditMax.getText());
			sqlString = sqlString + (" and " + Course.CREDIT + " <= " + creditMax);
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
