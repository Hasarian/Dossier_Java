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
    public Espece() throws ErrorNull
    {
        setEstEnVoieDeDisparition(true);
        setLibelle("chien");
        setMilieuDeVie("maison");
        setTypeDeDeplacement("à pattes");
    }

    public void setEstEnVoieDeDisparition(Boolean estEnVoieDeDisparitionArg) throws ErrorNull {
        if(estEnVoieDeDisparitionArg != null)
            this.estEnVoieDeDisparition = estEnVoieDeDisparitionArg;
        else throw new ErrorNull();
    }

    public void setLibelle(String libelleArg) throws ErrorNull{
        if(libelleArg != null)
            this.libelle = libelleArg;
        else throw new ErrorNull("libelle de modele Espece");
    }

    public void setMilieuDeVie(String milieuDeVieArg) throws ErrorNull {
            this.milieuDeVie = milieuDeVieArg;
    }

    public void setTypeDeDeplacement(String typeDeDeplacementArg) throws ErrorNull {
        if(typeDeDeplacementArg != null)
            this.typeDeDeplacement = typeDeDeplacementArg;
        else throw new ErrorNull("typeDeDeplacement du modele espece");
    }

    @Override
    public String toString() {
        return (estEnVoieDeDisparition)?libelle+" [en voie de disparition]":libelle;
    }
    public String getLibelle(){return libelle;}
}


