package userInterface;

import falseData.CareFolder;
import falseData.CareState;
import falseData.ToDo;

import javax.swing.*;
import java.util.ArrayList;

public class CarePanel extends JPanel
{
    private CareFolder fodler;
    private JButton folderPanel,infoPanel;
    private ArrayList<SingleCarePanel> cares;
    public CarePanel(CareFolder folder,int x, int y, int width, int height)
    {
        super();
        this.fodler=new CareFolder();
        JLabel animalName=new JLabel(fodler.getAnimal().getName());
        JLabel animalId=new JLabel(new Integer(folder.getAnimal().getId()).toString());
        setBounds(x,y,width,height);
        int heightCare=animalName.getHeight();
        for (ToDo care:folder.getCareList())
        {
            cares.add(new SingleCarePanel(care,getX()+10,height,getWidth()-50,getHeight()/5));
        }
        add(animalName,animalId);
        for (SingleCarePanel carePanel:cares) add(carePanel);

    }
}
