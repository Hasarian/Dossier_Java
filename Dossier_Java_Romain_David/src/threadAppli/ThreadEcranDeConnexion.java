package threadAppli;

import javax.swing.*;

public class ThreadEcranDeConnexion extends Thread {
    private JLabel logo;
    private JPanel cadre;
    private boolean continu;
    public ThreadEcranDeConnexion(JLabel logo, JPanel cadre){
        this.logo = logo;
        this.cadre = cadre;
        continu=true;
    }
    @Override
    public void run() {
        try {
            while (continu) {
                sleep(100);
                logo.setBounds(logo.getX()+10,logo.getY(),logo.getWidth(),logo.getHeight());
                if(logo.getX()>=cadre.getX()+cadre.getWidth()) logo.setBounds(cadre.getX(),logo.getY(),logo.getWidth(),logo.getHeight());
                cadre.revalidate();
            }

        }
        catch (InterruptedException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur dans le thread",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void stopThread()
    {
        continu=false;
    }
}
