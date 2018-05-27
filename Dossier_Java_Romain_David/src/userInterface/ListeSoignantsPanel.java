package userInterface;

import Business.ListeAnimalBusiness;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class ListeSoignantsPanel extends JPanel
{
    private SoignantController controller;
    private MainFrame mainFrame;
    private JTable table;
    private ListeSoignantsPanel cePanneau;
    public ListeSoignantsPanel(MainFrame mainFrame) throws BDConnexionErreur,ErreurrNull,SoignantInexistant
    {
        cePanneau=this;
        setBackground(Color.white);
        setLayout(null);
        controller=new SoignantController();
        this.mainFrame=mainFrame;
        ListingSoignantTableModel model=new ListingSoignantTableModel();
        table=new JTable(model);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(0,0,950,500);
        add(scrollPane);

        JButton modifier=new JButton("modifier");
        JButton retour= new JButton("retour");
        retour.addActionListener(new RetourListener());
        retour.setBounds(5,530,50,30);
        add(retour);
    }
    private class ModifierListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                mainFrame.changePanel(new InfoUtilisateurPanel(controller.getSoignantParIndex(table.getSelectedRow()),mainFrame));
            }
            catch (BDConnexionErreur bdErreur)
            {
                JOptionPane.showMessageDialog(null,bdErreur.getMessage(),"erreur de connexion à la BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull erreurrNull)
            {
                JOptionPane.showMessageDialog(null,erreurrNull.getMessage(),"attribut invalide",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class RetourListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.changePanel();
        }
    }
    private class ListingSoignantTableModel extends AbstractTableModel
    {
        private String [] columnNames=
                {
                    "nom",
                    "mail",
                    "téléphone",
                    "date d'embauche",
                    "bénévole",
                    "remarque"

                };
        public ListingSoignantTableModel()
                throws BDConnexionErreur, ErreurrNull, SoignantInexistant
        { controller.initSoignant();}
        public int getRowCount() {
                return controller.nbSoignants();
            }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
                return controller.obtenirInfo(rowIndex, columnIndex);
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex)
            {
                case 2: return int.class;
                case 3: return GregorianCalendar.class;
                case 4: return Boolean.class;
                default: return String.class;
            }
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
    }
}
