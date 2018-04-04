package falseData;

import java.util.ArrayList;

public class CareFolder
{
   private int nbDir;
   private String note;
   private CareState state;
   private AnimalFolder animal;
   private ArrayList <ToDo> careList;

    public ArrayList<ToDo> getCareList() {
        return careList;
    }

    public AnimalFolder getAnimal(){return animal;}


}
