package interfaceUtilisateur;

import erreurs.Erreur;
import erreurs.erreurFormat.MauvaiseTailleString;
import erreurs.erreurFormat.NombreInvalideException;
import controle.ControleListesAnimaux;
import controle.ControleTache;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class InfoTachePanel extends JPanel
{
    private MainFrame mainFrame;
    private ControleTache controller;
    private ArrayList<JTextArea> remarques;
    private ArrayList<JCheckBox> soinsFaits;
    private JCheckBox pourLeVeto,estDangereux;
    private ListeDeTachesPanel listeDeTachesPanel;
    public InfoTachePanel(Integer id,JPanel parentPanel,MainFrame mainFrame, ListeDeTachesPanel listeDeTachesPanel) throws Erreur
    {
        this.listeDeTachesPanel = listeDeTachesPanel;
        this.mainFrame=mainFrame;
        setLayout(null);
        setBounds(2,2,parentPanel.getWidth()-15,parentPanel.getHeight()-15);
        remarques=new ArrayList<JTextArea>();
        soinsFaits=new ArrayList<JCheckBox>();
        setBackground(Color.WHITE);
        controller=new ControleTache(id);
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
            try{
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
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
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
            try{
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
            JLabel nbCaractere= new JLabel("restant: 140 caractères");
            remarqueSoin.getDocument().addDocumentListener(new NbCaracteresListener(remarqueSoin,nbCaractere));
            remarques.add(remarqueSoin);
            remarqueSoin.setBorder(BorderFactory.createMatteBorder(1,5,1,1,Color.black));
            nbCaractere.setBounds(300,25,150,25);
            remarqueSoin.setBounds(300,50,500, 40);
            add(remarqueSoin);
            add(nbCaractere);
            JLabel remarqueLabel=new JLabel("remarque à ajouter");
            remarqueLabel.setBounds(remarqueSoin.getX()-150,remarqueSoin.getY(),145,20);
            JLabel conditionLabel=new JLabel("obligatoire uniquement si le soin n'est pas effectué");
            conditionLabel.setBounds(remarqueSoin.getX()-300,remarqueLabel.getY()+remarqueLabel.getHeight()+5,300,20);
            add(remarqueLabel);
            add(conditionLabel);
            //System.out.println("remarque soin: "+remarqueSoin.getX()+" "+remarqueSoin.getY()+" "+remarqueSoin.getWidth()+" "+remarqueSoin.getHeight());
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class SuivantListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                ArrayList<Boolean> sontFaits = new ArrayList<Boolean>();
                ArrayList<String> remarquesFaites = new ArrayList<String>();
                for(int i=0;i<controller.nbTaches();i++)
                {
                    sontFaits.add(soinsFaits.get(i).isSelected());
                    if(remarques.get(i).getText()!=null&&remarques.get(i).getText().length()>140) throw new MauvaiseTailleString("remarque n°"+i,remarques.get(i).getText().length(),140);
                    remarquesFaites.add(remarques.get(i).getText());
                }
                int choix=JOptionPane.showConfirmDialog(null,"confirmez-vous les soins ?","confirmation",JOptionPane.YES_NO_OPTION);
                if(choix==JOptionPane.YES_OPTION) {
                    controller.faireSoin(sontFaits, remarquesFaites, pourLeVeto.isSelected());
                    mainFrame.setPanelActuel(null);
                    mainFrame.changePanel();
                    ControleListesAnimaux listesAnimauxController = new ControleListesAnimaux();
                    if(listesAnimauxController.aucunAnimalReserve())mainFrame.getBasePanel().removeTab(listeDeTachesPanel);
                }
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }

        }
    }
    private class LockedBoxListener implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) {
            try{
            estDangereux.setSelected(controller.getDanger());
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class NbCaracteresListener implements DocumentListener
    {
        public JTextArea zone;
        public JLabel nbCaracLabel;

        public NbCaracteresListener(JTextArea aVerifier,JLabel aModifier)
        {
            zone=aVerifier;
            nbCaracLabel=aModifier;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            int caracteresRestant=140-zone.getText().length();
            nbCaracLabel.setText("restant: "+caracteresRestant+" caractères");
            if(caracteresRestant<0) nbCaracLabel.setForeground(Color.RED);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int caracteresRestant=140-zone.getText().length();
            nbCaracLabel.setText("restant: "+caracteresRestant+" caractères");
            if(caracteresRestant>=0)nbCaracLabel.setForeground(Color.BLACK);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

}
