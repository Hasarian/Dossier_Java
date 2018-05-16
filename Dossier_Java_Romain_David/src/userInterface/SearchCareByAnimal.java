package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.AnimalController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchCareByAnimal extends JPanel
{
    AnimalController animalController;
    JLabel idAnimal, resultats;
    JList animals;
    JScrollPane resultat;
    JButton confirmer;

    public SearchCareByAnimal() throws InexistantCareGiver {
        try {
            animalController = new AnimalController();
            idAnimal = new JLabel("ID de l'animal dont vous voulez voir les soins :");
            idAnimal.setBounds(50, 25, 50, 25);
            this.add(idAnimal);
            animals = new JList(animalController.getStringAnimals());
            animals.setVisibleRowCount(2);
            animals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            animals.setSelectedIndex(0);
            animals.setBounds(idAnimal.getX()+idAnimal.getWidth()+5,idAnimal.getY(),idAnimal.getWidth()+2,idAnimal.getHeight());
            this.add(animals);
            confirmer = new JButton("Rechercher");
            confirmer.addActionListener(new ConfirmeButtonListener());
            confirmer.setBounds(animals.getX()+animals.getWidth()+5,animals.getY(),animals.getWidth()+2,animals.getHeight());
            add(confirmer);

        } catch (BDConnexionError connexionError) {
            JOptionPane.showMessageDialog(null, connexionError.getMessage(), "accès BD", JOptionPane.ERROR_MESSAGE);
        } catch (ErrorNull errorNull) {
            JOptionPane.showMessageDialog(null, errorNull.getMessage(), "db access error", JOptionPane.ERROR_MESSAGE);
        }
    }

        private class ConfirmeButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)  {
                try {
                    if(resultat!=null) remove(resultat);
                    String stringAnimal =(String) animals.getSelectedValue();
                    int id = Integer.parseInt(stringAnimal.substring(0, stringAnimal.indexOf(":")));
                    System.out.println(id);
                    ModelTable model = new ModelTable(animalController.getCareByAnimal(id));
                    JTable animalTable = new JTable(model);
                    resultat = new JScrollPane(animalTable);
                    resultat.setBounds(25, 85, 500, 500);
                    add(resultat);

                    repaint();
                    revalidate();
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
                            "NumOrdonnance",
                            "idSoinMedical",
                            "remarque",
                            "mailVeto",
                            "date",
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
