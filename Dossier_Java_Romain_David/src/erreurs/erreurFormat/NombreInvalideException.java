package erreurs.erreurFormat;

public class NombreInvalideException extends ErreurFormat
{
    private String msg;
    public NombreInvalideException(int mauvaisNombre,String explication)
    {
        super("vous avez entré "+mauvaisNombre,explication);
    }
}
