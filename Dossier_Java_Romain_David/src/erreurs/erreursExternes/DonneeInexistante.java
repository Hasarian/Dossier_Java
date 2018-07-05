package erreurs.erreursExternes;

public class DonneeInexistante extends ErreurExterne
{
    public DonneeInexistante(String nom)
    {
        super(nom+" n'existe pas");
    }
}
