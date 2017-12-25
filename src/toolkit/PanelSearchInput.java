package toolkit;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Jordan on 2017/12/25.
 */

public class PanelSearchInput extends JPanel {
    JLabel instruction,title;
    public JTextField jtf;
    public JButton jb;
    JPanel down;
    public PanelSearchInput(String name,String instr){
        LayoutManager layout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(layout);
        title = new JLabel(name);
        jtf=new JTextField();
        instruction = new JLabel(instr);
        jb=new JButton("查询");
        down=new JPanel();
        down.add(title);
        down.add(jtf);
        down.add(jb);
        add(instruction);
        add(down);
    }
}//当年破解安装oracle数据库有三千块钱。
