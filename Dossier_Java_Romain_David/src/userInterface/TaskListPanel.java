package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;
import uIController.ListsController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class TaskListPanel extends JPanel
{
    private DashBoardPane parentPanel;
    private ListsController listController;

    public DashBoardPane getParentPanel() {
        return parentPanel;
    }

    private JScrollPane tablePane;
    private JLabel infoLabel,infoLabel2,infoLabel3;
    private JTable taskTable;

    public JTable getTaskTable() {
        return taskTable;
    }

    public JLabel getInfoLabel3() {
        return infoLabel3;
    }

    public TaskListPanel(DashBoardPane parentPanel, CareGiverController user)
    {
        try {
            listController = new ListsController(user);
        }
        catch (BDConnexionError connexionError)
        {
            JOptionPane.showMessageDialog(null,connexionError.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
        }
        catch(ErrorNull error)
        {
            JOptionPane.showMessageDialog(null,error.getMessage(),"attribute error",JOptionPane.ERROR_MESSAGE);
        }
            this.parentPanel = parentPanel;
            setLayout(null);
            setBackground(Color.WHITE);
            TaskTableModel model = new TaskTableModel(listController.getAvailableData());
            taskTable = new JTable(model);
            tablePane = new JScrollPane(taskTable);
            taskTable.setFillsViewportHeight(true);
            tablePane.setBounds(5, 5, 975, 300);
            add(tablePane);


        infoLabel=new JLabel("use click to select then click a button below to act");
        infoLabel.setBounds(tablePane.getX(),tablePane.getY()+tablePane.getHeight(),350,15);
        infoLabel2=new JLabel("use ctrl+click to select multiple tasks");
        infoLabel2.setBounds(infoLabel.getX(),infoLabel.getY()+infoLabel.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());
        infoLabel3=new JLabel("use shift+click to select all the tasks between two selections");
        infoLabel3.setBounds(infoLabel.getX(),infoLabel2.getY()+infoLabel2.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());

        add(infoLabel);
        add(infoLabel2);
        add(infoLabel3);

    }


    private class TaskTableModel extends AbstractTableModel
    {
        private ArrayList<ArrayList<String>> data;
        private String [] columnNames=
                {
                        "name",
                        "id",
                        "cell number",
                        "species",
                };

        public TaskTableModel(ArrayList<ArrayList<String>> data)
        {
            this.data=data;
        }
        public int getRowCount() {
            return data.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex).get(columnIndex);
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

    }
}
