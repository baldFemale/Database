package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.MainControl;
import toolkit.Utility;

/**
 * Singleton. Hence we can get access to the instance with the name of the class.
 * on 2017/11/19.
 */

public class MainPageView extends JFrame implements ActionListener{
    private static final MainPageView ourInstance = new MainPageView();

    public static MainPageView getInstance() {
        MainControl.addViewToViewscollector(ourInstance);
        return ourInstance;
    }

    JMenuBar mainBar = new JMenuBar();
	JMenu menu1;
	JMenu menu2;
	JMenu menu3;
	JMenu menu31;
	JMenu menu32;
	JMenu menu33;
	JMenu menu34;
	
	JMenuItem item11;
	JMenuItem item12;
	JMenuItem item13;
	JMenuItem item14;
	JMenuItem item15;
	JMenuItem item16;
	JMenuItem item21;
	JMenuItem item22;
	JMenuItem item23;
	JMenuItem item24;
	JMenuItem item25;
	JMenuItem item26;
	JMenuItem item311;
	JMenuItem item312;
	JMenuItem item321;
	JMenuItem item322;
	JMenuItem item323;
	JMenuItem item331;
	JMenuItem item341;
	JMenuItem item342;
	JMenuItem item343;
	JMenuItem item344;
	JMenuItem item345;
	JMenuItem item346;
	JMenuItem item347;
	
	JPanel jpBasic = new JPanel();
	
	//JScrollPane jspBasic = new JScrollPane(jpBasic);
    
	//添加响应事件实例
	view.Item11Act item11Act = new view.Item11Act();
	view.Item12Act item12Act = new view.Item12Act();
	view.Item13Act item13Act = new view.Item13Act();
	view.Item14Act item14Act = new view.Item14Act();
	view.Item15Act item15Act = new view.Item15Act();
	view.Item16Act item16Act = new view.Item16Act();
	view.Item21Act item21Act = new view.Item21Act();
	view.Item22Act item22Act = new view.Item22Act();
	view.Item23Act item23Act = new view.Item23Act();
	view.Item24Act item24Act = new view.Item24Act();
	view.Item25Act item25Act = new view.Item25Act();
	view.Item26Act item26Act = new view.Item26Act();
	
	view.Item311Act item311Act = new view.Item311Act();
	view.Item312Act item312Act = new view.Item312Act();
	view.Item321Act item321Act = new view.Item321Act();
	view.Item322Act item322Act = new view.Item322Act();
	view.Item323Act item323Act = new view.Item323Act();
	view.Item331Act item331Act = new view.Item331Act();
	view.Item341Act item341Act = new view.Item341Act();
	view.Item342Act item342Act = new view.Item342Act();
	view.Item343Act item343Act = new view.Item343Act();
	view.Item344Act item344Act = new view.Item344Act();
	view.Item345Act item345Act = new view.Item345Act();
	view.Item346Act item346Act = new view.Item346Act();
	view.Item347Act item347Act = new view.Item347Act();
	
    private MainPageView() {
        super();
        createMenu();
    }
    
