package erreurs.erreursExternes;

public class ErreurInsertionAnimal extends ErreurExterne {
    public ErreurInsertionAnimal(String cause){
        super("l'animal n'a pas pu être inscrit. Cause:\n "+cause);
    }
}
