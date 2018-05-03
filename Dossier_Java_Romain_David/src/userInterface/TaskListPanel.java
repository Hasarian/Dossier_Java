package userInterface;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class TaskListPanel extends JPanel
{
    private ArrayList<ArrayList<String>> data;
    public ArrayList<ArrayList<String>> getTableData(){return data;}
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

    public TaskListPanel(DashBoardPane parentPanel,ArrayList<ArrayList<String>>data)
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
        this(parentPanel,new ArrayList<ArrayList<String>>());
        ArrayList<String> rowData=new ArrayList<String>();
        rowData.add("rex");
        rowData.add("0001");
        rowData.add("a5");
        rowData.add("dog");
        data.add(rowData);

        rowData=new ArrayList<String>();
        rowData.add("alfonse");
        rowData.add("0002");
        rowData.add("c8");
        rowData.add("tortue");
        data.add(rowData);

        rowData=new ArrayList<String>();
        rowData.add("misty");
        rowData.add("0003");
        rowData.add("b3");
        rowData.add("cat");
        data.add(rowData);

        rowData=new ArrayList<String>();
        rowData.add("ash");
        rowData.add("0004");
        rowData.add("a5");
        rowData.add("dog");
        data.add(rowData);

        rowData=new ArrayList<String>();
        rowData.add("ziggy");
        rowData.add("0005");
        rowData.add("a8");
        rowData.add("dog");
        data.add(rowData);

        rowData=new ArrayList<String>();
        rowData.add("farcry");
        rowData.add("0006");
        rowData.add("b2");
        rowData.add("cat");
        data.add(rowData);

    }
    public void removeData(ArrayList<ArrayList<String>> dataToRemove)
    {
        for(ArrayList<String>data:dataToRemove)removeSingleData(data);
    }
    public void removeSingleData(ArrayList<String> dataToRemove)
    {
      data.remove(dataToRemove);
    }
    public void addRow(ArrayList<String> newData)
    {
        data.add(newData);
    }

    private class TaskTableModel extends AbstractTableModel
    {
        private String [] columnNames=
                {
                        "name",
                        "id",
                        "cell number",
                        "species",
                };
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
        public void setValueAt(String value, int row, int col) {
            data.get(row).set(col,value);
            fireTableCellUpdated(row, col);
        }
        /*public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }*/

    }
}
