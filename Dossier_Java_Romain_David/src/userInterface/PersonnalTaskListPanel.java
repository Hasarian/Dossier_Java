package userInterface;

import Model.Animal;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonnalTaskListPanel extends TaskListPanel
{
    PersonnalTaskListPanel thisPanel;
    public PersonnalTaskListPanel(DashBoardPane parentPanel, CareGiverController user) throws InexistantCareGiver{
        super(parentPanel, user);
        thisPanel=this;
        TaskTableModel model = new TaskTableModel(Animal.EtatSoin.RESERVEE);
        setTaskTable( new JTable(model));
        JScrollPane tablePane = new JScrollPane(getTaskTable());
        getTaskTable().setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton start = new JButton("start care");
        start.setBounds(760, openFile.getY(), 200, 30);
        start.addActionListener(new StartListener());
        //commencer les soins

        JButton unselect=new JButton("abandon task");
        unselect.addActionListener(new AbandonListener());
        unselect.setBounds(start.getX(),start.getY()+start.getHeight(),150,30);

        add(start);
        add(unselect);
    }
    private class AbandonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows=getTaskTable().getSelectedRows();
            try {
                for (int i : selectedRows) {
                    getListController().unselectTask(new Integer(getListController().getSelectedId(i)));
                }
            }
            catch (ErrorNull error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            getTaskTable().clearSelection();
            if(getListController().personnalListIsNull())getParentPanel().removeTab(thisPanel);
            getTablePane().repaint();
        }
    }
    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id=getListController().getSelectedId(getTaskTable().getSelectedRow());
                getParentPanel().getFrame().changePanel(new TaskPanel(id,getUser()));
            }
            catch (Exception error)
            {
                JOptionPane.showMessageDialog(null,error.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
