package interfaceUtilisateur;

import erreurs.Erreur;
import controlle.ControleAnimal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheSoinParAnimalPanel extends JPanel
{
    private ControleAnimal controleAnimal;
    private JLabel idAnimal, resultats;
    private JList animals;
    private JScrollPane resultat;
    private JButton confirmer;
    private MainFrame frame;

    public RechercheSoinParAnimalPanel(MainFrame frame) throws Erreur {
        try {
            this.frame=frame;
            controleAnimal = new ControleAnimal();
            idAnimal = new JLabel("ID de l'animal dont vous voulez voir les soins :");
            idAnimal.setBounds(50, 25, 50, 25);
            this.add(idAnimal);
            animals = new JList(controleAnimal.getTableauStringAnimaux());
            animals.setVisibleRowCount(2);
            animals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            animals.setSelectedIndex(0);
            animals.setBounds(idAnimal.getX()+idAnimal.getWidth()+5,idAnimal.getY(),idAnimal.getWidth()+2,idAnimal.getHeight());
            this.add(animals);
            confirmer = new JButton("Rechercher");
            confirmer.addActionListener(new ConfirmeButtonListener());
            confirmer.setBounds(animals.getX()+animals.getWidth()+5,animals.getY(),animals.getWidth()+2,animals.getHeight());
            add(confirmer);

            JButton retour=new JButton("retour");
            retour.addActionListener(new CancelButtonListener());
            retour.setBounds(5,600,50,30);
            add(retour);

        }
        catch (Erreur err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
        }
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
            public void actionPerformed(ActionEvent e)  {
                try {
                    if (resultat != null) remove(resultat);
                    String stringAnimal = (String) animals.getSelectedValue();
                    int id = Integer.parseInt(stringAnimal.substring(0, stringAnimal.indexOf(":")));
                    ModelTable model = new ModelTable(controleAnimal.getSoinParAnimal(id));
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
                            "NumOrdonnance",
                            "idSoinMedical",
                            "remarque",
                            "mailVeto",
                            "date",
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
                    case 0: case 1: return Integer.class;
                    default: return String.class;
                }
            }

        }

}
