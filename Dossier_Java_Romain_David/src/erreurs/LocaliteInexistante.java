package erreurs;

public class LocaliteInexistante extends Exception
{
    private String nom;
    public LocaliteInexistante(String nom)
    {
        this.nom=nom;
    }
    @Override
    public String getMessage() {
        return nom+" non connu";
    }
}
