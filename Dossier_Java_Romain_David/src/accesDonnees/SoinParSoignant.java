package accesDonnees;

import accesDonnees.dao.DAORechercheSoinEffectue;
import erreurs.Erreur;
import modèle.Animal;
import modèle.SoinEffectue;
import modèle.SoinMedical;
import erreurs.erreursExternes.DonneePermanenteErreur;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoinParSoignant
implements DAORechercheSoinEffectue
{
    private Connection connection;
    public SoinParSoignant() throws DonneePermanenteErreur
    {
        connection=SingletonDB.getInstance();
    }
    public ArrayList<SoinEffectue> searchHistory(String mail) throws Erreur {
        String sql = "select *from soignant, localite, soineffectue, soinmedical\n" +
                "where soignant.mail = ? and soignant.localite = localite.idLocalite\n" +
                "and soineffectue.mail = soignant.mail and soineffectue.numSoinMedical = soinmedical.idSoinMedical; ";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mail);
            ResultSet data = statement.executeQuery();
            ArrayList<SoinEffectue> soinsEffectués=new ArrayList<SoinEffectue>();
            while(data.next())
            {
                soinsEffectués.add(traductionSQL(data));
            }
            return soinsEffectués;
        }

        catch(SQLException e){
            new DonneePermanenteErreur(e.getMessage());
            return null;
        }
    }

    @Override
    public void create(String mailSoignant, GregorianCalendar heureEffectuee, Integer soinMedical, String remarque)
            throws DonneePermanenteErreur
    {
        /*mail varchar(50) not null,
dateSoin dateTime not null,
numSoinMedical integer(10) not null,
idSoinEffectue integer(10) auto_increment,
remarque varchar(140),*/
        String sql="insert into soinEffectue(mail,dateSoin,numSoinMedical, remarque) values(?,?,?,?)";
        /*                                   1      2          3 */

        try {
            PreparedStatement instruction = connection.prepareStatement(sql);
            instruction.setString(1,mailSoignant);
            instruction.setDate(2,new java.sql.Date(heureEffectuee.getTimeInMillis()));
            instruction.setInt(3,soinMedical);
            instruction.setString(4,remarque);
            instruction.execute();
        }catch (SQLException bdConnexionErreur)
        {
            throw new DonneePermanenteErreur(bdConnexionErreur.getMessage());
        }

    }

    public SoinEffectue traductionSQL(ResultSet data) throws Erreur
    {
        try {
            String mail = data.getString("soignant.mail");
            /*GregorianCalendar dateArrive = new GregorianCalendar();
                dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));*/
            GregorianCalendar dateSoin=new GregorianCalendar();
            dateSoin.setTime( data.getDate("soinEffectue.dateSoin"));
            Integer idSoineffectue= new Integer(data.getInt("soinEffectue.idSoinEffectue"));

            Integer idSoinMedical= new Integer(data.getInt("soinMedical.idSoinMedical"));
            Integer numDossier= data.getInt("soinMedical.numDossier");
            Animal animal=null;
            if(!data.wasNull())animal= new AnimalDonnees().read(numDossier);
            GregorianCalendar dateSoinMedical=new GregorianCalendar();
            dateSoinMedical.setTime( data.getDate("soinMedical.dateSoinMedical"));//data.getDate("soinMdecial.dateSoinMedical")
            GregorianCalendar heureSoin=new GregorianCalendar();
            Time heure=data.getTime("soinMedical.heureSoinMedical");
            if (data.wasNull()) heureSoin=null;
            else heureSoin.setTime(heure);
            String description=data.getString("soinMedical.description");
            String remarqueSoin=data.getString("soinMedical.remarque");
            if(data.wasNull()) remarqueSoin=null;
            Integer numOrdonnance=new Integer(data.getInt("soinMedical.numOrdonnance"));
            String mailVeto =data.getString("soinMedical.mailVeto");
            if(data.wasNull())mailVeto =null ;
            SoinMedical soinMedical=new SoinMedical(idSoinMedical,animal,dateSoin,heureSoin,description,remarqueSoin,numOrdonnance, mailVeto);

            String remarque=(data.wasNull())?null:data.getString("soinEffectue.remarque");

            return new SoinEffectue(new SoignantDonnees().read(mail),dateSoin,soinMedical,idSoineffectue,remarque);
        }
        catch(SQLException e)
        {
            new DonneePermanenteErreur(e.getMessage());
            return null;
        }
    }
/*

 */
}
