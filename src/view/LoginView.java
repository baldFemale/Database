package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.DBConnection;
import toolkit.Utility;
import model.Usercode;

/**
 * Created by Jordan on 2017/11/19.
 */

public class LoginView extends JFrame implements ActionListener{
    private static LoginView instance;
    public static LoginView getInstance(){
        if(instance==null)instance=new LoginView();
        return instance;
    }
    private JPanel jPanel1,jPanel2,jPanel3;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton jButtonConfirm,jButtonCancel;
    private LoginView(){
        super("Login");
        //Toolkit toolkit=Toolkit.getDefaultToolkit();
        //Dimension screenSize=toolkit.getScreenSize();
        GridLayout gridLayout=new GridLayout(3,1);
        Container container=this.getContentPane();
        container.setLayout(gridLayout);
        jPanel1=new JPanel();
        //jPanel1.setLayout(new FlowLayout());
        usernameField =new JTextField(10);
        jPanel1.add(new JLabel("Username:"));
        jPanel1.add(usernameField);
        container.add(jPanel1);
        jPanel2=new JPanel(new FlowLayout());
        passwordField =new JPasswordField(10);
        jPanel2.add(new JLabel("Password:"));
        jPanel2.add(passwordField);
        container.add(jPanel2);
        jPanel3=new JPanel(new FlowLayout());
        jButtonConfirm=new JButton("Confirm");
        jButtonConfirm.addActionListener(this);
        jButtonCancel=new JButton("Quit");
        jButtonCancel.addActionListener(this);
        jPanel3.add(jButtonConfirm);
        jPanel3.add(jButtonCancel);
        container.add(jPanel3);
        this.setSize(300,200);
        this.setVisible(true);
        Utility.setWindowAtCenter(this);
        //this.setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButtonConfirm){
            boolean correct=false;
            Statement statement;
            ResultSet resultSet;
            try{
                statement=DBConnection.getConnection().createStatement();
                resultSet = statement.executeQuery("select * from "+ Usercode.TABLE);
                System.out.println(resultSet.wasNull());
                while (resultSet.next()){
                    if(resultSet.getString(Usercode.NAME).equals(usernameField.getText()))
                        if(String.valueOf(passwordField.getPassword()).equals(resultSet.getString(Usercode.PW))){
                            correct=true;break;
                        }
                }
                resultSet.close();
                statement.close();
            }catch (SQLException er){
                er.printStackTrace();
            }finally {
                if(!correct)
                    JOptionPane.showMessageDialog(this,"The username or password is incorrect.",
                            "Invalid",JOptionPane.ERROR_MESSAGE);
                else {
                    System.out.println("login:ok");
                    MainPageView.getInstance();
                    this.dispose();
                }
            }
        }else{
            this.dispose();
        }
    }
}
