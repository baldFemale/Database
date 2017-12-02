package view;

import javax.swing.JFrame;

import control.MainControl;

/**
 * Created by Jordan on 2017/11/19.
 */

public class MainPageView extends JFrame{
    private static final MainPageView ourInstance = new MainPageView();

    public static MainPageView getInstance() {
        MainControl.addView(ourInstance);
        return ourInstance;
    }

    private MainPageView() {
    }
}
