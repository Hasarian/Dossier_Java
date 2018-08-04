package interfaceUtilisateur;

import controle.ControleSoignant;
import erreurs.Erreur;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheSoinParSoignantPanel extends JPanel {
    private MainFrame mainFrame;
    private JScrollPane idGiver;
    private JButton searchButton;
    private JButton backButton;
    private ControleSoignant careGiverControl;
    private JScrollPane tablePane;
    private JLabel idGiverLabel;
    private JList listId;
    public RechercheSoinParSoignantPanel(MainFrame mainFrame) throws Erreur
    {
        careGiverControl=new ControleSoignant();
        this.mainFrame=mainFrame;
        setBackground(Color.WHITE);
        setLayout(null);
        idGiverLabel= new JLabel("mail du soignant:");
        idGiverLabel.setBounds(50,getY(),125,20);
        listId =  new JList(careGiverControl.getTousLesUtilisateurs());
        idGiver=new JScrollPane(listId);
        listId.setVisibleRowCount(1);
        listId.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       listId.setSelectedIndex(0);
        idGiver.setBounds(idGiverLabel.getX()+idGiverLabel.getWidth(),idGiverLabel.getY(),200,idGiverLabel.getHeight()*3);
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

                if (tablePane != null) {
                    //remove(tablePane);
                    removeAll();
                    add(idGiverLabel);
                    add(idGiver);
                    add(searchButton);
                    add(backButton);
                }

                String mail = (String) listId.getSelectedValue();
                JLabel nom, numTel, estbenevole, codePosal;
                nom = new JLabel(careGiverControl.getNomDeFamilleAutreUtilisateur(mail));
                nom.setBounds(searchButton.getX() + searchButton.getWidth() + 150, 15, 150, 20);
                add(nom);
                String numeroTel=(careGiverControl.getTelAutreUtilisateur(mail)==null)?"numéro de téléphone non renseigné":careGiverControl.getTelAutreUtilisateur(mail).toString();
                numTel = new JLabel(numeroTel);
                numTel.setBounds(nom.getX()+50, nom.getY() + nom.getHeight() + 5, 300, nom.getHeight());
                add(numTel);
                estbenevole = new JLabel(careGiverControl.AutreUtilisateurEstBenevole(mail));
                estbenevole.setBounds(nom.getX() + nom.getWidth() + 150, nom.getY(), nom.getWidth(), nom.getHeight());
                add(estbenevole);
                codePosal = new JLabel(careGiverControl.getCodePostalAutreUtilisateur(mail));
                codePosal.setBounds(numTel.getX() + numTel.getWidth() + 70, numTel.getY(), 150, 20);
                add(codePosal);
                ModelTable model = new ModelTable(careGiverControl.soinsFaitsPar(mail));
                JTable careTable = new JTable(model);
                tablePane = new JScrollPane(careTable);
                tablePane.setBounds(getX() + 15, searchButton.getY() + codePosal.getHeight() + 20, getWidth() - 30, 300);
                add(tablePane);
                repaint();

            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
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
        private ArrayList<ArrayList<Object>> data;
        private String [] columnNames=
                {
                        "animal id",
                        "Mail du Vétérinaire",
                        "date / heure du soin rendu",
                        "soin médical: heure prévue",
                        "remarque pour le soin effectué",
                        "remarque pour l'animal"
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
                case 0: return Integer.class;
                default: return String.class;
            }
        }

    }
}
