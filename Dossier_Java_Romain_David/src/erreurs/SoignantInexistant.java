package erreurs;

public class SoignantInexistant extends Exception
{
    private String msg;

    public SoignantInexistant()
    {
        msg="login incorrect";
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
