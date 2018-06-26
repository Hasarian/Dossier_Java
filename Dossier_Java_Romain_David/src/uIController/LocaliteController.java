package uIController;

import Business.LocaliteBusiness;
import Model.Localite;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.LocaliteInexistante;

import java.util.ArrayList;

public class LocaliteController {
    private LocaliteBusiness localiteBusiness;
    private ArrayList<Localite> localites;
    public LocaliteController() throws DonneePermanenteErreur {
        localiteBusiness =  new LocaliteBusiness();
    }

    public ArrayList<Localite> getToutesLesLocalites() throws DonneePermanenteErreur, ErreurrNull {
        localites=localiteBusiness.getToutesLesLocalites();
        return localites;
    }
    public int getIndexLocalite(Localite localite) throws LocaliteInexistante
    {
        int i=0;
        for(Localite localiteB:localites)
        {
            if(localite.getIdLocalite()==localiteB.getIdLocalite()) return i;
            i++;
        }
        throw new LocaliteInexistante(localite.getLibelle());
    }

}
