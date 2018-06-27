package dataAccess;

import dataAccess.dao.DAORechercheSoinEffectue;
import model.Animal;
import model.SoinEffectue;
import model.SoinMedical;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.MauvaiseTailleString;
import erreurs.SoignantInexistant;

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
    public ArrayList<SoinEffectue> searchHistory(String mail) throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant,MauvaiseTailleString {
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

    public SoinEffectue traductionSQL(ResultSet data) throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant, MauvaiseTailleString
    {
        try {
            String mail = data.getString("soignant.mail");
            /*GregorianCalendar dateArrive = new GregorianCalendar();
                dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));*/
            GregorianCalendar dateSoin=new GregorianCalendar();
            dateSoin.setTime( data.getDate("soinEffectue.dateSoin"));
            Integer idSoineffectue= new Integer(data.getInt("soinEffectue.idSoinEffectue"));

            Integer idSoinMedical= new Integer(data.getInt("soinMedical.idSoinMedical"));
            Animal animal=new AnimalDataAccess().read((data.wasNull())?null : data.getInt("soinMedical.numDossier"));
            GregorianCalendar dateSoinMedical=new GregorianCalendar();
            dateSoinMedical.setTime( data.getDate("soinMedical.dateSoinMedical"));//data.getDate("soinMdecial.dateSoinMedical")
            GregorianCalendar heureSoin=new GregorianCalendar();
            heureSoin.setTime((data.wasNull())?null : data.getTime("soinMedical.heureSoinMedical"));
            String description=data.getString("soinMedical.description");
            String remarqueSoin=(data.wasNull())?null : data.getString("soinMedical.remarque");
            Integer numOrdonnance=new Integer(data.getInt("soinMedical.numOrdonnance"));
            String mailVeto =(data.wasNull())?null :  data.getString("soinMedical.mailVeto");
            SoinMedical soinMedical=new SoinMedical(idSoinMedical,animal,dateSoin,heureSoin,description,remarqueSoin,numOrdonnance, mailVeto);

            String remarque=(data.wasNull())?null:data.getString("soinEffectue.remarque");

            return new SoinEffectue(new SoignantDataAccess().read(mail),dateSoin,soinMedical,idSoineffectue,remarque);
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
