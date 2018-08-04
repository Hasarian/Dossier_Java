package erreurs.erreurFormat;

public class NombreInvalideException extends ErreurFormat
{
    public NombreInvalideException(int mauvaisNombre,String explication)
    {
        super("vous avez entré "+mauvaisNombre,explication);
    }
}
