package Model;

import erreurs.ErrorNull;

import java.util.GregorianCalendar;

public class SoinEffectue {
    private CareGiver careGiver;
    private GregorianCalendar dateHeure;
    private Animal ficheSoin;
    private Integer idSoinEffectue;

    public SoinEffectue(CareGiver careGiver, GregorianCalendar dateHeure,
                        Animal ficheSoin, Integer idSoinEffectue) throws ErrorNull{
        setCareGiver(careGiver);
        setDateHeure(dateHeure);
        setFicheSoin(ficheSoin);
        setIdSoinEffectue(idSoinEffectue);

    }

    public void setFicheSoin(Animal ficheSoin) throws ErrorNull {
        if(ficheSoin == null) throw new ErrorNull();
        this.ficheSoin = ficheSoin;
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

}
