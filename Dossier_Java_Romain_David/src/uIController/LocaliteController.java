package uIController;

import business.LocaliteBusiness;
import erreurs.Erreur;
import model.Localite;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreursExternes.DonneeInexistante;

import java.util.ArrayList;

public class LocaliteController {
    private LocaliteBusiness localiteBusiness;
    private ArrayList<Localite> localites;
    public LocaliteController() throws DonneePermanenteErreur {
        localiteBusiness =  new LocaliteBusiness();
    }

    public ArrayList<Localite> getToutesLesLocalites() throws Erreur {
        localites=localiteBusiness.getToutesLesLocalites();
        return localites;
    }
    public int getIndexLocalite(Localite localite) throws DonneeInexistante
    {
        int i=0;
        for(Localite localiteB:localites)
        {
            if(localite.getIdLocalite()==localiteB.getIdLocalite()) return i;
            i++;
        }
        throw new DonneeInexistante(localite.getLibelle());
    }

}
