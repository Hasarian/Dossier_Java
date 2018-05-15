package userInterface;

import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;
import uIController.ListsControllerAnimal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskListPanel extends JPanel
{
    private DashBoardPane parentPanel;
    private CareGiverController user;

    public CareGiverController getUser() {
        return user;
    }

    public DashBoardPane getParentPanel() {
        return parentPanel;
    }

    private ListsControllerAnimal listController;

    public ListsControllerAnimal getListController() {
        return listController;
    }

    private JScrollPane tablePane;
    public void setTablePan(JScrollPane tablePane){this.tablePane=tablePane;}

    public JScrollPane getTablePane() {
        return tablePane;
    }

    private JLabel infoLabel,infoLabel2,infoLabel3;
    private JTable taskTable;
    public void setTaskTable(JTable table){taskTable=table;}
    public JTable getTaskTable() {
        return taskTable;
    }

    public JLabel getInfoLabel3() {
        return infoLabel3;
    }

    JButton openFile;

    public JButton getOpenFile() {
        return openFile;
    }

    public TaskListPanel(DashBoardPane parentPanel, CareGiverController user) throws InexistantCareGiver
    {
        this.user=user;
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
            this.parentPanel = parentPanel;
            setLayout(null);
            setBackground(Color.WHITE);
            //add(tablePane);

        openFile = new JButton("consult");
        openFile.setBounds(20, 400, 200, 60);
        openFile.addActionListener(new ConsultListener());
        add(openFile);

        infoLabel=new JLabel("use click to select then click a button below to act");
        infoLabel.setBounds(this.getX(),openFile.getY()-(openFile.getHeight()),350,15);
        infoLabel2=new JLabel("use ctrl+click to select multiple tasks");
        infoLabel2.setBounds(infoLabel.getX(),infoLabel.getY()+infoLabel.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());
        infoLabel3=new JLabel("use shift+click to select all the tasks between two selections");
        infoLabel3.setBounds(infoLabel.getX(),infoLabel2.getY()+infoLabel2.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());

        add(infoLabel);
        add(infoLabel2);
        add(infoLabel3);

    }

    private class ConsultListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(getTaskTable().getValueAt(getTaskTable().getSelectedRow(),0));
            Integer id= Integer.parseInt(getTaskTable().getValueAt(getTaskTable().getSelectedRow(),1).toString());
            AnimalInfoFrame info=new AnimalInfoFrame(listController,id);
            getTaskTable().clearSelection();
        }
    }


    protected class TaskTableModel extends AbstractTableModel
    {
        private Animal.EtatSoin etat;
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
                    return listController.availAbleDataCount();
                case RESERVEE:
                    return listController.personnalDataCount();
                case VETODISPO:
                    return listController.getVetoAvailableCount();
                case VETORESERVEE:
                    return listController.getVetoPersonnalDataCount();
                    default: return 0;
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getValueAt(int rowIndex, int columnIndex) {
            switch (etat) {
                case DISPONIBLE:
                    return listController.getAvailableData(rowIndex,columnIndex);
                case RESERVEE:
                    return listController.getPersonnalData(rowIndex,columnIndex);
                case VETODISPO:
                    return listController.getVetoAvailableData(rowIndex,columnIndex);
                case VETORESERVEE:
                    return listController.getVetoPersonnalData(rowIndex,columnIndex);
                    default: return "inexistant data";
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
