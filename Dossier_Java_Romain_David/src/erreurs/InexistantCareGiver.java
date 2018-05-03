package erreurs;

public class InexistantCareGiver extends Exception
{
    private String msg;

    public InexistantCareGiver()
    {
        msg="login incorrect";
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
