package Model;

import erreurs.ErreurrNull;

import java.util.GregorianCalendar;

public class Soignant {
    private String mail;
    private String prenom;
    private String nomDeFamille;
    private String rue;
    private Integer numMaison;
    private Integer numTel;
    private String remarque;
    private Boolean estBenevole;
    private GregorianCalendar dateEmbauche;
    private Localite localite;
    public Soignant(String mail, String prenom, String nomDeFamille, String rue, Integer numMaison, Integer numTel,
                    String remarque, Boolean estBenevole, GregorianCalendar dateEmbauche, Localite localite)
            throws ErreurrNull
    {
        setDateEmbauche(dateEmbauche);
        setEstBenevole(estBenevole);
        setPrenom(prenom);
        setLocalite(localite);
        setMail(mail);
        setNomDeFamille(nomDeFamille);
        setNumMaison(numMaison);
        setNumTel(numTel);
        setRemarque(remarque);
        setRue(rue);
    }
    public Soignant() throws ErreurrNull
    {
        setDateEmbauche(new GregorianCalendar());
        setEstBenevole(false);
        setPrenom("jean-philippe");
        setLocalite(new Localite());
        setMail("philippe@ford.be");
        setNomDeFamille("Poutou");
        setNumMaison(6);
        setNumTel(new Integer(658462));
        setRemarque("grand et fort");
        setRue("fin du capitalisme.com");
    }
    public Soignant(String email) throws ErreurrNull
    {
        setMail(email);
    }

    public Boolean getEstBenevole() {
        return estBenevole;
    }

    public GregorianCalendar getDateEmbauche() {
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

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getNomDeFamille() {
        return nomDeFamille;
    }

    public String getRemarque() {
        return remarque;
    }

    public String getRue() {
        return rue;
    }

    public void setDateEmbauche(GregorianCalendar dateEmbauche) throws ErreurrNull {
        if(dateEmbauche != null)this.dateEmbauche = dateEmbauche;
        else throw new ErreurrNull("votre date Embauche");
    }

    public void setEstBenevole(Boolean estBenevole) throws ErreurrNull {
        if(estBenevole != null)this.estBenevole = estBenevole;
        else throw new ErreurrNull("etes vous bénévol");
    }

    public void setPrenom(String prenom) throws ErreurrNull {
        if(prenom != null)this.prenom = prenom;
        else throw new ErreurrNull("prenom");
    }

    public void setLocalite(Localite localite) throws ErreurrNull {
        if(localite == null) throw new ErreurrNull();
        this.localite = localite;
    }

    public void setMail(String mail) throws ErreurrNull {
        if(mail == null) throw new ErreurrNull("l'email");
        this.mail = mail;
    }

    public void setNomDeFamille(String nomDeFamille) throws ErreurrNull {
        if(nomDeFamille == null) throw new ErreurrNull("votre nom de famille");
        this.nomDeFamille = nomDeFamille;
    }

    public void setNumMaison(Integer numMaison) throws ErreurrNull {
        if(numMaison == null) throw new ErreurrNull("votre numero de maison");
        this.numMaison = numMaison;
    }

    public void setNumTel(Integer numTel) {
        this.numTel = numTel;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setRue(String rue) throws ErreurrNull {
        if(rue == null) throw new ErreurrNull("votre rue");
        this.rue = rue;
    }

    public String toString()
    {
        return prenom +" "+ nomDeFamille;
    }

}
