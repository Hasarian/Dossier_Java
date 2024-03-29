package interfaceUtilisateur;

import controle.ControleSoignant;
import erreurs.Erreur;
import modèle.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDesTachesDisponiblesVetoPanel extends ListeDeTachesPanel {
    private MainFrame frame;
    public ListeDesTachesDisponiblesVetoPanel(EcranPrincipalPanel parentPanel, ControleSoignant user, MainFrame frame) throws Erreur {
        super(parentPanel, user);
        this.frame = frame;
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
                if(getListController().aucunAniumalReserveDansLaListeVeto())getParentPanel().insertTab(new ListeDesTachesReserveesVetoPanel(getParentPanel(),getUser(), frame),"liste personnelle vete");
                int[] selectedRows=getTaskTable().getSelectedRows();
                for (int i : selectedRows) {
                    getListController().selectionnerAnimalVeto(i);
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
}
