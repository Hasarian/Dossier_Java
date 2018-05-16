package Model;

import erreurs.ErrorNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Veterinaire extends CareGiver
{

    public Veterinaire(String mail, String firstName, String name, String street, Integer numMaison, Integer numTel, String remarque, Boolean estBenevole, GregorianCalendar dateEmbauche, Localite localite) throws ErrorNull {
        super(mail, firstName, name, street, numMaison, numTel, remarque, estBenevole, dateEmbauche, localite);
    }
   /* public Veterinaire() throws ErrorNull
    {
        super();

    }*/
    @Override
    public String toString() {
        return super.toString()+" (vétérinaire)";
    }
}
