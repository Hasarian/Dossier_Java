package userInterface;

import uIController.CareGiverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VetoTaskListPanel extends TaskListPanel {
    public VetoTaskListPanel(DashBoardPane parentPanel, CareGiverController user) {
        super(parentPanel, user);
        TaskTableModel model = new TaskTableModel(getListController().getVetoAvailableData());
        JTable taskTable = new JTable(model);
        JScrollPane tablePane = new JScrollPane(taskTable);
        taskTable.setFillsViewportHeight(true);
        tablePane.setBounds(5, 5, 975, 300);
        setTablePan(tablePane);
        add(getTablePane());


        JButton openFile = new JButton("consult");
        openFile.setBounds(20, getInfoLabel3().getY() + getInfoLabel3().getHeight() + 20, 200, 60);
        JButton select = new JButton("select");
        select.setBounds(760, openFile.getY(), 200, 60);
        select.addActionListener(new SelectListener());

        add(select);
        add(openFile);
    }
    private class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            getTaskTable().clearSelection();
        }
    }
}
