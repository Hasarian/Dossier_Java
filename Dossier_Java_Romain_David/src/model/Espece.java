package model;

import erreurs.ErreurrNull;

public class Espece {
    private String libelle;
    private Boolean estEnVoieDeDisparition;
    private String typeDeDeplacement;
    private String milieuDeVie;

    public Espece(String libelle, Boolean estEnVoieDeDisparition, String typeDeDeplacement, String milieuDeVie)throws ErreurrNull {
        setEstEnVoieDeDisparition(estEnVoieDeDisparition);
        setLibelle(libelle);
        setMilieuDeVie(milieuDeVie);
        setTypeDeDeplacement(typeDeDeplacement);
    }
    public Espece() throws ErreurrNull
    {
        setEstEnVoieDeDisparition(true);
        setLibelle("chien");
        setMilieuDeVie("maison");
        setTypeDeDeplacement("à pattes");
    }

    public void setEstEnVoieDeDisparition(Boolean estEnVoieDeDisparitionArg) throws ErreurrNull {
        if(estEnVoieDeDisparitionArg != null)
            this.estEnVoieDeDisparition = estEnVoieDeDisparitionArg;
        else throw new ErreurrNull();
    }

    public void setLibelle(String libelleArg) throws ErreurrNull {
        if(libelleArg != null)
            this.libelle = libelleArg;
        else throw new ErreurrNull("libelle de modele Espece");
    }

    public void setMilieuDeVie(String milieuDeVieArg) throws ErreurrNull {
        if(milieuDeVieArg==null) throw new ErreurrNull("milieu de vie dans l'objet modele \"espece\"");
        this.milieuDeVie = milieuDeVieArg;
    }

    public void setTypeDeDeplacement(String typeDeDeplacementArg) throws ErreurrNull {
        if(typeDeDeplacementArg != null)
            this.typeDeDeplacement = typeDeDeplacementArg;
        else throw new ErreurrNull("typeDeDeplacement du modele espece");
    }

    @Override
    public String toString() {
        return (estEnVoieDeDisparition)?libelle+" [en voie de disparition]":libelle;
    }
    public String getLibelle(){return libelle;}
    public String getEspeceLibelle(){return getEspeceLibelle();}
}


