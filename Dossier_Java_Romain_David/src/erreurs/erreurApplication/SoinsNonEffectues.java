package erreurs.erreurApplication;

public class SoinsNonEffectues extends ErreurAppli
{
    private int nb;
   public SoinsNonEffectues(int nbSoinsNonEffecues)
   {
      super(" terminer les soins alors que "+nbSoinsNonEffecues+" n'ont pas été effectués");
    }
}
