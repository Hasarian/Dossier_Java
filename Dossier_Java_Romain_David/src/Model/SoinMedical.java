package Model;

import erreurs.ErreurrNull;

import java.util.GregorianCalendar;

public class SoinMedical {
    private Integer idSoinMedical;
    private Animal ficheSoin;
    private GregorianCalendar date;
    private GregorianCalendar heure;
    private String remarque;
    private Integer numOrdonnance;
    private String mailVeto;

    public SoinMedical(Integer idSoinMedical, Animal ficheSoin, GregorianCalendar date,GregorianCalendar heure,String remarque, Integer numOrdonnance, String veterinaire) throws ErreurrNull {
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

    public void setRemarque(String remarque) throws ErreurrNull {
        if(remarque == null) throw new ErreurrNull();
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
}
