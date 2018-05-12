package userInterface;

import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;
import uIController.ListsControllerAnimal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TaskListPanel extends JPanel
{
    //private DashBoardPane parentPanel;
    private ListsControllerAnimal listController;


    private JScrollPane tablePane;
    public void setTablePan(JScrollPane tablePane){this.tablePane=tablePane;}

    public JScrollPane getTablePane() {
        return tablePane;
    }

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
            listController = ListsControllerAnimal.obtenirListController(user);
        }
        catch (BDConnexionError connexionError)
        {
            JOptionPane.showMessageDialog(null,connexionError.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
        }
        catch(ErrorNull error)
        {
            JOptionPane.showMessageDialog(null,error.getMessage(),"attribute error",JOptionPane.ERROR_MESSAGE);
        }
            //this.parentPanel = parentPanel;
            setLayout(null);
            setBackground(Color.WHITE);
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


    protected class TaskTableModel extends AbstractTableModel
    {
        private Animal.EtatSoin etat;
        private ListsControllerAnimal listControl;
        private String [] columnNames=
                {
                        "name",
                        "id",
                        "cell number",
                        "species",
                };

        public TaskTableModel(Animal.EtatSoin etat)
        {
            this.etat=etat;
        }
        public int getRowCount() {
            switch (etat)
            {
                case DISPONIBLE:
                    return listController.getAvailableData().size();
                case RESERVEE:
                    return listController.getPersonnalDatz().size();
                case VETODISPO:
                    return listController.getVetoAvailableData().size();
                case VETORESERVEE:
                    return listController.getVetoPersonnalDatz().size();
                    default: return 0;
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (etat) {
                case DISPONIBLE:
                    return listController.getAvailableData().get(rowIndex).get(columnIndex);
                case RESERVEE:
                    return listController.getPersonnalDatz().get(rowIndex).get(columnIndex);
                case VETODISPO:
                    return listController.getVetoAvailableData().get(rowIndex).get(columnIndex);
                case VETORESERVEE:
                    return listController.getVetoPersonnalDatz().get(rowIndex).get(columnIndex);
                    default: return null;
            }
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
