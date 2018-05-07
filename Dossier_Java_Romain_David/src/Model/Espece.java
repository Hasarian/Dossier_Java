package Model;

import erreurs.ErrorNull;

public class Espece {
    private String libelle;
    private Boolean estEnVoieDeDisparition;
    private String typeDeDeplacement;
    private String milieuDeVie;

    public Espece(String libelle, Boolean estEnVoieDeDisparition, String typeDeDeplacement, String milieuDeVie)throws ErrorNull {
        setEstEnVoieDeDisparition(estEnVoieDeDisparition);
        setLibelle(libelle);
        setMilieuDeVie(milieuDeVie);
        setTypeDeDeplacement(typeDeDeplacement);
    }

    public void setEstEnVoieDeDisparition(Boolean estEnVoieDeDisparition) throws ErrorNull {
        if(estEnVoieDeDisparition != null)
            this.estEnVoieDeDisparition = estEnVoieDeDisparition;
        else throw new ErrorNull();
    }

    public void setLibelle(String libelle) throws ErrorNull{
        if(estEnVoieDeDisparition != null)
            this.libelle = libelle;
        else throw new ErrorNull("libelle de modele Espece");
    }

    public void setMilieuDeVie(String milieuDeVie) throws ErrorNull {
        if(estEnVoieDeDisparition != null)
            this.milieuDeVie = milieuDeVie;
        else throw new ErrorNull("milieuDeVie du modele Espece");
    }

    public void setTypeDeDeplacement(String typeDeDeplacement) throws ErrorNull {
        if(estEnVoieDeDisparition != null)
            this.typeDeDeplacement = typeDeDeplacement;
        else throw new ErrorNull("typeDeDeplacement du modele espece");
    }

    @Override
    public String toString() {
        return libelle;
    }
}


