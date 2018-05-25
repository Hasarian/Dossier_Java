package erreurs;

public class SoignantInexistant extends Exception
{
    private String msg;

    public SoignantInexistant()
    {
        msg="email incorrect";
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
