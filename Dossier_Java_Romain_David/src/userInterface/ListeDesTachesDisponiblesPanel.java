package userInterface;

import Model.Animal;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDesTachesDisponiblesPanel extends ListeDeTachesPanel
{
    MainFrame frame;
    public ListeDesTachesDisponiblesPanel(EcranPrincipalPanel parentPanel, SoignantController user, MainFrame frame) throws SoignantInexistant {
        super(parentPanel,user);
        this.frame = frame;
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.DISPONIBLE);
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
        add(getOpenFile());

    }

    private class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {

            try {
                if(getListController().aucunAnimalReserve())getParentPanel().insertTab(new ListeDesTachesReserveesPanel(getParentPanel(),getUser(),frame ),"personnal list");
                int[] selectedRows=getTaskTable().getSelectedRows();
                int nbRetrait = 0;
                for (int i : selectedRows) {
                    getListController().selectionnerAnimal(new Integer(getListController().getIdDansLaListeDispo(i - nbRetrait)));
                    nbRetrait++;
                }
            }
            catch(SoignantInexistant notFoundError)
            {
                JOptionPane.showMessageDialog(null,notFoundError.getMessage(),"member not found",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"Attribut obligatoir a null",JOptionPane.ERROR_MESSAGE);
            }
            catch (DonneePermanenteErreur error){
                JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
        }

    }
}
