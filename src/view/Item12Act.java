package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;



import control.DBConnection;
import toolkit.Table;

public class Item12Act extends JPanel implements ItemListener,ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public Item12Act(){    	
        createSubPage();
    }
    
    JLabel jl11;
    JLabel jl12;
    JLabel jl13;
    JLabel jl14;
    JLabel jl15;
    JLabel jl16;
    JLabel jl17;
    JLabel jl18;
    JLabel jl19;
    JLabel jl21;
    JTable jTa1;
    JButton jb1;
    JButton jb2;
    JButton jb3;
    //JButton jb4;
    JButton jb5;
    //JButton jb6;
    JTextField jt1;
    JTextField jt2;
    JTextField jt3;
    JTextField jt4;
    JTextField jt5;
    JTextField jt6;
    JTextField jt7;
    JTextField jt8;
    JTextField jt9;
    JComboBox<String> jc1;
    
    JRadioButton jr1;
    JRadioButton jr2;
    JRadioButton jr3;
    ButtonGroup bg1;
    JPanel jp1;
    JPanel jp2=new JPanel();
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    GridBagLayout gbl2 = new GridBagLayout();
	GridBagConstraints c2 = new GridBagConstraints();
    
    void createSubPage(){
    	
    	MainPageView.setUIFont();
    	setLayout(gbl);
    	
    	//initialize items
    	jr1 = new JRadioButton("录入");
    	jr2 = new JRadioButton("修改");
    	jr3 = new JRadioButton("删除");
    	bg1 = new ButtonGroup();
    	bg1.add(jr1);
    	bg1.add(jr2);
    	bg1.add(jr3);
    	jp1 = new JPanel();
    	jl21 = new JLabel("更改为:");
    	jTa1 = new JTable(1,3);
    	
    	//add items
    	add(jr1);
    	add(jr2);
    	add(jr3);
    	add(jp1);
    	jp1.add(jTa1);
    	
    	//set JPanel2 layout
    	jp2.setLayout(gbl2);
    	
    	//set jpBasic layout
    	c.fill = GridBagConstraints.BOTH;
    	
    	c.gridwidth=1;
    	c.weightx=0;
    	c.weighty=0;
    	c.gridx=0;
    	c.gridy=0;
    	c.insets=new Insets(5, 5, 5, 5);
    	gbl.setConstraints(jr1, c);
    	c.gridx=1;
    	gbl.setConstraints(jr2, c);
    	c.gridx=2;
    	gbl.setConstraints(jr3, c);
    	c.gridwidth=3;
    	c.gridheight=6;
    	c.weightx=1;
    	c.gridx=0;
    	c.gridy=1;
    	gbl.setConstraints(jp2, c);
    	c.gridwidth=6;
    	c.gridheight=8;
    	c.gridx=0;
    	c.gridy=10;
    	gbl.setConstraints(jp1, c);
    	
    	//add RedioButton ActionListener
    	jr1.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createInsertView();
			}
		});
    	jr2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				createUpdateView();
			}
		});
    	jr3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				createDeleteView();
			}
		});

    }
	
	void createInsertView(){
		
		//initialize items
    	jl11 = new JLabel("教师号:");
    	jl12 = new JLabel("姓名:");
    	jl13 = new JLabel("性别:");
    	jl14 = new JLabel("出生日期:");
    	jl15 = new JLabel("来自省份:");
    	jl16 = new JLabel("来自地区（市县）:");
    	jl17 = new JLabel("所属系别代码:");
    	jl18 = new JLabel("职称:");
    	jl19 = new JLabel("薪水:");
    	
    	jb1 = new JButton("确定");
    	jb2 = new JButton("查看当前表");
    	
    	jt1 = new JTextField(14);
    	jt2 = new JTextField(14);
    	jt3 = new JTextField(14);
    	jt4 = new JTextField(14);
    	jt5 = new JTextField(14);
    	jt6 = new JTextField(14);
    	jt7 = new JTextField(14);    
    	jt8 = new JTextField(14);
    	jt9 = new JTextField(14); 
		
		//add items
		add(jp2);
		jp2.removeAll();
    	
		setLayout(gbl);
    	jp2.setLayout(gbl2);

    	add(jl11);
    	add(jl12);
    	add(jl13);
    	add(jl14);
    	add(jl15);
    	add(jl16);
    	add(jl17);
    	add(jl18);
    	add(jl19);
    	add(jt1);
    	add(jt2);
    	add(jt3);
    	add(jt4);
    	add(jt5);
    	add(jt6);
    	add(jt7);
    	add(jt8);
    	add(jt9);
		
		//set jp2 layout
    	jp2.add(jl11);
    	jp2.add(jl12);
    	jp2.add(jl13);
    	jp2.add(jl14);
    	jp2.add(jl15);
    	jp2.add(jl16);
    	jp2.add(jl17);
    	jp2.add(jl18);
    	jp2.add(jl19);
    	jp2.add(jt1);
    	jp2.add(jt2);
    	jp2.add(jt3);
    	jp2.add(jt4);
    	jp2.add(jt5);
    	jp2.add(jt6);
    	jp2.add(jt7);
    	jp2.add(jt8);
    	jp2.add(jt9);
    	jp2.add(jb1);
    	jp2.add(jb2);
    	
    	c2.fill=GridBagConstraints.BOTH;
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=0;
    	c2.insets=new Insets(5, 5, 5, 5);
    	gbl2.setConstraints(jl11, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt1, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=1;
    	gbl2.setConstraints(jl12, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt2, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=2;
    	gbl2.setConstraints(jl13, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt3, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=3;
    	gbl2.setConstraints(jl14, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt4, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=4;
    	gbl2.setConstraints(jl15, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt5, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=5;
    	gbl2.setConstraints(jl16, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt6, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=6;
    	gbl2.setConstraints(jl17, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt7, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=7;
    	gbl2.setConstraints(jl18, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt8, c2);
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=8;
    	gbl2.setConstraints(jl19, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt9, c2);
    	c2.gridwidth=1;
    	c2.gridheight=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=9;
    	gbl2.setConstraints(jb1, c2);
    	c2.gridx=1;
    	c2.gridy=9;
    	c2.gridwidth=1;
    	gbl2.setConstraints(jb2, c2);
    	
    	//validate();
    	//updateUI();
    	jp2.validate();
    	//jp2.repaint();
    	
    	jp2.updateUI();
    	
    	jb1.addActionListener(this);
    	jb2.addActionListener(this);
	}
	
	void createUpdateView(){
		
		String[] stuProperties = {"T_name","T_sex","T_birth","T_prov","T_region","Dept_id","Prof","Sal"};
		
		setLayout(gbl);
		jp2.setLayout(gbl2);
		
		//initialize items
    	jl11 = new JLabel("教师号:");
    	jl12 = new JLabel("属性:");
    	jl21 = new JLabel("更改为:");
		
    	jb3 = new JButton("确定");
    	jb2 = new JButton("查看当前表");
    	
    	jt1 = new JTextField(14);
    	jt2 = new JTextField(14);
    	
    	jc1 = new JComboBox<>(stuProperties);
    	
    	//add items
    	add(jp2);
    	jp2.removeAll();

    	add(jl11);
    	add(jl12);
    	add(jl21);
    	add(jt1);
    	add(jt2);
    	add(jb3);
    	add(jb2);
    	add(jc1);
    	
    	//set jp2 layout
    	jp2.add(jl11);
    	jp2.add(jl12);
    	jp2.add(jl21);
    	jp2.add(jt1);
    	jp2.add(jt2);
    	jp2.add(jb3);
    	jp2.add(jb2);
    	jp2.add(jc1);
    	
    	c2.fill=GridBagConstraints.BOTH;
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=0;
    	c2.insets=new Insets(5, 5, 5, 5);
    	gbl2.setConstraints(jl12, c2);
    	c2.gridwidth=2;
    	c2.gridx=1;
    	gbl2.setConstraints(jc1, c2);
    	c2.gridwidth=1;
    	c2.gridx=0;
    	c2.gridy=1;
    	gbl2.setConstraints(jl11, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt1, c2);
    	c2.gridwidth=1;
    	c2.gridx=0;
    	c2.gridy=2;
    	gbl2.setConstraints(jl21, c2);
    	c2.gridwidth=2;
    	c2.gridx=1;
    	gbl2.setConstraints(jt2, c2);    	
    	c2.gridwidth=1;
    	c2.gridheight=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=3;
    	gbl2.setConstraints(jb3, c2);
    	c2.gridx=1;
    	c2.gridwidth=1;
    	gbl2.setConstraints(jb2, c2);
    	
    	validate();
    	updateUI();
    	jp2.validate();
    	jp2.updateUI();
    	
    	jb3.addActionListener(this);
    	//jb4.addActionListener(this);
	}
	
	void createDeleteView(){
		
		setLayout(gbl);
		jp2.setLayout(gbl2);
		
		//initialize items
    	jl11 = new JLabel("教师号:");
		
    	jb5 = new JButton("确定");
    	jb2 = new JButton("查看当前表");
    	
    	jt1 = new JTextField(14);
    	
    	//add items
    	add(jp2);
    	jp2.removeAll();

    	add(jl11);
    	add(jt1);
    	add(jb5);
    	add(jb2);
    	
    	//set jp2 layout
    	jp2.add(jl11);
    	jp2.add(jt1);
    	jp2.add(jb5);
    	jp2.add(jb2);
    	
    	c2.fill=GridBagConstraints.BOTH;
    	c2.gridwidth=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=0;
    	c2.insets=new Insets(5, 5, 5, 5);
    	gbl2.setConstraints(jl11, c2);
    	c2.gridx=1;
    	c2.gridwidth=2;
    	gbl2.setConstraints(jt1, c2);	
    	c2.gridwidth=1;
    	c2.gridheight=1;
    	c2.weightx=0;
    	c2.weighty=0;
    	c2.gridx=0;
    	c2.gridy=1;
    	gbl2.setConstraints(jb5, c2);
    	c2.gridx=1;
    	c2.gridy=1;
    	c2.gridwidth=1;
    	gbl2.setConstraints(jb2, c2);
    	
    	validate();
    	updateUI();
    	jp2.validate();
    	jp2.updateUI();
    	
    	jb5.addActionListener(this);
    	//jb6.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==jb1){
			String sql = "insert into student (T_id,T_name,T_sex,T_birth,T_prov,T_region,Dept_id,Prof,Sal)values ('"
						+jt1.getText()+"','"+jt2.getText()+"','"+jt3.getText()+"','"
						+jt4.getText()+"','"+jt5.getText()+"','"+jt6.getText()+"','"
						+jt7.getText()+"','"+jt8.getText()+"','"+jt9.getText()+"');";
			System.out.println(sql);
			Statement statement = null;	 
			try {
				statement = DBConnection.getConnection().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				statement.executeUpdate(sql);
				statement.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else if(e.getSource()==jb2){
			String sql = "select from student order by T_id;";
			System.out.println(sql);
			Statement statement = null;	 
			try {
				statement = DBConnection.getConnection().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ResultSet resultSet = statement.executeQuery(sql);
				jTa1 = new Table(resultSet).jt;
				jp1.validate();
				jp1.updateUI();
				statement.close();
				resultSet.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else if(e.getSource()==jb3){
			String sql = "update student set "+jc1.getSelectedItem()+" = '"+jt2.getText()+"' where T_id="
							+jt1.getText()+";";
			System.out.println(sql);
			Statement statement = null;	 
			try {
				statement = DBConnection.getConnection().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				statement.executeUpdate(sql);
				statement.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else if(e.getSource()==jb5){
			String sql = "delete from student where T_id ="+jt1.getText()+";";
			System.out.println(sql);
			Statement statement = null;
			try {
				statement = DBConnection.getConnection().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				statement.executeUpdate(sql);
				statement.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}

