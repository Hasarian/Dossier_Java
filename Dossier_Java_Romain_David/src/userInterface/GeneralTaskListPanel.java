package userInterface;

import Model.Animal;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import sun.security.util.Length;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GeneralTaskListPanel extends TaskListPanel
{
    public GeneralTaskListPanel(DashBoardPane parentPanel, CareGiverController user) throws InexistantCareGiver {
        super(parentPanel,user);

        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.DISPONIBLE);
        setTaskTable( new JTable(model));
        JScrollPane tablePane = new JScrollPane(getTaskTable());
        getTaskTable().setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton select = new JButton("select");
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
                if(getListController().personnalListIsNull())getParentPanel().insertTab(new PersonnalTaskListPanel(getParentPanel(),getUser()),"personnal list");
                int[] selectedRows=getTaskTable().getSelectedRows();
                for (int i : selectedRows) {
                    getListController().selectTask(new Integer(getListController().getAvailbleId(i)));
                }
            }
            catch(InexistantCareGiver notFoundError)
            {
                JOptionPane.showMessageDialog(null,notFoundError.getMessage(),"member not found",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErrorNull error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
        }

    }
}
