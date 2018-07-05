package dataAccess.dao;

import erreurs.Erreur;
import model.SoinMedical;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheSoinAFaire
{
    ArrayList<SoinMedical> readCareToAnimal(int id,GregorianCalendar date)throws Erreur;

}
