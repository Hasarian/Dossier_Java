package userInterface;

import Model.Animal;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDesTachesDisponiblesVetoPanel extends ListeDeTachesPanel {
    public ListeDesTachesDisponiblesVetoPanel(EcranPrincipalPanel parentPanel, SoignantController user) throws SoignantInexistant {
        super(parentPanel, user);
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.VETODISPO);
        setTaskTable( new JTable(model));
        JScrollPane tablePane = new JScrollPane(getTaskTable());
        getTaskTable().setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton select = new JButton("selectionner");
        select.setBounds(760, openFile.getY(), 200, 60);
        select.addActionListener(new SelectListener());

        add(select);
    }
    private class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(getListController().aucunAniumalReserveDansLaListeVeto())getParentPanel().insertTab(new ListeDesTachesReserveesVetoPanel(getParentPanel(),getUser()),"liste personnelle vete");
                int[] selectedRows=getTaskTable().getSelectedRows();
                for (int i : selectedRows) {
                    getListController().selectionnerAnimalVeto(new Integer(getListController().getIdDansLaListeVetoDispo(i)));
                }
            }
            catch (ErreurrNull error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            catch(SoignantInexistant unknown)
            {
                JOptionPane.showMessageDialog(null,unknown.getMessage(),"unknown member",JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
            getTablePane().repaint();
        }
    }
}