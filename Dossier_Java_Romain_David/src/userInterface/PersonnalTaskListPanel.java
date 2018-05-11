package userInterface;

import uIController.CareGiverController;

import javax.swing.*;

public class PersonnalTaskListPanel extends TaskListPanel
{
    public PersonnalTaskListPanel(DashBoardPane parentPanel, CareGiverController user) {
        super(parentPanel, user);
        TaskTableModel model = new TaskTableModel(getListController().getPersonnalDatz());
        JTable taskTable = new JTable(model);
        JScrollPane tablePane = new JScrollPane(taskTable);
        taskTable.setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton openFile = new JButton("consult");
        openFile.setBounds(20, getInfoLabel3().getY() + getInfoLabel3().getHeight() + 20, 200, 60);
        JButton start = new JButton("start care");
        start.setBounds(760, openFile.getY(), 200, 60);
        JButton unselect=new JButton("abandon task");
        unselect.setBounds(start.getX(),start.getY()+start.getHeight()+15,150,50);

        add(start);
        add(openFile);
        add(unselect);
    }
}
