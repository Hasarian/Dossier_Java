package Model;

import java.util.Calendar;

public class CareGiver {
    private String mail;
    private String firstName;
    private String name;
    private String street;
    private Integer numMaison;
    private Integer numTel;
    private String remarque;
    private Boolean estBenevole;
    private Calendar dateEmbauche;
    private Localite localite;
    CareGiver(String mail, String firstName, String name,
              String street, Integer numMaison, Integer numTel,
              String remarque, Boolean estBenevole,
              Calendar dateEmbauche, Localite localite){
        this.mail = mail;
        this.firstName = firstName;
        this.name = name;
        this.street = street;
        this.numMaison = numMaison;
        this.numTel = numTel;
        this.remarque = remarque;
        this.estBenevole = estBenevole;
        this.dateEmbauche = dateEmbauche;
        this.localite = localite;
    }

    public Boolean getEstBenevole() {
        return estBenevole;
    }

    public Calendar getDateEmbauche() {
        return dateEmbauche;
    }

    public Integer getNumMaison() {
        return numMaison;
    }

    public Integer getNumTel() {
        return numTel;
    }

    public Localite getLocalite() {
        return localite;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getRemarque() {
        return remarque;
    }

    public String getStreet() {
        return street;
    }
}
