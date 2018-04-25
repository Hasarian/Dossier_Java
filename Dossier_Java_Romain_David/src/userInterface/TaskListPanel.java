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
            Object[] rowData={"rex","a5","","dog",new Boolean(false)};
            data[i]=rowData;
        }
        setLayout(null);
        setBackground(Color.WHITE);
        JTable taskTable=new JTable(new TaskTableModel());
        taskTable.setTableHeader(new JTableHeader());
        JScrollPane tablePane=new JScrollPane(taskTable);
        taskTable.setFillsViewportHeight(true);
        tablePane.setBounds(5,5,975,300);
        add(tablePane);

        JButton ouvrir=new JButton("consult");
        ouvrir.setBounds(20,tablePane.getY()+tablePane.getHeight()+20,200,60);
        JButton select=new JButton("select");
        /*add(taskTable.getTableHeader(),BorderLayout.PAGE_START);
        add(taskTable,BorderLayout.CENTER);*/

        add(ouvrir);
    }
    private class TaskTableModel extends AbstractTableModel
    {
        private String [] columnNames=
                {
                        "name",
                        "cell number",
                        "",
                        "species",
                        "selection"
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
    }
}
