package view;

/**
 * Created by Jordan on 2017/11/19.
 */

public class MainPageView {
    private static final MainPageView ourInstance = new MainPageView();

    public static MainPageView getInstance() {
        return ourInstance;
    }

    private MainPageView() {
    }
}
