package erreurs;

public class SuppressionCurrentUser extends Exception
{
    public String getMessage() {
        return "vous tentez de supprimer votre propre compte !";
    }
}
