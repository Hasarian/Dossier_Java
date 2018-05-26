package userInterface;

import Model.Animal;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDesTachesReserveesVetoPanel extends ListeDeTachesPanel {
    public ListeDesTachesReserveesVetoPanel(EcranPrincipalPanel parentPanel, SoignantController user) throws SoignantInexistant {
        super(parentPanel, user);
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.VETORESERVEE);
        setTaskTable( new JTable(model));
        JScrollPane tablePane = new JScrollPane(getTaskTable());
        getTaskTable().setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton start = new JButton("commencer les soins");
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
                    getListController().abandonnerAnimalVeto(new Integer(getListController().getIdDansLaListeVetoReservee(i)));
                }
            }
            catch (ErreurrNull error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
            getTablePane().repaint();
        }
    }
    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer id=Integer.parseInt(getListController().getIdDansLaListeReservee(getTaskTable().getSelectedRow()));
                getParentPanel().getFrame().changePanel(new InfoTachePanel(id,getParentPanel()));
            }
            catch (Exception error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}