package userInterface;

import com.mysql.fabric.xmlrpc.base.Array;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.NombreInvalideException;
import erreurs.SoignantInexistant;
import uIController.SoignantController;
import uIController.ListesAnimauxController;
import uIController.TacheController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class InfoTachePanel extends JPanel
{
    private TacheController controller;
    private ArrayList<JTextArea> remarques;
    private ArrayList<JCheckBox> soinsFaits;
    private JCheckBox pourLeVeto,estDangereux;
    public InfoTachePanel(Integer id,JPanel parentPanel) throws BDConnexionErreur, ErreurrNull, SoignantInexistant,NombreInvalideException
    {
        setLayout(null);
        setBounds(2,2,parentPanel.getWidth()-15,parentPanel.getHeight()-15);
        remarques=new ArrayList<JTextArea>();
        soinsFaits=new ArrayList<JCheckBox>();
        setBackground(Color.WHITE);
        controller=new TacheController(id);
        TacheContainer container=new TacheContainer();
        container.setBounds(3,150,getWidth()-15,getHeight()*1/2);
        container.initContainer();
        //System.out.println("containerPanel: "+container.getX()+" "+container.getY()+" "+container.getWidth()+" "+container.getHeight());
        add(container);

        JButton boutonSuivant=new JButton("suite >");
        boutonSuivant.addActionListener(new SuivantListener());
        boutonSuivant.setBounds(875,container.getY()-30,100,25);
        add(boutonSuivant);

        pourLeVeto=new JCheckBox();
        pourLeVeto.setBounds(boutonSuivant.getX()-30,boutonSuivant.getY()-40,20,20);
        pourLeVeto.setBackground(Color.WHITE);
        add(pourLeVeto);
        JLabel pourLeVetoLabel=new JLabel("pour le vétérinaire");
        pourLeVetoLabel.setBounds(pourLeVeto.getX()+pourLeVeto.getWidth(),pourLeVeto.getY(),110,25);
        add(pourLeVetoLabel);

        JLabel idAnimal,numCell,nomAnimal,remarqueSoin,remarqueAnimal,espece,race;
        idAnimal=new JLabel("id: "+controller.getId());
        idAnimal.setBounds(0,0,50,25);
        numCell=new JLabel("numero de cellule: "+controller.getNumCell());
        numCell.setBounds(0,idAnimal.getY()+idAnimal.getHeight(),150,25);
        nomAnimal=new JLabel("nom: "+controller.getNom());
        nomAnimal.setBounds(idAnimal.getX()+idAnimal.getWidth()+25,idAnimal.getY(),50,25);
        estDangereux=new JCheckBox("est dangereux",controller.getDanger());
        estDangereux.addItemListener(new LockedBoxListener());
        estDangereux.setBounds(150,75,250,25);
        estDangereux.setBackground(Color.WHITE);
        remarqueSoin=new JLabel(controller.getRemarqueGeneraleSoin());
        remarqueSoin.setBounds(5,125,500,25);
        remarqueAnimal=new JLabel(controller.getRemarqueAnimal());
        remarqueAnimal.setBounds(5,100,500,25);
        espece=new JLabel(controller.getEspece());
        espece.setBounds(nomAnimal.getX()+nomAnimal.getWidth()+50,nomAnimal.getY(),45,25);
        race=new JLabel("- "+controller.getRace());
        race.setBounds(espece.getX()+espece.getWidth(),espece.getY(),75,25);

        add(race);
        add(espece);
        add(remarqueAnimal);
        add(remarqueSoin);
        add(idAnimal);
        add(numCell);
        add(nomAnimal);
        add(estDangereux);

    }
    public InfoTachePanel(JFrame testFrame) throws NombreInvalideException,ErreurrNull
    {

        setLayout(null);
        setBounds(2,2,testFrame.getWidth()-15,testFrame.getHeight()-15);
        remarques=new ArrayList<JTextArea>();
        soinsFaits=new ArrayList<JCheckBox>();
        setBackground(Color.WHITE);
        controller=new TacheController();
        TacheContainer container=new TacheContainer();
        container.setBounds(3,150,getWidth()-15,getHeight()*1/2);
        container.initContainer();
        //System.out.println("containerPanel: "+container.getX()+" "+container.getY()+" "+container.getWidth()+" "+container.getHeight());
        add(container);

        JButton boutonSuivant=new JButton("suite >");
        boutonSuivant.addActionListener(new SuivantListener());
        boutonSuivant.setBounds(875,container.getY()-30,100,25);
        add(boutonSuivant);

        pourLeVeto=new JCheckBox();
        pourLeVeto.setBounds(boutonSuivant.getX()-30,boutonSuivant.getY()-40,20,20);
        pourLeVeto.setBackground(Color.WHITE);
        add(pourLeVeto);
        JLabel pourLeVetoLabel=new JLabel("pour le vétérinaire");
        pourLeVetoLabel.setBounds(pourLeVeto.getX()+pourLeVeto.getWidth(),pourLeVeto.getY(),110,25);
        add(pourLeVetoLabel);

        JLabel idAnimal,numCell,nomAnimal,remarqueSoin,remarqueAnimal,espece,race;
        idAnimal=new JLabel("id: "+controller.getId());
        idAnimal.setBounds(0,0,50,25);
        numCell=new JLabel("numero de cellule: "+controller.getNumCell());
        numCell.setBounds(0,idAnimal.getY()+idAnimal.getHeight(),150,25);
        nomAnimal=new JLabel("nom: "+controller.getNom());
        nomAnimal.setBounds(idAnimal.getX()+idAnimal.getWidth()+25,idAnimal.getY(),50,25);
        estDangereux=new JCheckBox("est dangereux",controller.getDanger());
        estDangereux.addItemListener(new LockedBoxListener());
        estDangereux.setBounds(150,75,250,25);
        estDangereux.setBackground(Color.WHITE);
        remarqueSoin=new JLabel(controller.getRemarqueGeneraleSoin());
        remarqueSoin.setBounds(5,125,500,25);
        remarqueAnimal=new JLabel(controller.getRemarqueAnimal());
        remarqueAnimal.setBounds(5,100,500,25);
        espece=new JLabel(controller.getEspece());
        espece.setBounds(nomAnimal.getX()+nomAnimal.getWidth()+50,nomAnimal.getY(),45,25);
        race=new JLabel("- "+controller.getRace());
        race.setBounds(espece.getX()+espece.getWidth(),espece.getY(),75,25);

        add(race);
        add(espece);
        add(remarqueAnimal);
        add(remarqueSoin);
        add(idAnimal);
        add(numCell);
        add(nomAnimal);
        add(estDangereux);

    }

    private class TacheContainer extends JScrollPane
    {
        public TacheContainer()
        {
            //setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
            getVerticalScrollBar().setUnitIncrement(20);
            //setBackground(Color.WHITE);
        }
        public void initContainer()  throws NombreInvalideException
        {
            JPanel container=new JPanel();
            container.setBackground(Color.WHITE);
            setViewportView(container);
            container.setLayout(null);
            container.setPreferredSize(new Dimension(950,100*controller.nbTaches()));
            int x=0;
            int width=955;
            int y= 5;
            int height=100;
            //System.out.println("nbTaches: "+controller.nbTaches());
            for(int i=0;i<controller.nbTaches();i++)
            {
                Border border=BorderFactory.createMatteBorder(1,1,1,5,Color.BLUE);
                TachePane tache=new TachePane(i,x,y,width,height);
                tache.setBorder(border);
                container.add(tache);
                y+=tache.getHeight();
            }
            //System.out.println("taille check box array: "+soinsFaits.size());
            //System.out.println("taille remarques array: "+remarques.size());
        }
    }
    private class TachePane extends JPanel
    {
        public TachePane(int index, int x,int y, int width, int height) throws NombreInvalideException
        {
            setLayout(null);
            setBounds(x,y,width,height);
            setBackground(Color.WHITE);
            JLabel description,remarque,heure;
            JCheckBox soinEffectue;
            JTextArea remarqueSoin;

            description=new JLabel(controller.getDescriptionTache(index));
            description.setBounds(5,0,width/2,25);
            //System.out.println("description: "+description.getX()+" "+description.getY()+" "+description.getWidth()+" "+description.getHeight()+" "+description.getText());
            add(description);

            remarque=new JLabel(controller.getRemarque(index));
            remarque.setBounds(description.getX(),description.getY()+description.getHeight(),width/2,25);
            add(remarque);
           // System.out.println("remarque: "+remarque.getText());

            heure=new JLabel(controller.getDateHeure(index));
            heure.setBounds(x+width-100,description.getY(),100,25);
            add(heure);
            //System.out.println(controller.getDateHeure(index));



            soinEffectue=new JCheckBox();
            soinEffectue.setBackground(Color.WHITE);
            soinsFaits.add(soinEffectue);
            soinEffectue.setBounds(x+width-25,description.getY()+70,20,25);
            add(soinEffectue);
            JLabel faitLabel=new JLabel("soin effectué ?");
            faitLabel.setBounds(soinEffectue.getX()-85,soinEffectue.getY(),85,25);
            add(faitLabel);
            //System.out.println("check box: "+soinEffectue.getX()+" "+soinEffectue.getY()+" "+soinEffectue.getWidth()+" "+soinEffectue.getHeight());

            remarqueSoin=new JTextArea();
            remarques.add(remarqueSoin);
            remarqueSoin.setBorder(BorderFactory.createMatteBorder(1,5,1,1,Color.black));
            remarqueSoin.setBounds(300,50,500, 40);
            add(remarqueSoin);
            JLabel remarqueLabel=new JLabel("remarque à ajouter");
            remarqueLabel.setBounds(remarqueSoin.getX()-150,remarqueSoin.getY(),145,20);
            JLabel conditionLabel=new JLabel("obligatoire uniquement si le soin n'est pas effectué");
            conditionLabel.setBounds(remarqueSoin.getX()-300,remarqueLabel.getY()+remarqueLabel.getHeight()+5,300,20);

            add(remarqueLabel);
            add(conditionLabel);
            //System.out.println("remarque soin: "+remarqueSoin.getX()+" "+remarqueSoin.getY()+" "+remarqueSoin.getWidth()+" "+remarqueSoin.getHeight());
        }
    }
    private class SuivantListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class LockedBoxListener implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) {
                estDangereux.setSelected(controller.getDanger());
        }
    }

}
