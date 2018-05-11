package Model;

import erreurs.ErrorNull;

import java.util.ArrayList;
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
    public CareGiver(String mail, String firstName, String name,
              String street, Integer numMaison, Integer numTel,
              String remarque, Boolean estBenevole,
              Calendar dateEmbauche, Localite localite)throws ErrorNull {
        setDateEmbauche(dateEmbauche);
        setEstBenevole(estBenevole);
        setFirstName(firstName);
        setLocalite(localite);
        setMail(mail);
        setName(name);
        setNumMaison(numMaison);
        setNumTel(numTel);
        setRemarque(remarque);
        setStreet(street);
    }
    public CareGiver(String email)
    {
        mail=email;
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

    public void setDateEmbauche(Calendar dateEmbauche) throws ErrorNull {
        if(dateEmbauche != null)this.dateEmbauche = dateEmbauche;
        else throw new ErrorNull("dateEmbauche");
    }

    public void setEstBenevole(Boolean estBenevole) throws ErrorNull {
        if(estBenevole != null)this.estBenevole = estBenevole;
        else throw new ErrorNull("estBenevole");
    }

    public void setFirstName(String firstName) throws ErrorNull {
        if(firstName != null)this.firstName = firstName;
        else throw new ErrorNull("firstName");
    }

    public void setLocalite(Localite localite) throws ErrorNull {
        if(localite == null) throw new ErrorNull();
        this.localite = localite;
    }

    public void setMail(String mail) throws ErrorNull {
        if(mail == null) throw new ErrorNull();
        this.mail = mail;
    }

    public void setName(String name) throws ErrorNull {
        if(name == null) throw new ErrorNull();
        this.name = name;
    }

    public void setNumMaison(Integer numMaison) throws ErrorNull {
        if(localite == null) throw new ErrorNull();
        this.numMaison = numMaison;
    }

    public void setNumTel(Integer numTel) {
        this.numTel = numTel;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setStreet(String street) throws ErrorNull {
        if(localite == null) throw new ErrorNull();
        this.street = street;
    }



}
