package control;

import view.LoginView;

public class MainControl {
    public static void main(String[] args){
        System.out.println("ojbk");
        DBConnection.getInstance();
        LoginView.getInstance();
    }
}
