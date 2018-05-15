package Model;

import erreurs.ErrorNull;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

public class SoinMedical {
    private Integer idSoinMedical;
    private Animal ficheSoin;
    private GregorianCalendar date;
    private GregorianCalendar heure;
    private String remarque;
    private Integer numOrdonnance;

    public SoinMedical(Integer idSoinMedical, Animal ficheSoin, GregorianCalendar date,GregorianCalendar heure,String remarque, Integer numOrdonnance) throws ErrorNull{
        setDate(date);
        setFicheSoin(ficheSoin);
        setIdSoinMedical(idSoinMedical);
        setNumOrdonnance(numOrdonnance);
        setRemarque(remarque);
        this.heure=heure;

    }

    public void setDate(GregorianCalendar date) throws ErrorNull {
        if(date == null) throw new ErrorNull();
        this.date = date;
    }

   /* public void setCareGiver(CareGiver careGiver) throws ErrorNull {
        if(careGiver == null) throw new ErrorNull();
        this.careGiver = careGiver;
    }*/

    public void setRemarque(String remarque) throws ErrorNull {
        if(remarque == null) throw new ErrorNull();
        this.remarque = remarque;
    }

    public void setFicheSoin(Animal ficheSoin) throws ErrorNull {
        if(ficheSoin == null) throw new ErrorNull();
        this.ficheSoin = ficheSoin;
    }

    public void setIdSoinMedical(Integer idSoinMedical) throws ErrorNull {
        if(idSoinMedical == null) throw new ErrorNull();
        this.idSoinMedical = idSoinMedical;
    }

    public void setNumOrdonnance(Integer numOrdonnance) throws ErrorNull {
        if(numOrdonnance == null) throw new ErrorNull();
        this.numOrdonnance = numOrdonnance;
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
}
