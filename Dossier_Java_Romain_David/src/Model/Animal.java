package Model;

import erreurs.ErrorNull;

import java.util.GregorianCalendar;

public class Animal {
    private Integer id;
    private String remarqueAnimal;
    private Integer numCellule;
    private String nomAnimal;
    private Race race;
    private GregorianCalendar dateArrive;
    private GregorianCalendar dateDesces;
    private Boolean estDangereux;
    private String etatAnimal;
    private String remaqueSoin;
    private Integer etatFicheSoin;

    public Animal(Integer id, String remarqueAnimal, Integer numCellule, Race race, String nomAnimal, GregorianCalendar dateArrive, GregorianCalendar dateDesces,
                  Boolean estDangereux, String etatAnimal, String remarqueSoin, Integer etatFicheSoin)throws ErrorNull{
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

    public void setEtatFicheSoin(Integer etatFicheSoin) throws ErrorNull {
        if(etatFicheSoin == null) throw new ErrorNull();
        this.etatFicheSoin = etatFicheSoin;
    }

    public void setRace(Race race) throws ErrorNull {
        if(race == null) throw new ErrorNull();
        this.race = race;
    }

    public void setEtatAnimal(String etatAnimal) throws ErrorNull {
        if(etatAnimal == null) throw new ErrorNull();
        this.etatAnimal = etatAnimal;
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

    public String getEtatAnimal(){
        return etatAnimal;
    }

    public Integer getEtatFicheSoin() {
        return etatFicheSoin;
    }
}
