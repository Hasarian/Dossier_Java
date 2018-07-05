package erreurs.erreurFormat;

public class EmailRegexErreur extends ErreurFormat
{
   public EmailRegexErreur(String mauvaisMail)
   {
       super("votre mail est "+mauvaisMail,"écrire un mail respectant la convention");
   }
}
