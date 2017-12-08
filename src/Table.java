package table;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import java.math.*;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Tabel table = new Tabel(resultset,"tablename")
 */

public class Table extends JFrame{
	private ResultSet res;
	private ResultSetMetaData rmd;
	private Vector rowData = new Vector();
	private Vector columnName = new Vector();
	private int rowcount;
	private int columncount;
	private JTable jt;
	private JTableHeader head;
	private JScrollPane jsp=null;  
	public Table(ResultSet res_in,String s) throws SQLException {
		
		res = res_in;
		rmd = res.getMetaData();
		get_column();
		get_content();
		if (rowcount!=0 && columncount!=0) {
		generate_table(s);
		}
		else {
			generate_wrong();
		}
	}
	
	private void get_column() throws SQLException {
		columncount = rmd.getColumnCount();
		for(int i=1;i<=columncount;i++) {
			columnName.add(rmd.getColumnName(i));
		}
	}
	
	private void get_content() throws SQLException {
		while(res.next()) {
			rowcount+=1;
			Vector hang = new Vector();
			for (int i=1;i<=columncount;i++) {
			hang.add(res.getString(i));
			}
			rowData.add(hang);
		}
	}
	private void generate_table(String s) {
		JTable jt = new JTable(rowData,columnName){
	        public boolean isCellEditable(int row, int column)
	             {return false;}
	        };
		jsp = new JScrollPane(jt);
		this.setTitle(s+"  "+"共有"+rowcount+"条记录,"+columncount+"字段");
		this.add(jsp);
		this.setSize(Math.max(100*columncount,400),300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	private void generate_wrong() {
		JOptionPane.showMessageDialog(null, "没有符合条件的记录，请检查查询条件","wrong",JOptionPane.ERROR_MESSAGE);;
	}
}
