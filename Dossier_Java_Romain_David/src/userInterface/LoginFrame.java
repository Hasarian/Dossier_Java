package userInterface;

import javax.swing.*;
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
        targetFrame=new MainFrame();
        add(new LoginPanel(this,targetFrame));
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

        public LoginPanel(LoginFrame frame,MainFrame targetFrame)
        {
            this.frame=frame;
            this.targetFrame=targetFrame;
            setLayout(null);
            setBounds(100,50,frame.getWidth()-2*100,frame.getHeight()-2*50);
            JLabel emailLabel=new JLabel("mail:");
            JLabel passwordLabel=new JLabel("password:");
            emailLabel.setBounds(getWidth()/3,getHeight()/3,100,35);
            passwordLabel.setBounds(getWidth()/3,getHeight()/3+emailLabel.getHeight(),100,emailLabel.getHeight());
            add(emailLabel);
            add(passwordLabel);

            email=new JTextField();
            password=new JTextField();
            password.setEditable(false);
            email.setBounds(getWidth()/2+emailLabel.getWidth(),getHeight()/3,150,35);
            password.setBounds(getWidth()/2+passwordLabel.getWidth(),getHeight()/3+email.getHeight(),150,email.getHeight());
            add(email);
            add(password);

            quitButton=new JButton("exit");
            loginButton= new JButton("log in");
            loginButton.addActionListener(new LoginListener());
            quitButton.addActionListener(new ExitListener());
            quitButton.setBounds(getWidth()*1/4,getHeight()-getHeight()/10,100,25);
            loginButton.setBounds(quitButton.getX()+10+quitButton.getWidth(),getHeight()-getHeight()/10,100,quitButton.getHeight());
            add(quitButton);
            add(loginButton);
        }

        private class LoginListener implements ActionListener
        {

            public void actionPerformed(ActionEvent e)
            {
                targetFrame.setVisible(true);
                targetFrame.changePanel(new TaskListPanel(targetFrame));
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
