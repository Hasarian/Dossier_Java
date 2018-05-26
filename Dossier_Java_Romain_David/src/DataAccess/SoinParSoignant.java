package DataAccess;

import Business.AnimalBusiness;
import Business.SoignantBusiness;
import Business.ListeAnimalBusiness;
import DataAccess.DAO.DAORechercheSoinEffectue;
import Model.Animal;
import Model.SoinEffectue;
import Model.SoinMedical;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.MauvaiseTailleString;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoinParSoignant
implements DAORechercheSoinEffectue
{
    private Connection connection;
    private SoignantBusiness giverBusiness;
    public SoinParSoignant() throws BDConnexionErreur
    {
        connection=SingletonDB.getInstance();
        giverBusiness= SoignantBusiness.otebnirSoignantBusiness();
    }
    public ArrayList<SoinEffectue> searchHistory(String mail) throws BDConnexionErreur, ErreurrNull, SoignantInexistant,MauvaiseTailleString {
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
            new BDConnexionErreur(e.getMessage());
            return null;
        }
    }

    @Override
    public void create(String mailSoignant, GregorianCalendar heureEffectuee, Integer soinMedical, String remarque)
            throws BDConnexionErreur
    {
        /*mail varchar(50) not null,
dateSoin dateTime not null,
numSoinMedical integer(10) not null,
idSoinEffectue integer(10) auto_increment,
remarque varchar(140),*/
        String sql="insert into soinEffectue(mail,dateSoin,numSoinMedical) values(?,?,?)";
        /*                                   1      2          3 */

        try {
            PreparedStatement instruction = connection.prepareStatement(sql);
            instruction.setString(1,mailSoignant);
            instruction.setTime(2,new java.sql.Time(heureEffectuee.getTimeInMillis()));
            instruction.setInt(3,soinMedical);
        }catch (SQLException bdConnexionErreur)
        {
            throw new BDConnexionErreur(bdConnexionErreur.getMessage());
        }

    }

    public SoinEffectue traductionSQL(ResultSet data) throws ErreurrNull, BDConnexionErreur, SoignantInexistant, MauvaiseTailleString
    {
        try {
            AnimalBusiness animalBusiness=AnimalBusiness.obtenirAnimalBusiness(ListeAnimalBusiness.obtenirListAnimalBusiness(new SoignantController()));
            String mail = data.getString("soignant.mail");
            /*GregorianCalendar dateArrive = new GregorianCalendar();
                dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));*/
            GregorianCalendar dateSoin=new GregorianCalendar();
            dateSoin.setTime((data.wasNull())?null : data.getDate("soinEffectue.dateSoin"));
            Integer idSoineffectue=(data.wasNull())?null : new Integer(data.getInt("soinEffectue.idSoinEffectue"));

            Integer idSoinMedical=(data.wasNull())?null : new Integer(data.getInt("soinMedical.idSoinMedical"));
            Animal animal=animalBusiness.getAnimal((data.wasNull())?null : data.getInt("soinMedical.numDossier"));
            GregorianCalendar dateSoinMedical=new GregorianCalendar();
            dateSoinMedical.setTime((data.wasNull())?null : data.getDate("soinMedical.dateSoinMedical"));//data.getDate("soinMdecial.dateSoinMedical")
            GregorianCalendar heureSoin=new GregorianCalendar();
            heureSoin.setTime((data.wasNull())?null : data.getTime("soinMedical.heureSoinMediacl"));
            String description=(data.wasNull())?null:data.getString("soinMedical.description");
            String remarqueSoin=(data.wasNull())?null : data.getString("soinMedical.remarque");
            Integer numOrdonnance=(data.wasNull())?null : new Integer(data.getInt("soinMedical.numOrdonnance"));
            String mailVeto =(data.wasNull())?null :  data.getString("soinMedical.mailVeto");
            SoinMedical soinMedical=new SoinMedical(idSoinMedical,animal,dateSoin,heureSoin,description,remarqueSoin,numOrdonnance, mailVeto);

            String remarque=(data.wasNull())?null:data.getString("soinEffectue.remarque");

            return new SoinEffectue(giverBusiness.getUtilisateurParMail(mail),dateSoin,soinMedical,idSoineffectue,remarque);
        }
        catch(SQLException e)
        {
            new BDConnexionErreur(e.getMessage());
            return null;
        }
    }
/*

 */
}
