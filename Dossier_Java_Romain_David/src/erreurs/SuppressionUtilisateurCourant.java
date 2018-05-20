package erreurs;

public class SuppressionUtilisateurCourant extends Exception
{
    public String getMessage() {
        return "vous tentez de supprimer votre propre compte !";
    }
}
