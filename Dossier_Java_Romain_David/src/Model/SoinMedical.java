package Model;

import erreurs.ErreurrNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SoinMedical {
    private Integer idSoinMedical;
    private Animal ficheSoin;
    private GregorianCalendar date;
    private GregorianCalendar heure;
    private String descriptionSoin;
    private String remarque;
    private Integer numOrdonnance;
    private String mailVeto;

    public SoinMedical(Integer idSoinMedical, Animal ficheSoin, GregorianCalendar date,GregorianCalendar heure,
                       String descriptionSoin,String remarque, Integer numOrdonnance, String veterinaire)
            throws ErreurrNull
    {
        setDescriptionSoin(descriptionSoin);
        setDate(date);
        setFicheSoin(ficheSoin);
        setIdSoinMedical(idSoinMedical);
        setNumOrdonnance(numOrdonnance);
        setRemarque(remarque);
        this.heure=heure;
        setMailVeto(veterinaire);


    }
    public SoinMedical(int version) throws ErreurrNull
    {
        String descriptionSoin,remarque,veterinaire;
        veterinaire="maquaque";
        GregorianCalendar date,heure;
        date=new GregorianCalendar();
        Animal ficheSoin= new Animal();
        Integer idSoinMedical=new Integer(1);
        Integer numOrdonnance=new Integer(2);
        switch (version)
        {
            case 0:
                descriptionSoin="à manger";
                remarque=null;
                heure=new GregorianCalendar();
                heure.set(Calendar.HOUR_OF_DAY,12);
                heure.set(Calendar.MINUTE,00);
                break;
            case 1:
                descriptionSoin="à manger";
                remarque=null;
                heure=new GregorianCalendar();
                heure.set(Calendar.HOUR_OF_DAY,19);
                heure.set(Calendar.MINUTE,00);
                break;
            case 2:
                descriptionSoin="à manger";
                remarque="avec une aspirine";
                heure=new GregorianCalendar();
                heure.set(Calendar.HOUR_OF_DAY,9);
                heure.set(Calendar.MINUTE,00);
                break;
            case 3:
                descriptionSoin="prendre la température";
                remarque=null;
                heure=null;
                break;
            case 4:
                descriptionSoin="prendre la température";
                remarque="établir des statistiques de l'évolution de la température sur 15 minutes";
                heure=new GregorianCalendar();
                heure.set(Calendar.HOUR_OF_DAY,18);
                heure.set(Calendar.MINUTE,30);
                break;
            case 5:
                descriptionSoin="donner une aspirine";
                remarque=null;
                heure=new GregorianCalendar();
                heure.set(Calendar.HOUR_OF_DAY,16);
                heure.set(Calendar.MINUTE,15);
                break;
            default:
                descriptionSoin="promener";
                remarque=null;
                heure=null;
        }

        setDescriptionSoin(descriptionSoin);
        setDate(date);
        setFicheSoin(ficheSoin);
        setIdSoinMedical(idSoinMedical);
        setNumOrdonnance(numOrdonnance);
        setRemarque(remarque);
        this.heure=heure;
        setMailVeto(veterinaire);
    }

    public void setDate(GregorianCalendar date) throws ErreurrNull {
        if(date == null) throw new ErreurrNull();
        this.date = date;
    }

   /* public void setSoignant(Soignant careGiver) throws ErreurrNull {
        if(careGiver == null) throw new ErreurrNull();
        this.careGiver = careGiver;
    }*/
    public void setDescriptionSoin(String descriptionSoin) throws ErreurrNull
    {
        if(descriptionSoin == null) throw new ErreurrNull();
        this.descriptionSoin=descriptionSoin;
    }
    public void setRemarque(String remarque){

        this.remarque = remarque;
    }

    public void setFicheSoin(Animal ficheSoin) throws ErreurrNull {
        if(ficheSoin == null) throw new ErreurrNull();
        this.ficheSoin = ficheSoin;
    }

    public void setIdSoinMedical(Integer idSoinMedical) throws ErreurrNull {
        if(idSoinMedical == null) throw new ErreurrNull();
        this.idSoinMedical = idSoinMedical;
    }

    public void setNumOrdonnance(Integer numOrdonnance) throws ErreurrNull {
        if(numOrdonnance == null) throw new ErreurrNull();
        this.numOrdonnance = numOrdonnance;
    }

    public void setMailVeto(String mailVeto) throws ErreurrNull {
        if(numOrdonnance == null) throw new ErreurrNull();
        this.mailVeto = mailVeto;
    }

    public Animal getFicheSoin(){
        return ficheSoin;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getRemarque() {
        return remarque;
    }

    public GregorianCalendar getHeure() {
        return heure;
    }

    public Integer getIdSoinMedical() {
        return idSoinMedical;
    }

    public Integer getNumOrdonnance() {
        return numOrdonnance;
    }

    public String getMailVeto() {
        return mailVeto;
    }
    public String getDescriptionSoin(){return descriptionSoin;}
}
