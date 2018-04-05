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
        String out=super.toString()+" ";
        out+= String.format("%02d",hour/100);
        out+="h";
        out+=String.format("%02d",hour%100);
        return out;
    }
}
