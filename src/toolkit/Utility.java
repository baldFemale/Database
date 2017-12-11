package toolkit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JOptionPane;

/**
 * A bunch of tools.
 * Created on 2017/12/3.
 */

public class Utility {
    private Utility(){}
    public static void SetWindowAtCenter(Window window){
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension screenSize=toolkit.getScreenSize();
        window.setLocation((screenSize.width-window.getWidth())/2,(screenSize.height-window.getHeight())/2);
    }
    public static void ReportErrorEmptyTable(){
        JOptionPane.showMessageDialog(null, "没有符合条件的记录，请检查查询条件","wrong",JOptionPane.ERROR_MESSAGE);;
    }
}
