package userInterface;

import erreurs.Erreur;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreurFormat.ErreurrNull;
import uIController.AnimalController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class RechercheAnimalPanel extends JPanel {
    private JLabel dateDebutLabel, dateFinLabel;
    private JSpinner dateDebut, dateFin;
    private JScrollPane resultat;
    private JButton confirmer;
    private AnimalController animalController;
    private MainFrame frame;

    public RechercheAnimalPanel(MainFrame frame) throws Erreur {
        this.frame=frame;
        setLayout(null);
        setBackground(Color.WHITE);
        animalController = new AnimalController();
        dateDebutLabel = new JLabel("Date d'arrivée qui servira de borne inférieure à la recherche");
        dateDebutLabel.setBounds(50,0,350,20);
        this.add(dateDebutLabel);
        dateDebut = new JSpinner(new SpinnerDateModel());
		dateDebut.setEditor(new JSpinner.DateEditor(dateDebut, "dd/MM/yyyy"));
		dateDebut.setBounds(dateDebutLabel.getX()+dateDebutLabel.getWidth()+5,dateDebutLabel.getY(),100,dateDebutLabel.getHeight());
		this.add(dateDebut);

		dateFinLabel = new JLabel("Date d'arrivée qui servira de borne suppérieure à la recherche");
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


        JButton retour=new JButton("retour");
        retour.addActionListener(new CancelButtonListener());
        retour.setBounds(5,600,50,30);
        add(retour);
    }
    private class CancelButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            frame.changePanel();
        }
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
                ModelTable model = new ModelTable(animalController.getAnimauxEntreDates(dateDebTemp, dateFinTemp));
                JTable animalTable = new JTable(model);
                resultat = new JScrollPane(animalTable);
                resultat.setBounds(25, 85, 500, 500);
                add(resultat);
                repaint();
                revalidate();

            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ModelTable extends AbstractTableModel
    {
        private ArrayList<ArrayList<Object>> data;
        private String [] columnNames=
                {
                        "Nom", "est dangereux", "num cellule", "espece", "race", "date arrive"
                };

        public ModelTable(ArrayList<ArrayList<Object>> data)
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

        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex)
            {
                case 2: return Integer.class;
                case 1: return Boolean.class;
                default: return String.class;
            }
        }
    }
}