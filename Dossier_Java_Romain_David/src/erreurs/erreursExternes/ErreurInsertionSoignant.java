package erreurs.erreursExternes;

public class ErreurInsertionSoignant extends ErreurExterne {
    public ErreurInsertionSoignant(String errorMessage){
        super("le soignant n'a pas pu être inscrit. Cause:\n "+errorMessage);
    }

}
