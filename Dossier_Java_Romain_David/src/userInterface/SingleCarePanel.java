package userInterface;

import falseData.ToDo;

import javax.swing.*;

public class SingleCarePanel extends JPanel
{
    private ToDo toDo;
    private JCheckBox done;
    private JTextField noteField;
    public boolean done(){return done.isSelected();}
    public String getNote(){return noteField.getText();}
    public SingleCarePanel(ToDo toDo,int x, int y, int width, int height)
    {
        this.toDo=toDo;
        JLabel label=new JLabel(toDo.toString());
        done=new JCheckBox();
        setBounds(x,y,width,height);
        label.setBounds(x,y,width/2,height);
        done.setBounds(x+width/2,y,width/2,height);
        add(label);
        add(done);
    }
    public void addNoteField()
    {
        noteField=new JTextField();
        noteField.setBounds(getX(),getY()+getHeight(),getWidth(),getHeight());
        setSize(getWidth(),getHeight()*2);
        add(noteField);
    }
}
