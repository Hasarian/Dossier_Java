package modèle;

import erreurs.erreurFormat.ErreurrNull;

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
    private Soignant soignant;

    public Animal(Integer id, String remarqueAnimal, Integer numCellule, Race race, String nomAnimal, GregorianCalendar dateArrive, GregorianCalendar dateDesces,
                  Boolean estDangereux, EtatAnimal etatAnimal, String remarqueSoin, EtatSoin etatFicheSoin, Soignant soignant)throws ErreurrNull {
        //System.out.print("modèle animal: créé");
        setId(id);
        setDateArrive(dateArrive);
        setDateDeces(dateDesces);
        setEstDangereux(estDangereux);
        setEtatAnimal(etatAnimal);
        setEtatFicheSoin(etatFicheSoin);
        setNomAnimal(nomAnimal);
        setNumCellule(numCellule);
        setRace(race);
        setRemarqueAnimal(remarqueAnimal);
        setRemaqueSoin(remarqueSoin);
        setSoignant(soignant);
    }
    public Animal() throws ErreurrNull
    {
        setId(new Integer(1));
        setDateArrive(new GregorianCalendar());
        setDateDeces(new GregorianCalendar());
        setEstDangereux(false);
        setEtatAnimal(EtatAnimal.NORMAL);
        setEtatFicheSoin(EtatSoin.RESERVEE);
        setNomAnimal("rex");
        setNumCellule(5);
        setRace(new Race());
        setRemarqueAnimal("grand et fort, aime les radis");
        setRemaqueSoin("il faut lui donner de l'aspirine avec sa nourriture");
        setSoignant(new Veterinaire());
    }

    public void setDateArrive(GregorianCalendar dateArrive) throws ErreurrNull {
        if(dateArrive == null) throw new ErreurrNull("date d'arrivée");
        this.dateArrive = dateArrive;
    }

    public void setNomAnimal(String nomAnimal) throws ErreurrNull {
        if(nomAnimal == null) throw new ErreurrNull("nom de l'animal");
        this.nomAnimal = nomAnimal;
    }

    public void setEtatFicheSoin(EtatSoin etatFicheSoin) throws ErreurrNull {
        if(etatFicheSoin == null) throw new ErreurrNull("fiche soin");
        this.etatFicheSoin = etatFicheSoin;

    }

    public void setRace(Race race) throws ErreurrNull {
        if(race == null) throw new ErreurrNull("race");
        this.race = race;
    }

    public void setEtatAnimal(EtatAnimal etatAnimal) throws ErreurrNull {
        if(etatAnimal == null) throw  new ErreurrNull("état de l'animal");
        this.etatFicheAnimal = etatAnimal;
    }

    public void setEstDangereux(Boolean estDangereux) throws ErreurrNull {
        if(estDangereux == null) throw new ErreurrNull("dangerosité");
        this.estDangereux = estDangereux;
    }

    public void setRemarqueAnimal(String remarqueAnimal) {
        this.remarqueAnimal = remarqueAnimal;
    }

    public void setRemaqueSoin(String remaqueSoin) {
        this.remaqueSoin = remaqueSoin;
    }

    public void setNumCellule(Integer numCellule) throws ErreurrNull {
        if(numCellule == null) throw new ErreurrNull("numéro de cellule");
        this.numCellule = numCellule;
    }

    public void setId(Integer id) throws ErreurrNull {
        if(id == null) throw new ErreurrNull("id de l'animal");
        this.id = id;
    }

    public void setDateDeces(GregorianCalendar dateDesces) {
        this.dateDesces = dateDesces;
    }

    public EtatAnimal getEtatAnimal(){
        return etatFicheAnimal;
    }

    public EtatSoin getEtatFicheSoin() {
        return etatFicheSoin;
    }

    public String getNomAnimal(){return nomAnimal;}
    public Integer getId(){return id;}
    public String getNumeroCellule(){return numCellule.toString();}
    public String getEspece(){return race.getEspeceLibelle();}
    public EtatSoin getEtatSoin(){return etatFicheSoin;}
    public String getRemarqueAnimal(){return remarqueAnimal;}
    public void setSoignant(Soignant soignant){
        this.soignant = soignant;
    }
    public boolean estReserveParlUtilisateurCourant(String mailReservation)
    {
        return mailReservation.compareTo(soignant.getMail())==0;
    }

    public GregorianCalendar getDateArrive() {
        return dateArrive;
    }

    public GregorianCalendar getDateDeces() {
        return dateDesces;
    }

    public Boolean getEstDangereux() {
        return estDangereux;
    }

    public Soignant getSoignant() {
        return soignant;
    }
    public String getRemaqueSoin()
    {
        return remaqueSoin;
    }

    public Race getRace() {
        return race;
    }
}
