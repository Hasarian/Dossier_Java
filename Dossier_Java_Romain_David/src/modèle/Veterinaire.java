package modèle;

import erreurs.erreurFormat.ErreurrNull;

import java.util.GregorianCalendar;

public class Veterinaire extends Soignant
{

    public Veterinaire(String mail, String firstName, String name, String street, Integer numMaison, Integer numTel, String remarque, Boolean estBenevole, GregorianCalendar dateEmbauche, Localite localite) throws ErreurrNull {
        super(mail, firstName, name, street, numMaison, numTel, remarque, estBenevole, dateEmbauche, localite);
    }
   public Veterinaire() throws ErreurrNull
    {
        super();

    }
    @Override
    public String toString() {
        return super.toString()+" (vétérinaire)";
    }
}
