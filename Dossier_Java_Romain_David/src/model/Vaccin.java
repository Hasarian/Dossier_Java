package model;

import erreurs.erreurFormat.ErreurrNull;

public class Vaccin {
    private String libelle;
    private Integer numVaccin;

    public Vaccin(String libelle, Integer numVaccin) throws ErreurrNull {
        setLibelle(libelle);
        setNumVaccin(numVaccin);
    }

    public void setLibelle(String libelle) throws ErreurrNull {
        if(libelle == null) throw new ErreurrNull("libellé du vaccin");
        this.libelle = libelle;
    }

    public void setNumVaccin(Integer numVaccin) throws ErreurrNull {
        if(numVaccin == null) throw new ErreurrNull("numéro du vaccin");
        this.numVaccin = numVaccin;
    }

    public String getLibelle() {
        return libelle;
    }

    public Integer getNumVaccin() {
        return numVaccin;
    }
}
