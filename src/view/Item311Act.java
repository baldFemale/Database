package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import control.DBConnection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import toolkit.Table;
import model.Teacher;
import model.TC;
import model.SC;
import toolkit.Utility;

public class Item311Act extends JPanel implements ActionListener {
	JLabel jl1,jl2,jl3;
	JButton jb1;
	JPanel jp1,jp2;
	JComboBox jcb1,jcb2;
	JTable jt1;
	public Item311Act(){
		jl1 = new JLabel("学年");
		jl2 = new JLabel("任课教师");
		jl3 = new JLabel("请输入需要查询的学年和教师姓名");
		jb1 = new JButton("查询");
		jcb1 = new JComboBox(Utility.simpleUniqueQuery(SC.TABLE, TC.AYEAR));
		jcb2 = new JComboBox(Utility.simpleUniqueQuery(SC.TABLE, Teacher.NAME));
		jt1 = new JTable(1,3);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jb1.addActionListener(this);
		this.jp1.setLayout(createLayout());
		jp2.add(jl3);
		this.add(jp2);
		this.add(jp1);
		this.add(jt1);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
		
	}
	private LayoutManager createLayout() {
		GroupLayout layout = new GroupLayout(jp1);
		layout.setAutoCreateContainerGaps(true);
		GroupLayout.ParallelGroup hGroup1 = layout.createParallelGroup().addComponent(jl1).addComponent(jl2);
		GroupLayout.ParallelGroup hGroup2 = layout.createParallelGroup().addComponent(jcb1).addComponent(jcb2);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addComponent(jb1);
		layout.setHorizontalGroup(hGroup);
		GroupLayout.ParallelGroup vGroup1 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jl1).addComponent(jcb1);
		GroupLayout.ParallelGroup vGroup2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jl2).addComponent(jcb2).addComponent(jb1);
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2);
		layout.setVerticalGroup(vGroup);
		return layout;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String sql = "SELECT TC.T_id,Teacher.T_name,Teacher.Prof,Course.C_name,TC.Rating from "+jcb1.getSelectedItem();
		System.out.println(sql);
		Statement statement = null;	 
		try {
			statement = DBConnection.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			jt1 = new Table(resultSet).jt; 
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
