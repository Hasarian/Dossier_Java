package userInterface;

import falseData.CareData;
import falseData.SimpleCare;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimalCarePanel extends JPanel
{
    CareData careData;
    JPanel parentPanel;
    JButton selectionButton,addInfoButton;
    ArrayList<JLabel> tasks;

    public AnimalCarePanel(JPanel parentPanel,int height)
    {
        super();
        this.parentPanel=parentPanel;
        careData=new CareData();
        setLayout(null);
        setBounds(parentPanel.getX(),parentPanel.getY()+height,parentPanel.getWidth()*3/10,parentPanel.getHeight()/10);
        Border animalIntro=BorderFactory.createTitledBorder(careData.toString());
        setBorder(animalIntro);
        selectionButton=new JButton("select animal");
        addInfoButton=new JButton("see tasks");
        selectionButton.setBounds(getX(),getY()+15,getWidth()*4/10,20);
        addInfoButton.setBounds(getX()+getWidth()-selectionButton.getWidth()-15,selectionButton.getY(),selectionButton.getWidth()-5,selectionButton.getHeight());
        add(selectionButton);
        add(addInfoButton);
        addInfoButton.addActionListener(new AddinfoPush());
        int y=addInfoButton.getY()+5+addInfoButton.getHeight();
        tasks=new ArrayList<JLabel>();
        for(SimpleCare care:careData.getCares())
        {
            JLabel task=new JLabel(care.toString());
            task.setBounds(getX()+25,y,getWidth(),25);
            y+=task.getHeight();
            tasks.add(task);
        }
    }

    private class AddinfoPush implements ActionListener
    {
        boolean activated;
        private AddinfoPush()
        {
            activated=false;
        }

        public void actionPerformed(ActionEvent e)
        {
            if(activated)
            {
                setBounds(getX(),getY(),getWidth(),parentPanel.getHeight()/10);
                activated=false;
                for(JLabel task:tasks)
                {
                    remove(task);
                }
            }
            else
            {
                setBounds(getX(),getY(),getWidth(),getHeight()*2);
                activated=true;
                for (JLabel task:tasks)
                {
                    add(task);
                }
            }
        }
    }
}
