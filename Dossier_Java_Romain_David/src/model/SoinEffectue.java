package model;

import erreurs.erreurFormat.ErreurrNull;
import erreurs.erreurFormat.MauvaiseTailleString;

import java.util.GregorianCalendar;

public class SoinEffectue {
    private Soignant soignant;
    private GregorianCalendar dateHeure;
    private Integer idSoinEffectue;
    private String remarque;
    private SoinMedical soinMedical;

    public SoinEffectue(Soignant soignant, GregorianCalendar dateHeure,
                        SoinMedical soinMedical, Integer idSoinEffectue,String remarque) throws ErreurrNull,MauvaiseTailleString {
        setSoignant(soignant);
        setDateHeure(dateHeure);
        setFicheSoin(soinMedical);
        setIdSoinEffectue(idSoinEffectue);
        setRemarque(remarque);
    }

    public void setFicheSoin(SoinMedical ficheSoin) throws ErreurrNull {
        if(ficheSoin == null) throw new ErreurrNull("soin référence");
        this.soinMedical = ficheSoin;
    }
    public void setRemarque(String remarque) throws MauvaiseTailleString
    {

        if( remarque != null && remarque.length()>140) throw new MauvaiseTailleString("une remarque ajoutée ",remarque.length(),150);
        this.remarque=remarque;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setSoignant(Soignant soignant) throws ErreurrNull {
        if(soignant == null) throw new ErreurrNull("soignant");
        this.soignant = soignant;
    }

    public void setDateHeure(GregorianCalendar dateHeure) throws ErreurrNull {
        if(dateHeure == null) throw new ErreurrNull("heure d'exécution");
        this.dateHeure = dateHeure;
    }

    public void setIdSoinEffectue(Integer idSoinEffectue) throws ErreurrNull {
        //if(idSoinEffectue == null) throw new ErreurrNull();
        //auto-increment: valeur donnée par la BD, on doit pouvoir créer un objet de ce type sans remplir ce champ !
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
