package userInterface;

import Model.Animal;
import erreurs.ErrorNull;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VetoTaskListPanel extends TaskListPanel {
    public VetoTaskListPanel(DashBoardPane parentPanel, CareGiverController user) {
        super(parentPanel, user);
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.VETODISPO);
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
    }
    private class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(getListController().vetoPersonnalListIsNull())getParentPanel().insertTab(new VetoPersonnalListPanel(getParentPanel(),getUser()),"liste personnelle vete");
            int[] selectedRows=getTaskTable().getSelectedRows();
            try {
                for (int i : selectedRows) {
                    getListController().selectVetoTask(new Integer(getListController().getVetoAvailbleId(i)));
                }
            }
            catch (ErrorNull error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
            getTablePane().repaint();
        }
    }
}
