package control;

import java.awt.Window;
import java.util.HashSet;

import view.LoginView;
import view.MainPageView;

public class MainControl {
    private static HashSet<Window> viewsCollector = new HashSet<>();
    public static void main(String[] args){
        System.out.println("ojbk");
        DBConnection.getInstance();
        //MainPageView.getInstance();
        LoginView.getInstance();
    }
    public static boolean addViewToViewscollector(Window view){
        return viewsCollector.add(view);
    }
    public static void disposeAll(){
        for (Window view:viewsCollector) {
            view.dispose();
        }
        viewsCollector.clear();
    }
}
