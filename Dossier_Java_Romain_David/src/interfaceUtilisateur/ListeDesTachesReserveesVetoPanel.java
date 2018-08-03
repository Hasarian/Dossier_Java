package interfaceUtilisateur;

import controlle.ControleSoignant;
import erreurs.Erreur;
import modèle.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDesTachesReserveesVetoPanel extends ListeDeTachesPanel {
    MainFrame frame;
    ListeDeTachesPanel listeDeTachesPanel;
    public ListeDesTachesReserveesVetoPanel(EcranPrincipalPanel parentPanel, ControleSoignant user, MainFrame frame) throws Erreur {
        super(parentPanel, user);
        this.listeDeTachesPanel = this;
        this.frame = frame;
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.VETORESERVEE);
        setTaskTable( new JTable(model));
        JScrollPane tablePane = new JScrollPane(getTaskTable());
        getTaskTable().setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton start = new JButton("commencer les soins");
        start.addActionListener(new StartListener());
        start.setBounds(760, openFile.getY(), 200, 60);


        JButton unselect=new JButton("abandonner les soins");
        unselect.addActionListener(new AbandonListener());
        unselect.setBounds(start.getX(),start.getY()+start.getHeight()+15,150,50);

        add(start);
        add(unselect);
    }
    private class AbandonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows=getTaskTable().getSelectedRows();
            try {
                for (int i : selectedRows) {
                    getListController().abandonnerAnimalVeto(getListController().getIdDansLaListeVetoReservee(i));
                }
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
            getTablePane().repaint();
        }
    }
    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer id=getListController().getIdDansLaListeVetoReservee(getTaskTable().getSelectedRow());
                getParentPanel().getFrame().changePanel(new InfoTachePanel(id,getParentPanel(), frame, listeDeTachesPanel));
            }
            catch (Exception error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
