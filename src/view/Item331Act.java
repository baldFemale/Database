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
import model.Student;
import model.Department;
import toolkit.Utility;
public class Item331Act extends JPanel implements ActionListener{
	JLabel jl1,jl2;
	JButton jb1;
	JPanel jp1,jp2,jp3;
	JComboBox jcb1;
	JTable jt1;
	public Item331Act() {
		jl1 = new JLabel("系别");
		jl2 = new JLabel("查询各系来自各省份的学生人数");
		jcb1 = new JComboBox(Utility.simpleUniqueQuery(Department.TABLE,Department.NAME));
		jb1 = new JButton("查询");
		jb1.addActionListener(this);
		jt1 = new JTable(1,3);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp1.add(jl2);
		jp2.add(jl1);
		jp2.add(jcb1);
		jp2.add(jb1);
		this.add(jp1);
		this.add(jp2);
		this.add(jt1);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String sql = "select S_prov,count(*) as COUNT from student where Dept_id in (select Dept_id from department"
				+ "where dept_name='"+jcb1.getSelectedItem()+"') group by S_prov;";
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
