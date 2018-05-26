package DataAccess.DAO;

import Model.Animal;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

public interface DAOAnimal {
    Animal read(int id) throws ErreurrNull, BDConnexionErreur, SoignantInexistant;
    void readTousLesAnimaux() throws ErreurrNull, BDConnexionErreur, SoignantInexistant;
    void updateEtat(Animal animal) throws BDConnexionErreur;
}
