package toolkit;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Tabel table = new Tabel(resultset)
 * table.jt
 * table.jsp
 * table.columnName
 * table.rowcount
 */

public class Table{
	private ResultSet res;
	private ResultSetMetaData rmd;
	private Vector rowData = new Vector();
	private Vector columnName = new Vector();
	public int rowcount;
	public int columncount;
	public JTable jt;
	private JTableHeader head;
	public JScrollPane jsp=null;  
	
	public Table(ResultSet res_in) throws SQLException {
		
		res = res_in;
		rmd = res.getMetaData();
		get_column();
		get_content();
		jt = generate_table();
		jsp.add(jt);
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
	
	private JTable generate_table() {
		JTable jt = new JTable(rowData,columnName){
	        public boolean isCellEditable(int row, int column)
	             {return false;}
	        };
	    return jt;
	}
}

