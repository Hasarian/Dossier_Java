package erreurs.erreurFormat;

public class MauvaiseTailleString extends ErreurFormat
{
    public MauvaiseTailleString(String nom,int taille,int tailleMax)
    {
        super(nom+" faisait "+taille+" caractères"," le limiter à "+tailleMax+" caractères");
    }
}
