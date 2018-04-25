package userInterface;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TaskListPanel extends JPanel
{
    private Object[][] data;

    public TaskListPanel()
    {
        data=new Object[55][5];
        for(int i=0;i<data.length;i++)
        {
            Object[] rowData={"rex","a5","dog"};
            data[i]=rowData;
        }
        setLayout(null);
        setBackground(Color.WHITE);
        TaskTableModel model=new TaskTableModel();
        JTable taskTable=new JTable(model);
        JScrollPane tablePane=new JScrollPane(taskTable);
        taskTable.setFillsViewportHeight(true);
        tablePane.setBounds(5,5,975,300);
        add(tablePane);

        JLabel infoLabel=new JLabel("use click to select then click a button below to act");
        infoLabel.setBounds(tablePane.getX(),tablePane.getY()+tablePane.getHeight(),350,15);
        JLabel infoLabel2=new JLabel("use ctrl+click to select multiple tasks");
        infoLabel2.setBounds(infoLabel.getX(),infoLabel.getY()+infoLabel.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());
        JLabel infoLabel3=new JLabel("use shift+click to select all the tasks between two selections");
        infoLabel3.setBounds(infoLabel.getX(),infoLabel2.getY()+infoLabel2.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());
        JButton ouvrir=new JButton("consult");
        ouvrir.setBounds(20,infoLabel3.getY()+infoLabel3.getHeight()+20,200,60);
        JButton select=new JButton("select");
        select.setBounds(760,ouvrir.getY(),200,60);
        /*add(taskTable.getTableHeader(),BorderLayout.PAGE_START);
        add(taskTable,BorderLayout.CENTER);*/
        add(select);
        add(ouvrir);
        add(infoLabel);
        add(infoLabel2);
        add(infoLabel3);
    }
    private class TaskTableModel extends AbstractTableModel
    {
        private String [] columnNames=
                {
                        "name",
                        "cell number",
                        "species",
                };
        private TaskTableModel()
        {
            for(int rownb=0;rownb<getRowCount();rownb++)
                for(int columnNb=0;columnNb<getColumnCount();columnNb++)
                {
                    setValueAt(data[rownb][columnNb],rownb,columnNb);
                }
        }
        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex==columnNames.length-1;
        }
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

    }
}
