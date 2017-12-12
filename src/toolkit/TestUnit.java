package toolkit;

import java.awt.Font;

import javax.swing.JFrame;

import view.Item341Act;

/**
 * Created by Jordan on 2017/12/12.
 */

final class TestUnit {
    public static void main(String[] a){
        JFrame jf=new JFrame("test");
        jf.setSize(600,300);
        jf.add(new Item341Act());
        jf.setFont(new Font("宋体",Font.ITALIC,30));
        Utility.setWindowAtCenter(jf);
        jf.setVisible(true);
    }
}
