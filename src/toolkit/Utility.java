package toolkit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * A bunch of tools.
 * Created by Jordan on 2017/12/3.
 */

public class Utility {
    private Utility(){}
    public static void SetWindowAtCenter(Window window){
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension screenSize=toolkit.getScreenSize();
        window.setLocation((screenSize.width-window.getWidth())/2,(screenSize.height-window.getHeight())/2);
    }
}
