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
    JFrame parentPanel;
    JButton selectionButton,addInfoButton;
    ArrayList<JLabel> tasks;

    public AnimalCarePanel(JFrame parentPanel)
    {
        super();
        this.parentPanel=parentPanel;
        careData=new CareData();
        setLayout(null);
        setBounds(5,5,parentPanel.getWidth()*3/10,parentPanel.getHeight()/10);
        Border animalIntro=BorderFactory.createTitledBorder(careData.toString());
        setBorder(animalIntro);
        selectionButton=new JButton("select animal");
        addInfoButton=new JButton("see tasks");
        selectionButton.setBounds(getX()+5,getY()+15,getWidth()*3/10,20);
        addInfoButton.setBounds(getX()+getWidth()-selectionButton.getWidth(),selectionButton.getY(),selectionButton.getWidth(),selectionButton.getHeight());
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
                System.out.println(activated+"\t"+getHeight());
                activated=false;
                for(JLabel task:tasks)
                {
                    remove(task);
                }
            }
            else
            {
                setBounds(getX(),getY(),getWidth(),getHeight()*2);
                System.out.println(activated+"\t"+getHeight());
                activated=true;
                for (JLabel task:tasks)
                {
                    add(task);
                }
            }
        }
    }
}
