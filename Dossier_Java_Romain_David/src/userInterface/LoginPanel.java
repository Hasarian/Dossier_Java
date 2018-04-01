package userInterface;

import javax.swing.*;

public class LoginPanel extends JPanel
{
    JLabel emailLabel,passwordLabel;
    JTextField email,password;
    JButton quitButton,loginButton;
    public String getEmail(){return email.getText(); }

    public LoginPanel(MainFrame frame)
    {
        setBounds(100,50,frame.getWidth()-2*100,frame.getHeight()-2*50);
        JLabel emailLabel=new JLabel("mail:");
        JLabel passwordLabel=new JLabel("password:");
        emailLabel.setBounds(getWidth()/2,getHeight()/2,10,10);
        passwordLabel.setBounds(getWidth()/2,getHeight()/2+emailLabel.getHeight(),10,10);
        emailLabel.setHorizontalAlignment(JLabel.EAST);
        passwordLabel.setHorizontalAlignment(JLabel.EAST);
        add(emailLabel);
        add(passwordLabel);

        email=new JTextField();
        password=new JTextField();
        password.setEditable(false);
        email.setHorizontalAlignment(JTextField.WEST);
        password.setHorizontalAlignment(JTextField.WEST);
        email.setBounds(getWidth()/2+emailLabel.getWidth(),getHeight()/2,15,10);
        password.setBounds(getWidth()/2+passwordLabel.getWidth(),getHeight()/2+email.getHeight(),15,10);
        add(email);
        add(password);

        quitButton=new JButton("exit");
        loginButton= new JButton("log in");

        quitButton.setBounds(getWidth()*1/4,getHeight()-getHeight()/10,10,10);
        loginButton.setBounds(getWidth()*3/4,getHeight()-getHeight()/10,10,10);
        add(quitButton);
        add(loginButton);
    }
}
