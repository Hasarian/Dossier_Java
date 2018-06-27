package dataAccess.dao;

import model.Soignant;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurInsertionSoignant;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;

public interface DAOSoignant {
    void create(Soignant soignant)throws ErreurInsertionSoignant;
    Soignant read(String id) throws SoignantInexistant, DonneePermanenteErreur, ErreurrNull;
    void update(String ancienMail,Soignant soignant) throws DonneePermanenteErreur;
    void delete(String id) throws DonneePermanenteErreur;
    ArrayList<String> readallMails() throws DonneePermanenteErreur;
    ArrayList<Soignant> readTousLesSoignants() throws DonneePermanenteErreur,ErreurrNull;
}