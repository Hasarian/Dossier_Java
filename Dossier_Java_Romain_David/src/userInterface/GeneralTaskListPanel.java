package userInterface;

import sun.security.util.Length;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GeneralTaskListPanel extends TaskListPanel {
    public GeneralTaskListPanel(DashBoardPane parentPanel) {
        super(parentPanel);
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
            ArrayList<ArrayList<String>> newData = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < getTaskTable().getSelectedRows().length; i++)
            {
                System.out.println( getTaskTable().getSelectedRows().length);
                newData.add(getTableData().get(getTaskTable().getSelectedRows()[i]));
            }
            getParentPanel().generatePersonnalList(newData);
            removeData(newData);
            getTaskTable().clearSelection();
        }
    }
}