	public void createMenu(){

        //setUIFont();
		
        //initialize every item
		menu1 = new JMenu("数据维护");
		menu2 = new JMenu("模糊查询");
		menu3 = new JMenu("统计查询");
		menu31 = new JMenu("教师信息");
		menu32 = new JMenu("课程情况");
		menu33 = new JMenu("学生基本信息");
		menu34 = new JMenu("学生成绩信息");
		
		item11 = new JMenuItem("学生信息");
		item12 = new JMenuItem("教师信息");
		item13 = new JMenuItem("系别信息");
		item14 = new JMenuItem("课程信息");
		item15 = new JMenuItem("学生选课情况");
		item16 = new JMenuItem("教师授课情况");
		item21 = new JMenuItem("学生信息");
		item22 = new JMenuItem("教师信息");
		item23 = new JMenuItem("系别信息");
		item24 = new JMenuItem("课程信息");
		item25 = new JMenuItem("学生选课情况");
		item26 = new JMenuItem("教师授课情况");
		
		item311 = new JMenuItem("评教等级");
		item312 = new JMenuItem("全优教师及课程");
		item321 = new JMenuItem("选课人数");
		item322 = new JMenuItem("课程成绩");
		item323 = new JMenuItem("课程分档人数");
		item331 = new JMenuItem("生源地情况");
		item341 = new JMenuItem("课程前五名单");
		item342 = new JMenuItem("总成绩排名");
		item343 = new JMenuItem("全优学生名单");
		item344 = new JMenuItem("不及格学生");
		item345 = new JMenuItem("不及格课程");
		item346 = new JMenuItem("选课包含查询");
		item347 = new JMenuItem("最牛学生查询");
        
		//把菜单添加进菜单栏
		mainBar.add(menu1);
		mainBar.add(menu2);
		mainBar.add(menu3);
		mainBar.setAutoscrolls(true);
		mainBar.setSize( 1280, 30);
		
		//把菜单项添进菜单
		menu1.add(item11);
		menu1.add(item12);
		menu1.add(item13);
		menu1.add(item14);
		menu1.add(item15);
		menu1.add(item16);
		menu2.add(item21);
		menu2.add(item22);
		menu2.add(item23);
		menu2.add(item24);
		menu2.add(item25);
		menu2.add(item26);
		
		//添加子菜单
		menu3.add(menu31);
		menu3.add(menu32);
		menu3.add(menu33);
		menu3.add(menu34);
		
		//把菜单项添加进子菜单
		menu31.add(item311);
		menu31.add(item312);
		menu32.add(item321);
		menu32.add(item322);
		menu32.add(item323);
		menu33.add(item331);
		menu34.add(item341);
		menu34.add(item342);
		menu34.add(item343);
		menu34.add(item344);
		menu34.add(item345);
		menu34.add(item346);
		menu34.add(item347);
		
		//显示菜单
		this.setJMenuBar(mainBar);
		this.add(jpBasic);
		
		//add listeners
		item11.addActionListener(this);
		item12.addActionListener(this);
		item13.addActionListener(this);
		item14.addActionListener(this);
		item15.addActionListener(this);
		item16.addActionListener(this);
		item21.addActionListener(this);
		item22.addActionListener(this);
		item23.addActionListener(this);
		item24.addActionListener(this);
		item25.addActionListener(this);
		item26.addActionListener(this);
		
		item311.addActionListener(this);
		item312.addActionListener(this);
		item321.addActionListener(this);
		item322.addActionListener(this);
		item323.addActionListener(this);
		item331.addActionListener(this);
		item341.addActionListener(this);
		item342.addActionListener(this);
		item343.addActionListener(this);
		item344.addActionListener(this);
		item345.addActionListener(this);
		item346.addActionListener(this);
		item347.addActionListener(this);
		
		//set UI
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Utility.setWindowAtCenterAndFitSize(this);
		this.setVisible(true);
		this.setTitle("数据库大作业 李嘉宁 周俊杰 沈晓斌 唐鑫泽");
	}
	
	public void actionPerformed(ActionEvent e){  
        if(e.getSource() == item11){//改变图形化界面为“后来”的界面。  
            jpBasic.removeAll();  
            jpBasic.add("11" , item11Act);//切换代码。  
            jpBasic.validate();
            jpBasic.updateUI();
        }  
        else if(e.getSource() == item12){
            jpBasic.removeAll();
            jpBasic.add("12", item12Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item13){
            jpBasic.removeAll();
            jpBasic.add("13", item13Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item14){
            jpBasic.removeAll();
            jpBasic.add("14", item14Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item15){ 
            jpBasic.removeAll();
            jpBasic.add("15", item15Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item16){
            jpBasic.removeAll();
            jpBasic.add("16", item16Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item21){
            jpBasic.removeAll();
            jpBasic.add("21", item21Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item22){
            jpBasic.removeAll();
            jpBasic.add("22", item22Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item23){  
            jpBasic.removeAll();
            jpBasic.add("23", item23Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item24){
            jpBasic.removeAll();
            jpBasic.add("24", item24Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item25){
            jpBasic.removeAll();
            jpBasic.add("25", item25Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item26){  
            jpBasic.removeAll();
            jpBasic.add("26", item26Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item311){
            jpBasic.removeAll();
            jpBasic.add("311", item311Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item312){
            jpBasic.removeAll();
            jpBasic.add("312", item312Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item321){
            jpBasic.removeAll();
            jpBasic.add("321", item321Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item322){
            jpBasic.removeAll();
            jpBasic.add("322", item322Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item323){  
            jpBasic.removeAll();
            jpBasic.add("323", item323Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item331){
            jpBasic.removeAll();
            jpBasic.add("331", item331Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item341){
            jpBasic.removeAll();
            jpBasic.add("341", item341Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item342){
            jpBasic.removeAll();
            jpBasic.add("342", item342Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item343){
            jpBasic.removeAll();
            jpBasic.add("343", item343Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item344){
            jpBasic.removeAll();
            jpBasic.add("344", item344Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item345){
            jpBasic.removeAll();
            jpBasic.add("345", item345Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item346){ 
            jpBasic.removeAll();
            jpBasic.add("346", item346Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
        else if(e.getSource() == item347){
            jpBasic.removeAll();
            jpBasic.add("347", item347Act);
            jpBasic.validate();  
            jpBasic.updateUI();
        }
    }
    
    /*static void setUIFont()
	{
		Font f = new Font("宋体",Font.BOLD,20);
		String   names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
				"JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
				"TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
				"OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
				"ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
				"PasswordField","TextField", "Table", "Label", "Viewport",
				"RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
		}; 
		for (String item : names) {
			 UIManager.put(item+ ".font",f); 
		}
	}*/
}
