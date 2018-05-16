package erreurs;

public class EmailRegexErreur extends Exception
{
    @Override
    public String getMessage() {
        return "votre mail ne respecte pas la convention.\n Il doit avoir la forme suivante: minuscules.minuscules[.chiffre]@spa.be";
    }
}
