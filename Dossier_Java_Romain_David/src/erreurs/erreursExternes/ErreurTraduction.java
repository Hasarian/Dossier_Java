package erreurs.erreursExternes;

public class ErreurTraduction extends ErreurExterne
{
    public ErreurTraduction(String description) {
        super("Il y a eu une erreur dans la traduction des données obtenues :\n"+description);
    }
}
