package erreurs.erreurApplication;

import erreurs.Erreur;

public abstract class ErreurAppli extends Erreur {
    public ErreurAppli(String cause)
    {
        super("exception d'application","vous avez tenté de "+cause);
    }
}
