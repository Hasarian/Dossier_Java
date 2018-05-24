package userInterface;

import erreurs.ErreurrNull;
import erreurs.NombreInvalideException;

import javax.swing.*;
import java.awt.*;

import static com.sun.glass.ui.Cursor.setVisible;

public class TestFrame extends JFrame
{
    public TestFrame() {
        try {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container container = getContentPane();
            //container.setBackground(Color.white);
            setBounds(500, 200, 1000, 400);
            container.add(new InfoTachePanel(this));
            setVisible(true);
        }
        catch(NombreInvalideException nbException)
        {
            JOptionPane.showMessageDialog(null,nbException.getMessage(), "erreur de nombre", JOptionPane.ERROR_MESSAGE);
        }
        catch (ErreurrNull erreurrNull)
        {
            JOptionPane.showMessageDialog(null,erreurrNull.getMessage(), "attribut illégal", JOptionPane.ERROR_MESSAGE);
        }
    }
}
