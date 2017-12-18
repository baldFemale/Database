package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Item13Act extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField text;  
    public Item13Act(){  
        text = new JTextField(10);  
        text.setText("13");  
        text.setEditable(false);  
        add(text);  
    }
}