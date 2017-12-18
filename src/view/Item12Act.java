package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Item12Act extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField text;  
    public Item12Act(){  
        text = new JTextField(10);  
        text.setText("12");  
        text.setEditable(false);  
        add(text);  
    }
}
