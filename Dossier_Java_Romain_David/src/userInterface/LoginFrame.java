package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame
{
    MainFrame targetFrame;
    public LoginFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,500,400);
        setResizable(false);
        add(new LoginPanel(this));
        setVisible(true);
    }

    private class LoginPanel extends JPanel
    {
        MainFrame targetFrame;
        LoginFrame frame;
        JLabel emailLabel,passwordLabel;
        JTextField email,password;
        JButton quitButton,loginButton;
        public String getEmail(){return email.getText(); }

        public LoginPanel(LoginFrame frame)
        {
            this.frame=frame;
            this.targetFrame=targetFrame;
            setLayout(null);
            setBounds(15,15,frame.getWidth()-50,frame.getHeight()-50);
            setBackground(Color.WHITE);

            ImageIcon banner= new ImageIcon("./externalRessources/banner-login.jpg");
            JLabel bannerLabel=new JLabel(banner);
            bannerLabel.setBounds(getX(),getY(),getWidth(),getHeight()*3/10);
            add(bannerLabel);

            JLabel emailLabel=new JLabel("mail:");
            JLabel passwordLabel=new JLabel("password:");
            emailLabel.setBounds(getWidth()*15/100,getHeight()*53/100,75,35);
            passwordLabel.setBounds(emailLabel.getX(),emailLabel.getY()+emailLabel.getHeight()+5,emailLabel.getWidth(),emailLabel.getHeight());
            add(emailLabel);
            add(passwordLabel);

            email=new JTextField();
            password=new JTextField();
            password.setEditable(false);
            email.setBounds(emailLabel.getX()+emailLabel.getWidth(),emailLabel.getY(),150,35);
            password.setBounds(passwordLabel.getX()+passwordLabel.getWidth(),passwordLabel.getY(),150,email.getHeight());
            add(email);
            add(password);

            quitButton=new JButton("exit");
            loginButton= new JButton("log in");
            loginButton.addActionListener(new LoginListener());
            quitButton.addActionListener(new ExitListener());
            quitButton.setBounds(getWidth()*2/5,getHeight()-getHeight()/10,100,25);
            loginButton.setBounds(quitButton.getX()+10+quitButton.getWidth(),getHeight()-getHeight()/10,100,quitButton.getHeight());
            add(quitButton);
            add(loginButton);
        }

        private class LoginListener implements ActionListener
        {

            public void actionPerformed(ActionEvent e)
            {
                MainFrame newFrame= new MainFrame();
                frame.dispose();
            }
        }

        private class ExitListener implements ActionListener
        {

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        }

    }
}
