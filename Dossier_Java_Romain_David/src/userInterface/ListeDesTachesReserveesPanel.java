package userInterface;

import erreurs.Erreur;
import model.Animal;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDesTachesReserveesPanel extends ListeDeTachesPanel
{
    ListeDesTachesReserveesPanel thisPanel;
    MainFrame frame;
    public ListeDesTachesReserveesPanel(EcranPrincipalPanel parentPanel, SoignantController user,MainFrame frame) throws Erreur {
        super(parentPanel, user);
        thisPanel=this;
        this.frame=frame;
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.RESERVEE);
        setTaskTable( new JTable(model));
        JScrollPane tablePane = new JScrollPane(getTaskTable());
        getTaskTable().setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton start = new JButton("commencer les soins");
        start.setBounds(760, openFile.getY(), 200, 30);
        start.addActionListener(new StartListener());
        //commencer les soins

        JButton unselect=new JButton("abandonner les soins");
        unselect.addActionListener(new AbandonListener());
        unselect.setBounds(start.getX(),start.getY()+start.getHeight(),200,30);

        add(start);
        add(unselect);
    }
    private class AbandonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows=getTaskTable().getSelectedRows();
            try {
                for (int i : selectedRows) {

                    getListController().abandonnerAnimal(getListController().getIdDansLaListeReservee(i));
                }
            getTaskTable().clearSelection();
            if(getListController().aucunAnimalReserve())getParentPanel().removeTab(thisPanel);
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
            getTablePane().repaint();
        }
    }
    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int choix=JOptionPane.showConfirmDialog(null,"vous ne pourrez plus revenir en arrière avant d'avoir fini. Continuer ?","confirmation",JOptionPane.YES_NO_OPTION);
                if(choix==JOptionPane.YES_OPTION) {
                    Integer id = getListController().getIdDansLaListeReservee(getTaskTable().getSelectedRow());
                    InfoTachePanel tache = new InfoTachePanel(id, getParentPanel(), frame, thisPanel);
                    getParentPanel().getFrame().changePanel(tache);
                    frame.setPanelActuel(tache);
                }
            }
            catch (Exception error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
