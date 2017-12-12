package view;

import javax.swing.JFrame;

import control.MainControl;
import toolkit.Utility;

/**
 * Singleton. Hence we can get access to the instance with the name of the class.
 * on 2017/11/19.
 */

public class MainPageView extends JFrame{
    private static final MainPageView ourInstance = new MainPageView();

    public static MainPageView getInstance() {
        MainControl.addViewToViewscollector(ourInstance);
        return ourInstance;
    }

    private MainPageView() {
        super();
    }
}
