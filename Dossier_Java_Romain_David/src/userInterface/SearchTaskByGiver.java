package userInterface;

import Model.CareGiver;
import erreurs.BDConnexionError;
import uIController.CareGiverController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchTaskByGiver extends JPanel {
    private MainFrame mainFrame;
    private JTextField idGiver;
    private JButton searchButton;
    private CareGiverController careGiverControl;
    private JScrollPane tablePane;
    public SearchTaskByGiver(MainFrame mainFrame) throws BDConnexionError
    {
        careGiverControl=new CareGiverController();
        this.mainFrame=mainFrame;
        setBackground(Color.WHITE);
        setLayout(null);
        JLabel idGiverLabel= new JLabel("mail du soignant:");
        idGiverLabel.setBounds(50,getY(),35,20);
        idGiver=new JTextField();
        idGiver.setBounds(idGiverLabel.getX()+idGiverLabel.getWidth(),idGiverLabel.getY(),40,idGiverLabel.getHeight());
        add(idGiverLabel);
        add(idGiver);

        searchButton=new JButton("search");
        searchButton.setBounds(idGiver.getX(),idGiver.getY()+idGiver.getHeight()+10,idGiver.getWidth(),idGiver.getHeight());
        searchButton.addActionListener(new SearchListener());
        add(searchButton);

        JButton backButton=new JButton("back");
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
        public void actionPerformed(ActionEvent e)
        {
            if(tablePane!=null)remove(tablePane);
            String mail=idGiver.getText();
            JLabel nom,numTel,estbenevole,codePosal;
            nom=new JLabel(careGiverControl.getOtherName(mail));
            nom.setBounds(getX()+15,searchButton.getY()+searchButton.getHeight()+20,50,20);
            add(nom);
            numTel=new JLabel(careGiverControl.getOtherTel(mail));
            numTel.setBounds(nom.getX(),nom.getY()+nom.getHeight()+5,25,nom.getHeight());
            add(numTel);
            estbenevole=new JLabel(careGiverControl.estBenevole(mail));
            estbenevole.setBounds(nom.getX()+nom.getWidth(),nom.getY(),nom.getWidth(),nom.getHeight());
            add(estbenevole);
            codePosal=new JLabel(careGiverControl.codePostal(mail));
            codePosal.setBounds(numTel.getX()+numTel.getWidth()+15,numTel.getY(),50,20);
            add(codePosal);

            try {
                ModelTable model = new ModelTable(careGiverControl.careDoneBy(mail));
                JTable careTable = new JTable(model);
                tablePane = new JScrollPane(careTable);
                tablePane.setBounds(getX() + 15, searchButton.getY() + searchButton.getHeight() + 20, getWidth() - 30, 300);
                add(tablePane);
            } catch (BDConnexionError connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(), "accès BD", JOptionPane.ERROR_MESSAGE);
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
                        "remarque sur le soin",
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
