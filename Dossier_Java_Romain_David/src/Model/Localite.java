package Model;

public class Localite {
    private Integer idLocalite;
    private Integer codePostal;
    private String libelle;

    Localite(Integer idLocalite, Integer codePostal, String libelle) {
        this.codePostal = codePostal;
        this.idLocalite = idLocalite;
        this.libelle = libelle;
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
}
