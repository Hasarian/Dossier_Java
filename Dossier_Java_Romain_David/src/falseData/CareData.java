package falseData;

import java.util.ArrayList;

public class CareData
{
    private AnimalData animalData;
    private ArrayList<SimpleCare> cares;

    public AnimalData getAnimalData() {
        return animalData;
    }

    public ArrayList<SimpleCare> getCares() {
        return cares;
    }

    public CareData()
    {
        animalData=new AnimalData();
        cares=new ArrayList<SimpleCare>();
        cares.add(new Food());
        cares.add(new MediCare());
    }

    @Override
    public String toString() {
        return animalData.toString()+"  "+cares.size()+" tasks";
    }
}
