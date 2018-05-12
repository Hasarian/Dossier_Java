package Model;

import erreurs.ErrorNull;

import java.util.GregorianCalendar;

public class Animal {
    public enum EtatSoin{
        DISPONIBLE,RESERVEE,VETODISPO,VETORESERVEE,ARCHIVEE
    }
    public enum EtatAnimal{
        NORMAL,ARCHIVE,PREMIEREVISITE
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
    private CareGiver careGiver;

    public Animal(Integer id, String remarqueAnimal, Integer numCellule, Race race, String nomAnimal, GregorianCalendar dateArrive, GregorianCalendar dateDesces,
                  Boolean estDangereux, EtatAnimal etatAnimal, String remarqueSoin, EtatSoin etatFicheSoin, CareGiver careGiver)throws ErrorNull{
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
        setCareGiver(careGiver);
    }
    public Animal() throws ErrorNull
    {
        setId(new Integer(1));
        setDateArrive(new GregorianCalendar());
        setDateDesces(new GregorianCalendar());
        setEstDangereux(false);
        setEtatAnimal(EtatAnimal.NORMAL);
        setEtatFicheSoin(EtatSoin.RESERVEE);
        setNomAnimal("rex");
        setNumCellule(5);
        setRace(new Race());
        setRemarqueAnimal("grand et fort, aime les radis");
        setRemaqueSoin("il faut lui donner de l'aspirine avec sa nourriture");
        setCareGiver(new Veterinaire());
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

    public String getNomAnimal(){return nomAnimal;}
    public String getId(){return id.toString();}
    public String getCellNumber(){return numCellule.toString();}
    public String getSpecies(){return race.toString();}
    public EtatSoin getEtatSoin(){return etatFicheSoin;}
    public String getRemarqueAnimal(){return remarqueAnimal;}
    public void setCareGiver(CareGiver careGiver){
        this.careGiver = careGiver;
    }
    public boolean isReservedByUser(String mailReservation)
    {
        return mailReservation.compareTo(careGiver.getMail())==0;
    }

    public GregorianCalendar getDateArrive() {
        return dateArrive;
    }

    public GregorianCalendar getDateDesces() {
        return dateDesces;
    }

    public Boolean getEstDangereux() {
        return estDangereux;
    }

    public CareGiver getCareGiver() {
        return careGiver;
    }
    public String getRemaqueSoin()
    {
        return remaqueSoin;
    }
}
