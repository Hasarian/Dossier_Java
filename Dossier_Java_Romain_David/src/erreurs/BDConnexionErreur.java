package erreurs;

public class BDConnexionErreur extends Exception
{
    private String msg;
    public BDConnexionErreur(String msg)
    {
        this.msg="connexion à la base de données impossible. Cause: "+msg;
    }
    @Override
    public String getMessage() {
        return this.msg;
    }
}
