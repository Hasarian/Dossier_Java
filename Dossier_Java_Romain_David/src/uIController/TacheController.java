package uIController;

import Business.ListeAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

public class TacheController
{
    Animal animal;
    public TacheController(Integer id) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        ListeAnimalBusiness business= ListeAnimalBusiness.obtenirListAnimalBusiness(new SoignantController());
        animal=business.getAnimal(id);
    }
}
