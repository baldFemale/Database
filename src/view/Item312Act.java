package view;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.DBConnection;
import toolkit.Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
public class Item312Act extends JPanel implements ActionListener{
	JPanel jp1,jp2,jp3;
	JLabel jl1;
	JButton Jb1;
	JTable jt1;
	JScrollPane jsp1;
	public Item312Act() {
		jl1 = new JLabel("查询所讲授课程的评教等级全优的教师名单及所授课程");
		Jb1 = new JButton("查询");
		jt1 = new JTable(1,3);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jsp1 = new JScrollPane();
		jp1.add(jl1);
		jp2.add(Jb1);
		Jb1.addActionListener(this);
		this.add(jp1);
		this.add(jp2);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ResultSet res;
		Statement statement = null;
		String sql = "select teacher.T_Name,course.C_Name from tc,course,teacher where teacher.T_id not in (select distinct T_id from tc where Rating='良好')"
				+ " and tc.C_id=course.C_id and tc.T_id=teacher.T_id;";
		System.out.println(sql);
		try {
			statement = DBConnection.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			res = statement.executeQuery(sql);
			jsp1 = new Table(res).jsp1;
			jp3.removeALL();
			jp3.add(jsp1);
			this.add(jp3);
			this.updateUI();
			statement.close();
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
