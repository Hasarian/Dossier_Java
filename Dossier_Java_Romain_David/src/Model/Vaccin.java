package Model;

import erreurs.ErrorNull;

public class Vaccin {
    private String libelle;
    private Integer numVaccin;

    public Vaccin(String libelle, Integer numVaccin) throws ErrorNull{
        setLibelle(libelle);
        setNumVaccin(numVaccin);
    }

    public void setLibelle(String libelle) throws ErrorNull {
        if(libelle == null) throw new ErrorNull();
        this.libelle = libelle;
    }

    public void setNumVaccin(Integer numVaccin) throws ErrorNull {
        if(numVaccin == null) throw new ErrorNull();
        this.numVaccin = numVaccin;
    }

    public String getLibelle() {
        return libelle;
    }

    public Integer getNumVaccin() {
        return numVaccin;
    }
}
