package Model;

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

    public Animal(Integer id, String remarqueAnimal, Integer numCellule, String race, String nomAnimal, GregorianCalendar dateArrive, GregorianCalendar dateDesces,
                  Boolean estDangereux, String etatAnimal, String remarqueSoin, Integer etatFicheSoin){

    }

    public void setDateArrive(GregorianCalendar dateArrive) {
        this.dateArrive = dateArrive;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public void setEtatFicheSoin(Integer etatFicheSoin) {
        this.etatFicheSoin = etatFicheSoin;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setEtatAnimal(String etatAnimal) {
        this.etatAnimal = etatAnimal;
    }

    public void setEstDangereux(Boolean estDangereux) {
        this.estDangereux = estDangereux;
    }
}
