package erreurs.erreurFormat;

public class NombreExpection extends ErreurFormat {

    public NombreExpection(String attribut){
        super("l'entrée "+attribut+" comporte autre chose que des chiffres"," entrer un chiffre");
    }


}
