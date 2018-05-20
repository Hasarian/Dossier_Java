package userInterface;

import ThreadAppli.ThreadEcranDeConnexion;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionFrame extends JFrame
{

    public ConnexionFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,500,400);
        setResizable(false);

        add(new LoginPanel(this));
        setVisible(true);
    }

    private class LoginPanel extends JPanel
    {
        ThreadEcranDeConnexion threadEcranDeConnexion;
        //MainFrame targetFrame;
        ConnexionFrame frame;
        JTextField email,password;
        JButton quitButton,loginButton;

        public LoginPanel(ConnexionFrame frame)
        {
            this.frame=frame;
            setLayout(null);
            setBounds(15,15,frame.getWidth()-50,frame.getHeight()-50);
            setBackground(Color.WHITE);

            ImageIcon SPALogo= new ImageIcon("./externalRessources/Logo_de_la_SPA.jpg");
            JLabel logoLabel = new JLabel(SPALogo);
            logoLabel.setBounds(frame.getX(),getY()+getHeight()-20,20,20);
            add(logoLabel);

            ImageIcon banner= new ImageIcon("./externalRessources/banner-login.jpg");
            JLabel bannerLabel=new JLabel(banner);
            bannerLabel.setBounds(getX(),getY(),getWidth(),getHeight()*3/10);
            add(bannerLabel);

            JLabel emailLabel=new JLabel("mail:");
            JLabel passwordLabel=new JLabel("mot de passe:");
            emailLabel.setBounds(getWidth()*15/100,getHeight()*53/100,100,35);
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

            quitButton=new JButton("quitter");
            loginButton= new JButton("connexion");

            loginButton.addActionListener(new LoginListener());
            quitButton.addActionListener(new ExitListener());
            quitButton.setBounds(getWidth()*2/5,getHeight()-getHeight()/10,100,25);
            loginButton.setBounds(quitButton.getX()+10+quitButton.getWidth(),getHeight()-getHeight()/10,100,quitButton.getHeight());
            add(quitButton);
            add(loginButton);
            threadEcranDeConnexion = new ThreadEcranDeConnexion(logoLabel, this);
            threadEcranDeConnexion.start();
        }

        private class LoginListener implements ActionListener
        {

            public void actionPerformed (ActionEvent e)
            {
                try {
                    SoignantController loginControl = new SoignantController();
                    loginControl.setSoignantConnexion(email.getText());
                    MainFrame newFrame = new MainFrame(loginControl);
                    frame.dispose();
                    threadEcranDeConnexion.stopThread();
                }

                catch(BDConnexionErreur dbError)
                {
                    JOptionPane.showMessageDialog(null,dbError.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
                }
                catch (SoignantInexistant loginError)
                {
                    JOptionPane.showMessageDialog(null,loginError.getMessage(),"unknown login",JOptionPane.ERROR_MESSAGE);
                }
                catch (ErreurrNull erreurrNull){
                    JOptionPane.showMessageDialog(null, erreurrNull.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
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
