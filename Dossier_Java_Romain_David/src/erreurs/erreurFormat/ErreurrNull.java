package erreurs.erreurFormat;

public class ErreurrNull extends ErreurFormat {

    public ErreurrNull(String attribut){
       super("l'attribut "+attribut+" n'est pas rempli"," le remplir");
    }

}
