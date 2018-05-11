package userInterface;

import ThreadAppli.ThreadLogin;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends JFrame
{

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
        ThreadLogin threadLogin;
        MainFrame targetFrame;
        LoginFrame frame;
        JTextField email,password;
        JButton quitButton,loginButton;

        public LoginPanel(LoginFrame frame)
        {
            this.frame=frame;
            setLayout(null);
            setBounds(15,15,frame.getWidth()-50,frame.getHeight()-50);
            setBackground(Color.WHITE);

            ImageIcon SPALogo= new ImageIcon("./externalRessources/Logo_de_la_SPA.jpg");
            JLabel logoLabel = new JLabel(SPALogo);
            logoLabel.setBounds(45,325,20,20);
            add(logoLabel);

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
            threadLogin = new ThreadLogin(logoLabel, this);
            threadLogin.start();
        }

        private class LoginListener implements ActionListener
        {

            public void actionPerformed (ActionEvent e)
            {
                try {
                    CareGiverController loginControl = new CareGiverController();
                    loginControl.setCareGiverConnection(email.getText());
                    MainFrame newFrame = new MainFrame(loginControl);
                    frame.dispose();
                }

                catch(BDConnexionError dbError)
                {
                    JOptionPane.showMessageDialog(null,dbError.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
                }
                catch (InexistantCareGiver loginError)
                {
                    JOptionPane.showMessageDialog(null,loginError.getMessage(),"unknown login",JOptionPane.ERROR_MESSAGE);
                }
                catch (ErrorNull errorNull){
                    JOptionPane.showMessageDialog(null,errorNull.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
                }
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
