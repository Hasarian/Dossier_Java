package Model;

import erreurs.ErrorNull;
//faire toString

public class Localite {
    private Integer idLocalite;
    private Integer codePostal;
    private String libelle;

    public Localite(Integer idLocalite, Integer codePostal, String libelle) throws ErrorNull {
        setCodePostal(codePostal);
        setIdLocalite(idLocalite);
        setLibelle(libelle);
    }
    public Localite() throws ErrorNull
    {
        setCodePostal(new Integer(7000));
        setIdLocalite(new Integer(1));
        setLibelle("Mons");
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public Integer getIdLocalite() {
        return idLocalite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) throws ErrorNull {
        if(libelle == null) throw new ErrorNull();
        this.libelle = libelle;
    }

    public void setCodePostal(Integer codePostal) throws ErrorNull {
        if(codePostal == null) throw new ErrorNull();
        this.codePostal = codePostal;
    }

    public void setIdLocalite(Integer idLocalite) throws ErrorNull {
        if(idLocalite == null) throw new ErrorNull();
        this.idLocalite = idLocalite;
    }
}
