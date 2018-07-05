package erreurs.erreurFormat;

import erreurs.Erreur;

public abstract class ErreurFormat extends Erreur
{
    public ErreurFormat(String description,String condition)
    {
        super("erreur d'entrée",description+" \nalors que vous deviez "+condition);
    }
}
