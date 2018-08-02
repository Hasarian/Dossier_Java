package erreurs.erreursExternes;

public class DonneePermanenteErreur extends ErreurExterne {
    public DonneePermanenteErreur(String description) {
        super("Erreur avec la base de données:\n"+description);
    }
}
