package erreurs;

public abstract class Erreur extends Exception {
    private String titre;
    private String msg;

    public Erreur(String titre, String msg)
    {
        this.titre=titre;
        this.msg=msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
    public String getTitre()
    {
        return titre;
    }
}
