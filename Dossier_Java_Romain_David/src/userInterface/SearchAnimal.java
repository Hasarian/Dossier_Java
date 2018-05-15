package userInterface;

import Model.CareGiver;
import com.sun.org.apache.xpath.internal.operations.Mod;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.AnimalController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
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
    private AnimalController animalController;

    public SearchAnimal() throws BDConnexionError,ErrorNull, InexistantCareGiver{
        setLayout(null);
        setBackground(Color.WHITE);
        animalController = new AnimalController();
        dateDebutLabel = new JLabel("Date qui servira de borne inférieure à la recherche");
        dateDebutLabel.setBounds(50,0,300,20);
        this.add(dateDebutLabel);
        dateDebut = new JSpinner(new SpinnerDateModel());
		dateDebut.setEditor(new JSpinner.DateEditor(dateDebut, "dd/MM/yyyy"));
		dateDebut.setBounds(dateDebutLabel.getX()+dateDebutLabel.getWidth()+5,dateDebutLabel.getY(),100,dateDebutLabel.getHeight());
		this.add(dateDebut);

		dateFinLabel = new JLabel("Date qui servira de borne suppérieure à la recherche");
		dateFinLabel.setBounds(dateDebutLabel.getX(),dateDebutLabel.getY()+dateDebutLabel.getHeight()+5,dateDebutLabel.getWidth(),dateDebutLabel.getHeight());
		this.add(dateFinLabel);
        dateFin = new JSpinner(new SpinnerDateModel());
        dateFin.setEditor(new JSpinner.DateEditor(dateFin, "dd/MM/yyyy"));
        dateFin.setBounds(dateDebut.getX(),dateFinLabel.getY(),dateDebut.getWidth(),dateDebut.getHeight());
        this.add(dateFin);

        //resultat = new JScrollPane(resultat);
        confirmer = new JButton("Rechercher");
        confirmer.addActionListener(new ConfirmeButtonListener());
        confirmer.setBounds(dateFin.getX()+dateFin.getWidth()+5,dateFin.getY(),dateFin.getWidth()+2,dateFin.getHeight());
        add(confirmer);
    }


    private class ConfirmeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                if(resultat!=null) remove(resultat);
                GregorianCalendar dateDebTemp = new GregorianCalendar();
                dateDebTemp.setTime((Date) dateDebut.getValue());
                GregorianCalendar dateFinTemp = new GregorianCalendar();
                dateFinTemp.setTime((Date) dateFin.getValue());
                ModelTable model = new ModelTable(animalController.getAnimalsBetweenDates(dateDebTemp, dateFinTemp));
                resultat = new JTable(model);
                resultat.setBounds(25, 85, 500, 500);
                add(resultat);
                //repaint();
            }
        catch(BDConnexionError connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(), "accès BD", JOptionPane.ERROR_MESSAGE);
            }
			catch(ErrorNull errorNull)
            {
                JOptionPane.showMessageDialog(null, errorNull.getMessage(), "db access error", JOptionPane.ERROR_MESSAGE);
            }
            catch(InexistantCareGiver unknown)
            {
                JOptionPane.showMessageDialog(null,unknown.getMessage(),"unknown member",JOptionPane.ERROR_MESSAGE);
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
                        "race",
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