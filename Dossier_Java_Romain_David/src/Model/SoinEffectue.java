package Model;

import erreurs.ErreurrNull;

import java.util.GregorianCalendar;

public class SoinEffectue {
    private Soignant soignant;
    private GregorianCalendar dateHeure;
    private Integer idSoinEffectue;
    private SoinMedical soinMedical;

    public SoinEffectue(Soignant soignant, GregorianCalendar dateHeure,
                        SoinMedical soinMedical, Integer idSoinEffectue) throws ErreurrNull {
        setSoignant(soignant);
        setDateHeure(dateHeure);
        setFicheSoin(soinMedical);
        setIdSoinEffectue(idSoinEffectue);

    }

    public void setFicheSoin(SoinMedical ficheSoin) throws ErreurrNull {
        if(ficheSoin == null) throw new ErreurrNull();
        this.soinMedical = ficheSoin;
    }

    public void setSoignant(Soignant soignant) throws ErreurrNull {
        if(soignant == null) throw new ErreurrNull();
        this.soignant = soignant;
    }

    public void setDateHeure(GregorianCalendar dateHeure) throws ErreurrNull {
        if(dateHeure == null) throw new ErreurrNull();
        this.dateHeure = dateHeure;
    }

    public void setIdSoinEffectue(Integer idSoinEffectue) throws ErreurrNull {
        if(idSoinEffectue == null) throw new ErreurrNull();
        this.idSoinEffectue = idSoinEffectue;
    }

    public GregorianCalendar getDateHeure() {
        return dateHeure;
    }

    public SoinMedical getFicheSoin() {
        return soinMedical;
    }

    public SoinMedical getSoinMedical() {
        return soinMedical;
    }
}
