package view;

import javax.swing.JFrame;

import control.MainControl;
import toolkit.Utility;

/**
 * Created by Jordan on 2017/11/19.
 */

public class MainPageView extends JFrame{
    private static final MainPageView ourInstance = new MainPageView();

    public static MainPageView getInstance() {
        MainControl.addViewToViewscollector(ourInstance);
        return ourInstance;
    }

    private MainPageView() {
        super();
        this.add(new Item341Act());
        this.setVisible(true);
        Utility.SetWindowAtCenter(this);
        this.setSize(300,300);
    }
}
