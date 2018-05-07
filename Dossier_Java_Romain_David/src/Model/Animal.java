package Model;

import erreurs.ErrorNull;

import java.util.GregorianCalendar;

public class Animal {
    public enum EtatSoin{
        DISPONIBLE,RESERVEE,VETODISPO,VETORESERVEE
    }
    public enum EtatAnimal{
        NORMAL,ARCHIVE,
    }
    private Integer id;
    private String remarqueAnimal;
    private Integer numCellule;
    private String nomAnimal;
    private Race race;
    private GregorianCalendar dateArrive;
    private GregorianCalendar dateDesces;
    private Boolean estDangereux;
    private EtatAnimal etatFicheAnimal;
    private String remaqueSoin;
    private EtatSoin etatFicheSoin;

    public Animal(Integer id, String remarqueAnimal, Integer numCellule, Race race, String nomAnimal, GregorianCalendar dateArrive, GregorianCalendar dateDesces,
                  Boolean estDangereux, EtatAnimal etatAnimal, String remarqueSoin, EtatSoin etatFicheSoin)throws ErrorNull{
        setId(id);
        setDateArrive(dateArrive);
        setDateDesces(dateDesces);
        setEstDangereux(estDangereux);
        setEtatAnimal(etatAnimal);
        setEtatFicheSoin(etatFicheSoin);
        setNomAnimal(nomAnimal);
        setNumCellule(numCellule);
        setRace(race);
        setRemarqueAnimal(remarqueAnimal);
        setRemaqueSoin(remarqueSoin);

    }

    public void setDateArrive(GregorianCalendar dateArrive) throws ErrorNull {
        if(dateArrive == null) throw new ErrorNull();
        this.dateArrive = dateArrive;
    }

    public void setNomAnimal(String nomAnimal) throws ErrorNull {
        if(nomAnimal == null) throw new ErrorNull();
        this.nomAnimal = nomAnimal;
    }

    public void setEtatFicheSoin(EtatSoin etatFicheSoin) throws ErrorNull {
        if(etatFicheSoin == null) throw new ErrorNull();
        this.etatFicheSoin = etatFicheSoin;
    }

    public void setRace(Race race) throws ErrorNull {
        if(race == null) throw new ErrorNull();
        this.race = race;
    }

    public void setEtatAnimal(EtatAnimal etatAnimal) throws ErrorNull {
        if(etatAnimal == null)
        this.etatFicheAnimal = etatAnimal;
    }

    public void setEstDangereux(Boolean estDangereux) throws ErrorNull {
        if(estDangereux == null) throw new ErrorNull();
        this.estDangereux = estDangereux;
    }

    public void setRemarqueAnimal(String remarqueAnimal) {
        this.remarqueAnimal = remarqueAnimal;
    }

    public void setRemaqueSoin(String remaqueSoin) {
        this.remaqueSoin = remaqueSoin;
    }

    public void setNumCellule(Integer numCellule) throws ErrorNull {
        if(numCellule == null) throw new ErrorNull();
        this.numCellule = numCellule;
    }

    public void setId(Integer id) throws ErrorNull {
        if(id == null) throw new ErrorNull();
        this.id = id;
    }

    public void setDateDesces(GregorianCalendar dateDesces) {
        this.dateDesces = dateDesces;
    }

    public EtatAnimal getEtatAnimal(){
        return etatFicheAnimal;
    }

    public EtatSoin getEtatFicheSoin() {
        return etatFicheSoin;
    }

    /* "name",
             "id",
             "cell number",
             "species",*/
    public String getNomAnimal(){return nomAnimal;}
    public String getId(){return id.toString();}
    public String getCellNumber(){return numCellule.toString();}
    public String getSpecies(){return race.toString();}
    public EtatSoin getEtatSoin(){return etatFicheSoin;}
}
