package falseData;

public class AnimalData
{
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public AnimalData()
    {
        name="rex";
        id=10;
    }

    @Override
    public String toString() {
        return "name: "+name+" id: "+id;
    }
}
