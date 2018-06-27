package dataAccess.dao;

import model.SoinMedical;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheSoinAFaire
{
    ArrayList<SoinMedical> readCareToAnimal(int id,GregorianCalendar date)throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant;

}
