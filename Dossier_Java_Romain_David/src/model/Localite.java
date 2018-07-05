package model;

import erreurs.erreurFormat.ErreurrNull;
//faire toString

public class Localite {
    private Integer idLocalite;
    private Integer codePostal;
    private String libelle;

    public Localite(Integer idLocalite, Integer codePostal, String libelle) throws ErreurrNull {
        setCodePostal(codePostal);
        setIdLocalite(idLocalite);
        setLibelle(libelle);
    }
    public Localite() throws ErreurrNull
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

    public void setLibelle(String libelle) throws ErreurrNull {
        if(libelle == null) throw new ErreurrNull("libellé de la localité");
        this.libelle = libelle;
    }

    public void setCodePostal(Integer codePostal) throws ErreurrNull {
        if(codePostal == null) throw new ErreurrNull("code postal de la localité");
        this.codePostal = codePostal;
    }

    public void setIdLocalite(Integer idLocalite) throws ErreurrNull {
        if(idLocalite == null) throw new ErreurrNull("id de la localité");
        this.idLocalite = idLocalite;
    }
}
