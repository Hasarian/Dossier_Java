package modèle;

import erreurs.erreurFormat.ErreurrNull;

//setter des String pour empecher les texte trop long
public class Race {
    private String libelle;
    private String traitDeCaractere;
    private String tare;
    private String CaracteristiqueDuMilieuDeVie;
    private Espece espece;

    public Race(String libelle, String traitDeCaractere, String tare, String caracteristiqueDuMilieuDeVie, Espece espece) throws ErreurrNull {
        setCaracteristiqueDuMilieuDeVie(caracteristiqueDuMilieuDeVie);
        setEspece(espece);
        setLibelle(libelle);
        setTare(tare);
        setTraitDeCaractere(traitDeCaractere);
    }
    public Race() throws ErreurrNull
    {
        setLibelle("labrador");
        setTraitDeCaractere("joyeux");
        setCaracteristiqueDuMilieuDeVie("urbain");
        setTare("sale");
        setEspece(new Espece());
    }

    public void setLibelle(String libelle) throws ErreurrNull {
        if(libelle != null) this.libelle = libelle;
        else throw new ErreurrNull("libelle de l'objet modele \"Race\"");
    }

    public void setCaracteristiqueDuMilieuDeVie(String caracteristiqueDuMilieuDeVie) {
        CaracteristiqueDuMilieuDeVie = caracteristiqueDuMilieuDeVie;//varchar(75)
    }

    public void setEspece(Espece espece) throws ErreurrNull {
        if(espece!= null) this.espece = espece;//varchar(50)
        else throw new ErreurrNull("espece de l'objet modele \"Race\"");
    }

    public void setTare(String tare) {
        this.tare = tare;//varchar(50)
    }

    public void setTraitDeCaractere(String traitDeCaractere) throws ErreurrNull {
        if(traitDeCaractere==null) throw  new ErreurrNull("trait de caractere de l'objet modele \"Race\"");
        this.traitDeCaractere = traitDeCaractere;//varchar(50)
    }
    public String getEspeceLibelle(){return espece.getLibelle();}
    public String getLibelle(){return libelle;}
}
   /* create table race(
        libelle varchar(50),
    traitDeCaractere varchar(50) not null,
        tare varchar(50),
        CaracteristiqueDuMilieuDeVie varchar(75),
        espece varchar(50),
        constraint race_libelle_pk primary key(libelle),
        constraint espece_libelle_fk foreign key (espece) references espece(libelle)
        )engine = InnoDB;*/
