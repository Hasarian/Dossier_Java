package falseData;

public class MediCare extends SimpleCare
{
    private int hour;
    public MediCare()
    {
        super("medoc");
        hour=1400;
    }

    @Override
    public String toString() {
        return super.toString()+" at "+hour/100+"h"+hour%100;
    }
}
