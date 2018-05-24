package erreurs;

public class NombreInvalideException extends Exception
{
    private String msg;
    public NombreInvalideException(int mauvaisNombre,String explication)
    {
        msg=mauvaisNombre+" a été entré.\ninvalide parce que: ["+explication+"]";
    }
}
