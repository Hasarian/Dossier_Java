package erreurs;

public class MauvaiseTailleString extends Exception
{
    private String nomRemarque;
    private int tailleMax;
    private int taille;

    public MauvaiseTailleString(String nom,int taille,int tailleMax)
    {
        this.nomRemarque=nom;
        this.taille=taille;
        this.tailleMax=tailleMax;
    }

    @Override
    public String getMessage() {
        return nomRemarque+" fait "+taille+" caractères\nIl ne peut faire que "+tailleMax+" caractères";
    }
}
