package userInterface;

import sun.security.util.Length;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralTaskListPanel extends TaskListPanel
{
    public GeneralTaskListPanel(DashBoardPane parentPanel)
    {
        super(parentPanel);
        JButton ouvrir=new JButton("consult");
        ouvrir.setBounds(20,getInfoLabel3().getY()+getInfoLabel3().getHeight()+20,200,60);
        JButton select=new JButton("select");
        select.setBounds(760,ouvrir.getY(),200,60);
        select.addActionListener(new SelectListener());

        add(select);
        add(ouvrir);
    }
    private class SelectListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] newData=new Object[getTaskTable().getSelectedRows().length][TaskListPanel.COLUMNNUMBER];
            for(int i=0;i<newData.length;i++)
                for(int j=0;j<TaskListPanel.COLUMNNUMBER;j++)
                {
                    newData[i][j]=getTableData()[getTaskTable().getSelectedRows()[i]][j];
                }
                getParentPanel().generatePersonnalList(newData);
        }
    }
}
