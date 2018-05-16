package userInterface;

import Model.CareGiver;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchTaskByGiver extends JPanel {
    private MainFrame mainFrame;
    private JList idGiver;
    private JButton searchButton;
    private JButton backButton;
    private CareGiverController careGiverControl;
    private JScrollPane tablePane;
    private JLabel idGiverLabel;
    public SearchTaskByGiver(MainFrame mainFrame) throws BDConnexionError
    {
        careGiverControl=new CareGiverController();
        this.mainFrame=mainFrame;
        setBackground(Color.WHITE);
        setLayout(null);
        idGiverLabel= new JLabel("mail du soignant:");
        idGiverLabel.setBounds(50,getY(),125,20);
        idGiver=new JList(careGiverControl.getallUsers());
        idGiver.setVisibleRowCount(1);
        idGiver.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        idGiver.setSelectedIndex(0);
        idGiver.setBounds(idGiverLabel.getX()+idGiverLabel.getWidth(),idGiverLabel.getY(),150,idGiverLabel.getHeight()*3);
        add(idGiverLabel);
        add(idGiver);

        searchButton=new JButton("search");
        searchButton.setBounds(idGiver.getX(),idGiver.getY()+idGiver.getHeight()+10,idGiverLabel.getWidth(),idGiverLabel.getHeight());
        searchButton.addActionListener(new SearchListener());
        add(searchButton);

        backButton = new JButton("back");
        backButton.setBounds(getX()+getWidth()-50,idGiverLabel.getY(),35,idGiverLabel.getHeight());
        backButton.addActionListener(new BackListener());
        add(backButton);
    }
    private class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
                mainFrame.changePanel();
        }
    }
    private class SearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {

                if (tablePane != null){
                    //remove(tablePane);
                    removeAll();
                    add(idGiverLabel);
                    add(idGiver);
                    add(searchButton);
                    add(backButton);
                }
                String mail = idGiver.getSelectedValue().toString();
                JLabel nom, numTel, estbenevole, codePosal;
                nom = new JLabel(careGiverControl.getOtherName(mail));
                nom.setBounds(searchButton.getX() + searchButton.getWidth() + 50, 15, 150, 20);
                add(nom);
                numTel = new JLabel(careGiverControl.getOtherTel(mail));
                numTel.setBounds(nom.getX(), nom.getY() + nom.getHeight() + 5, 25, nom.getHeight());
                add(numTel);
                estbenevole = new JLabel(careGiverControl.estBenevole(mail));
                estbenevole.setBounds(nom.getX() + nom.getWidth(), nom.getY(), nom.getWidth(), nom.getHeight());
                add(estbenevole);
                codePosal = new JLabel(careGiverControl.codePostal(mail));
                codePosal.setBounds(numTel.getX() + numTel.getWidth() + 15, numTel.getY(), 50, 20);
                add(codePosal);
                ModelTable model = new ModelTable(careGiverControl.careDoneBy(mail));
                JTable careTable = new JTable(model);
                tablePane = new JScrollPane(careTable);
                tablePane.setBounds(getX() + 15, searchButton.getY() + codePosal.getHeight() + 20, getWidth() - 30, 300);
                add(tablePane);
                repaint();
                revalidate();
            } catch (BDConnexionError connexionError) {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(), "accès BD", JOptionPane.ERROR_MESSAGE);
            }
            catch (InexistantCareGiver inexistantCareGiver)
            {
                JOptionPane.showMessageDialog(null,inexistantCareGiver.getMessage(), "utlisateur inexistant", JOptionPane.ERROR_MESSAGE);
            }
            catch (ErrorNull nullerr)
            {
                JOptionPane.showMessageDialog(null, nullerr.getMessage(), "invalid data", JOptionPane.ERROR_MESSAGE);
            }

            revalidate();
        }
    }
    /*"Nom / prénom",
                        "numéro de téléphone",
                        "bénévole",
                        "code postal",*/

    private class ModelTable extends AbstractTableModel
    {
        private ArrayList<ArrayList<String>> data;
        private String [] columnNames=
                {
                        "animal id",
                        "date / heure du soin rendu",
                        "soin médical: heure prévue",
                        "remarque par le soignant"
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
