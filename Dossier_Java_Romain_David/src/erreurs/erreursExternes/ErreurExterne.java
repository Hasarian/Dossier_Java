package erreurs.erreursExternes;

import erreurs.Erreur;

public abstract class ErreurExterne extends Erreur {
    public ErreurExterne(String description)
    {
        super("Erreur de donnée permanente",description);
    }
}
