package Model;

import erreurs.ErrorNull;

import java.util.GregorianCalendar;

public class SoinEffectue {
    private CareGiver careGiver;
    private GregorianCalendar dateHeure;
    private Integer idSoinEffectue;
    private SoinMedical soinMedical;
    private String remarque;

    public SoinEffectue(CareGiver careGiver, GregorianCalendar dateHeure,
                        SoinMedical soinMedical, Integer idSoinEffectue,String remarque) throws ErrorNull{
        setCareGiver(careGiver);
        setDateHeure(dateHeure);
        setFicheSoin(soinMedical);
        setIdSoinEffectue(idSoinEffectue);
        this.remarque=remarque;

    }

    public void setFicheSoin(SoinMedical ficheSoin) throws ErrorNull {
        if(ficheSoin == null) throw new ErrorNull();
        this.soinMedical = ficheSoin;
    }

    public void setCareGiver(CareGiver careGiver) throws ErrorNull {
        if(careGiver == null) throw new ErrorNull();
        this.careGiver = careGiver;
    }

    public void setDateHeure(GregorianCalendar dateHeure) throws ErrorNull {
        if(dateHeure == null) throw new ErrorNull();
        this.dateHeure = dateHeure;
    }

    public void setIdSoinEffectue(Integer idSoinEffectue) throws ErrorNull {
        if(idSoinEffectue == null) throw new ErrorNull();
        this.idSoinEffectue = idSoinEffectue;
    }

    public GregorianCalendar getDateHeure() {
        return dateHeure;
    }

    public SoinMedical getFicheSoin() {
        return soinMedical;
    }
    public String getRemarque(){
        return remarque;
    }
}
