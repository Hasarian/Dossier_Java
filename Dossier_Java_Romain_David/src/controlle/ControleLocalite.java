package controlle;

import business.LocaliteBusiness;
import erreurs.Erreur;
import modèle.Localite;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreursExternes.DonneeInexistante;

import java.util.ArrayList;

public class ControleLocalite {
    private LocaliteBusiness localiteBusiness;
    private ArrayList<Localite> localites;
    public ControleLocalite() throws DonneePermanenteErreur {
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
