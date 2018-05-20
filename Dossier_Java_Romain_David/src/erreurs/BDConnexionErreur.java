package erreurs;

public class BDConnexionErreur extends Exception
{
    private String msg;
    public BDConnexionErreur(String msg)
    {
        this.msg="unable to connect to the DB: "+msg;
    }
    @Override
    public String getMessage() {
        return this.msg;
    }
}
