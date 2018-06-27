package DataAccess.DAO;

import Model.Animal;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;

public interface DAOAnimal {
    Animal read(int id) throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant;
    ArrayList<Animal> readTousLesAnimaux() throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant;
    void updateEtat(Animal animal) throws DonneePermanenteErreur;
}
