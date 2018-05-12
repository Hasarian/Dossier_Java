package ThreadAppli;

import javax.swing.*;

public class ThreadLogin extends Thread {
    private JLabel logo;
    private JPanel cadre;
    public ThreadLogin(JLabel logo, JPanel cadre){
        this.logo = logo;
        this.cadre = cadre;
    }
    @Override
    public void run() {
        try {
            while (true) {
                sleep(1000);
                logo.setBounds(logo.getX()+10,logo.getY()+10,logo.getWidth(),logo.getHeight());
                if(logo.getX()>=cadre.getX()+cadre.getWidth()) logo.setBounds(cadre.getX(),logo.getY(),logo.getWidth(),logo.getHeight());
                if(logo.getY()>=cadre.getY()+cadre.getHeight()) logo.setBounds(logo.getX(),cadre.getY(),logo.getWidth(),logo.getHeight());
                cadre.revalidate();
            }

        }
        catch (InterruptedException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur dans le thread",JOptionPane.ERROR_MESSAGE);
        }
    }
}
