package userInterface;

import sun.security.util.Length;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GeneralTaskListPanel extends TaskListPanel {
    public GeneralTaskListPanel(DashBoardPane parentPanel, CareGiverController user) {
        super(parentPanel,user);
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
