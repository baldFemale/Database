package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Item11Act extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField text;  
    public Item11Act(){  
        text = new JTextField(10);  
        text.setText("11");  
        text.setEditable(false);  
        add(text);  
    }
}
