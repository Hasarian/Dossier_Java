package uIController;

import Business.LocaliteBusiness;
import Model.Localite;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public class LocaliteController {
    private LocaliteBusiness localiteBusiness;

    public LocaliteController() throws BDConnexionErreur {
        localiteBusiness =  new LocaliteBusiness();
    }

    public ArrayList<Localite> getToutesLesLocalites() throws BDConnexionErreur, ErreurrNull {
        return localiteBusiness.getToutesLesLocalites();
    }
}
