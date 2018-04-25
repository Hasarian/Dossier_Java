package userInterface;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TaskListPanel extends JPanel
{
    public static final int COLUMNNUMBER=3;
    private Object[][] data;
    public Object[][]getTableData(){return data;}
    private DashBoardPane parentPanel;

    public DashBoardPane getParentPanel() {
        return parentPanel;
    }

    private JScrollPane tablePane;
    private JLabel infoLabel,infoLabel2,infoLabel3;
    private JTable taskTable;

    public JTable getTaskTable() {
        return taskTable;
    }

    public JLabel getInfoLabel3() {
        return infoLabel3;
    }

    public TaskListPanel(Object[][]data, DashBoardPane parentPanel)
    {
        this.parentPanel=parentPanel;
        this.data=data;
        setLayout(null);
        setBackground(Color.WHITE);
        TaskTableModel model=new TaskTableModel();
        taskTable=new JTable(model);
        tablePane=new JScrollPane(taskTable);
        taskTable.setFillsViewportHeight(true);
        tablePane.setBounds(5,5,975,300);
        add(tablePane);

        infoLabel=new JLabel("use click to select then click a button below to act");
        infoLabel.setBounds(tablePane.getX(),tablePane.getY()+tablePane.getHeight(),350,15);
        infoLabel2=new JLabel("use ctrl+click to select multiple tasks");
        infoLabel2.setBounds(infoLabel.getX(),infoLabel.getY()+infoLabel.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());
        infoLabel3=new JLabel("use shift+click to select all the tasks between two selections");
        infoLabel3.setBounds(infoLabel.getX(),infoLabel2.getY()+infoLabel2.getHeight(),infoLabel.getWidth(),infoLabel.getHeight());

        add(infoLabel);
        add(infoLabel2);
        add(infoLabel3);
    }
    public TaskListPanel(DashBoardPane parentPanel)
    {
        this(new String[25][COLUMNNUMBER],parentPanel);
        for(int i=0;i<data.length;i++)
        {
            String[] rowData={"rex","a5","dog"};
            data[i]=rowData;
        }
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
                for(int columnNb=0;columnNb<getColumnCount()&&data[rownb][columnNb]!=null;columnNb++)
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
