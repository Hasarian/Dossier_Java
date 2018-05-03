package erreurs;

public class BDConnexionError extends Exception
{
    private String msg;

    public BDConnexionError()
    {
        msg="unable to connect to the DB";
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
