package userInterface;

import Model.CareGiver;
import com.sun.org.apache.xpath.internal.operations.Mod;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.AnimalController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchAnimal extends JPanel {
    private JLabel dateDebutLabel, dateFinLabel;
    private JSpinner dateDebut, dateFin;
    private JTable resultat;
    private JButton confirmer;
    private CareGiver user;
    private AnimalController animalController;

    public SearchAnimal() throws BDConnexionError{
        animalController = new AnimalController();
        dateDebutLabel = new JLabel("Date qui servira de borne inférieure à la recherche");
        dateDebutLabel.setBounds(50,50,50,20);
        this.add(dateDebutLabel);
        dateDebut = new JSpinner(new SpinnerDateModel());
		dateDebut.setEditor(new JSpinner.DateEditor(dateDebut, "dd/MM/yyyy"));
		dateDebut.setBounds(110,50,50,20);
		this.add(dateDebut);

		dateFinLabel = new JLabel("Date qui servira de borne suppérieure à la recherche");
		dateFinLabel.setBounds(50,70,50,20);
		this.add(dateFinLabel);
        dateFin = new JSpinner(new SpinnerDateModel());
        dateFin.setEditor(new JSpinner.DateEditor(dateFin, "dd/MM/yyyy"));
        dateFin.setBounds(110,70,50,20);
        this.add(dateFin);

        //resultat = new JScrollPane(resultat);
        confirmer = new JButton("Rechercher");
        confirmer.addActionListener(new ConfirmeButtonListener());
    }


    private class ConfirmeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                GregorianCalendar dateDebTemp = new GregorianCalendar();
                dateDebTemp.setTime((Date) dateDebut.getValue());
                GregorianCalendar dateFinTemp = new GregorianCalendar();
                dateFinTemp.setTime((Date) dateFin.getValue());
                ModelTable model = new ModelTable(animalController.getAnimalsBetweenDate(dateDebTemp, dateFinTemp));
                resultat = new JTable(model);
                resultat.setBounds(25, 85, 500, 500);
            }
        catch(BDConnexionError connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(), "accès BD", JOptionPane.ERROR_MESSAGE);
            }
			catch(ErrorNull errorNull)
            {
                JOptionPane.showMessageDialog(null, errorNull.getMessage(), "db access error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ModelTable extends AbstractTableModel
    {
        private ArrayList<ArrayList<String>> data;
        private String [] columnNames=
                {
                        "Nom",
                        "est dangereux",
                        "num cellule",
                        "espece",
                        "race",
                        "milieu de vie",
                        "type deplacement",
                        "vaccin",
                        "date vaccin",
                        "date arrive"
                };

        public ModelTable(ArrayList<ArrayList<String>> data)
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
