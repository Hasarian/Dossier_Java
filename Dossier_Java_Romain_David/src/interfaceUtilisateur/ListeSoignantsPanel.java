package interfaceUtilisateur;

import controle.ControleSoignant;
import erreurs.Erreur;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeSoignantsPanel extends JPanel
{
    private ControleSoignant controller;
    private MainFrame mainFrame;
    private JTable table;
    private ListeSoignantsPanel cePanneau;
    public ListeSoignantsPanel(MainFrame mainFrame) throws Erreur
    {
        cePanneau=this;
        setBackground(Color.white);
        setLayout(null);
        controller=new ControleSoignant();
        this.mainFrame=mainFrame;
        ListingSoignantTableModel model=new ListingSoignantTableModel();
        table=new JTable(model);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(5,0,990,200);
        add(scrollPane);

        JButton modifier=new JButton("modifier");
        modifier.addActionListener(new ModifierListener());
        modifier.setBounds(889,230,100,30);
        JButton retour= new JButton("retour");
        retour.addActionListener(new RetourListener());
        retour.setBounds(5,230,100,30);
        add(modifier);
        add(retour);
    }
    private class ModifierListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                mainFrame.changePanel(new InfoUtilisateurPanel(controller.getSoignantParIndex(table.getSelectedRow()),mainFrame));
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
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
        public int getRowCount() {
                try {return controller.nbSoignants();
                }
                catch (Erreur err)
                {
                    JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            try{
                return controller.obtenirInfo(rowIndex, columnIndex); }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
                return "valeur inconnue";
            }
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
                case 2: return Integer.class;
                case 4: return Boolean.class;
                default: return String.class;
            }
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
    }
}
