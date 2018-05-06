package uIController;

import Business.LocaliteBusiness;
import Model.Localite;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class LocaliteController {
    private LocaliteBusiness localiteBusiness;

    public LocaliteController() throws BDConnexionError {
        localiteBusiness =  new LocaliteBusiness();
    }

    public ArrayList<Localite> getAllLocalite() throws BDConnexionError, ErrorNull {
        return localiteBusiness.getAllLocalite();
    }
}
