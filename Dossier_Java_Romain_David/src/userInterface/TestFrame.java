package userInterface;

import erreurs.ErreurrNull;
import erreurs.NombreInvalideException;

import javax.swing.*;
import java.awt.*;



public class TestFrame extends JFrame
{
    public TestFrame() {
        //try {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container container = getContentPane();
            //container.setBackground(Color.white);
            setBounds(500, 200, 1000, 400);
            //container.add(new InfoTachePanel(this));
            //container.add(new ListeSoignantsPanel(null));
            setVisible(true);
            setResizable(false);
        //}
        /*catch(NombreInvalideException nbException)
        {
            JOptionPane.showMessageDialog(null,nbException.getMessage(), "erreur de nombre", JOptionPane.ERROR_MESSAGE);
        }*/
        /*catch (ErreurrNull erreurrNull)
        {
            JOptionPane.showMessageDialog(null,erreurrNull.getMessage(), "attribut illégal", JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
