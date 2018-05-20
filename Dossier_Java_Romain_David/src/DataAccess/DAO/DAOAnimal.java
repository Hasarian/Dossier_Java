package DataAccess.DAO;

import Model.Animal;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

public interface DAOAnimal {
    void create(Animal animal);
    Animal read(int id) throws ErreurrNull, BDConnexionErreur, SoignantInexistant;
    void readTousLesAnimaux() throws ErreurrNull, BDConnexionErreur, SoignantInexistant;
    void update(Animal animal);
    void delete(int id);
}
