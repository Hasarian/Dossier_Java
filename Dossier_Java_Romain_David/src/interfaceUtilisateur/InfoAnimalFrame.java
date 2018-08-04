package interfaceUtilisateur;

import controle.ControleInfosAnimal;
import erreurs.Erreur;

import javax.swing.*;
import java.awt.*;

public class InfoAnimalFrame extends JFrame
{
    private ControleInfosAnimal infoDisplayer;
    public InfoAnimalFrame(Integer idAnimal)
    {
        try{
         infoDisplayer=new ControleInfosAnimal(idAnimal);
        }
        catch (Erreur err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
        }
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container=getContentPane();
        //container.setBackground(Color.white);
        container.add(new AnimalInfoPanel());
        setBounds(500,200,500,500);
        setVisible(true);
    }
    /*public InfoAnimalFrame()
    {
        try {
            infoDisplayer = new ControleInfosAnimal();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container container = getContentPane();
            //container.setBackground(Color.white);
            container.add(new AnimalInfoPanel());
            setBounds(500, 200, 500, 500);
            setVisible(true);
        }
        catch (ErreurrNull error)
        {
            JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
        }
    }*/

    private class AnimalInfoPanel extends JPanel
    {
        public AnimalInfoPanel()
        {
            setBackground(Color.WHITE);

            JLabel id,name,numCell,remarque,race,dates,danger,etatSoin,remarqueSoin;
            id= new JLabel("id: "+infoDisplayer.getAnimalId());
            add(id);
            try{
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
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
