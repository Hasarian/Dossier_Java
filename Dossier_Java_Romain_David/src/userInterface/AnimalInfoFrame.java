package userInterface;

import erreurs.ErrorNull;
import uIController.AnimalInfoDisplayer;
import uIController.ListsControllerAnimal;

import javax.swing.*;
import java.awt.*;

public class AnimalInfoFrame extends JFrame
{
    AnimalInfoDisplayer infoDisplayer;
    public AnimalInfoFrame(ListsControllerAnimal listControl,String idAnimal)
    {
         infoDisplayer=new AnimalInfoDisplayer(idAnimal,listControl);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container=getContentPane();
        //container.setBackground(Color.white);
        container.add(new AnimalInfoPanel());
        setBounds(500,200,500,500);
        setVisible(true);
    }
    public AnimalInfoFrame()
    {
        try {
            infoDisplayer = new AnimalInfoDisplayer();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container container = getContentPane();
            //container.setBackground(Color.white);
            container.add(new AnimalInfoPanel());
            setBounds(500, 200, 500, 500);
            setVisible(true);
        }
        catch (ErrorNull error)
        {
            JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private class AnimalInfoPanel extends JPanel
    {
        public AnimalInfoPanel()
        {
            setBackground(Color.WHITE);

            JLabel id,name,numCell,remarque,race,dates,danger,etatSoin,remarqueSoin;
            id= new JLabel("id: "+infoDisplayer.getAnimalId());
            add(id);

            name=new JLabel("nom: "+infoDisplayer.getNom());
            add(name);

            numCell=new JLabel("cellule: n°"+infoDisplayer.getNumCell());
            add(numCell);

            remarque=new JLabel("remarque: "+infoDisplayer.getRemarque());
            add(remarque);

            race=new JLabel(infoDisplayer.getRace());
            add(race);

            dates=new JLabel("date d'arrivee: "+infoDisplayer.getDateArrivee()+"- date deces: "+infoDisplayer.getDatDeces());
            add(dates);

            danger=new JLabel(infoDisplayer.getDanger());
            add(danger);

            etatSoin=new JLabel(infoDisplayer.getEtatSoin());
            add(etatSoin);

            remarqueSoin=new JLabel(infoDisplayer.getRemarqueSoin());
            add(remarqueSoin);
        }
    }
}
