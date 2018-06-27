package erreurs;

public class SoinsNonEffectues extends Exception
{
    private int nb;
   public SoinsNonEffectues(int nbSoinsNonEffecues)
   {
       nb=nbSoinsNonEffecues;
    }

    @Override
    public String getMessage() {
        return nb+" soins ne sont pas terminés.\n Rappel: un soin est terminé si vous avez coché la case correspondante ou si vous avez rempli la case de commentaire";
    }
}
