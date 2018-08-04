package interfaceUtilisateur;

import erreurs.Erreur;
import modèle.Animal;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreurFormat.ErreurrNull;
import controle.ControleSoignant;
import controle.ControleListesAnimaux;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeDeTachesPanel extends JPanel
{
    private EcranPrincipalPanel parentPanel;
    private ControleSoignant user;

    public ControleSoignant getUser() {
        return user;
    }

    public EcranPrincipalPanel getParentPanel() {
        return parentPanel;
    }

    private ControleListesAnimaux listController;

    public ControleListesAnimaux getListController() {
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

    public ListeDeTachesPanel(EcranPrincipalPanel parentPanel, ControleSoignant user) throws Erreur
    {
        this.user=user;
        try {
            listController = new ControleListesAnimaux();
        }
        catch (DonneePermanenteErreur connexionError)
        {
            JOptionPane.showMessageDialog(null,connexionError.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
        }
        catch(ErreurrNull error)
        {
            JOptionPane.showMessageDialog(null,error.getMessage(),"attribute error",JOptionPane.ERROR_MESSAGE);
        }
            this.parentPanel = parentPanel;
            setLayout(null);
            setBackground(Color.WHITE);
            //add(tablePane);

        openFile = new JButton("consulter");
        openFile.setBounds(20, 400, 200, 60);
        openFile.addActionListener(new ConsultListener());
        add(openFile);

    }

    private class ConsultListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(getTaskTable().getValueAt(getTaskTable().getSelectedRow(),0));
            Integer id= Integer.parseInt(getTaskTable().getValueAt(getTaskTable().getSelectedRow(),1).toString());
            InfoAnimalFrame info=new InfoAnimalFrame(id);
            getTaskTable().clearSelection();
        }
    }


    protected class TaskTableModel extends AbstractTableModel
    {
        private Animal.EtatSoin etat;
        private String [] columnNames=
                {
                        "nom de l'animal",
                        "id de l'animal",
                        "numéro de cellule",
                        "espece",
                        "race",
                        "dangerosité"
                };

        public TaskTableModel(Animal.EtatSoin etat)
        {
            this.etat=etat;
        }
        public int getRowCount()  {
            try {
                switch (etat) {
                    case DISPONIBLE:
                        return listController.nombreDeLignesListeDisponible();
                    case RESERVEE:
                        return listController.nombreDeLignesListeReservee();
                    case VETODISPO:
                        return listController.nombreLignesListeVetoDispo();
                    case VETORESERVEE:
                        return listController.nombreLignesListeVetoReservee();
                    default:
                        return 0;
                }
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            try{
            switch (etat) {
                case DISPONIBLE:
                    return listController.getDonneeDansListeDisponible(rowIndex,columnIndex);
                case RESERVEE:
                    return listController.getDonneeDansListeReservee(rowIndex,columnIndex);
                case VETODISPO:
                    return listController.getDonneeListeVetoDispo(rowIndex,columnIndex);
                case VETORESERVEE:
                    return listController.getDonneeListVetoReservee(rowIndex,columnIndex);
                    default: return "données inexistantes";
            }
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
                return "données inexistantes";
            }
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex)
            {

                case 1:  case 2:
                    return Integer.class ;
                case 5:
                    return Boolean.class;
                default: return String.class;
            }
        }
    }
}
