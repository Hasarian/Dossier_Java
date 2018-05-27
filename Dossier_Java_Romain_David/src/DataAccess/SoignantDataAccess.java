package DataAccess;


import DataAccess.DAO.DAOSoignant;
import Model.Soignant;
import com.mysql.fabric.xmlrpc.base.Array;
import erreurs.BDConnexionErreur;
import erreurs.ErreurInsertionSoignant;
import Model.Localite;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoignantDataAccess implements DAOSoignant {
    private Connection singletonDBAcces;
    public SoignantDataAccess() throws BDConnexionErreur {
        singletonDBAcces = SingletonDB.getInstance();
    }


    public void create(Soignant soignant) throws ErreurInsertionSoignant {
        String sql = "insert into soignant(mail, prenom, nom, rue, numMaison, numtel, remarque, estBenevole, dateEmbauche, localite) values(?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            if(soignant.getNumTel() == null)statement.setNull(6, Types.INTEGER);
            else statement.setInt(6, soignant.getNumTel());
            if(soignant.getRemarque() == null)statement.setNull(7,Types.VARCHAR);
            else statement.setString(7, soignant.getRemarque());

            statement.setString(1, soignant.getMail());
            statement.setString(2, soignant.getPrenom());
            statement.setString(3, soignant.getNomDeFamille());
            statement.setString(4, soignant.getRue());
            statement.setInt(5, soignant.getNumMaison());
            statement.setBoolean(8, soignant.getEstBenevole());
            statement.setDate(9, new java.sql.Date(soignant.getDateEmbauche().getTimeInMillis()));
            statement.setInt(10, soignant.getLocalite().getIdLocalite());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new ErreurInsertionSoignant(e.getMessage());
        }
    }



    public void delete(String id) throws BDConnexionErreur {
        String sql="delete from soignant where mail=?";
        try{
            PreparedStatement statement=singletonDBAcces.prepareStatement(sql);
            statement.setString(1,id);
            statement.execute();
        }
        catch (SQLException sqlException)
        {
            throw new BDConnexionErreur(sqlException.getMessage());
        }
    }


    public void update(Soignant soignant) throws BDConnexionErreur {
        String sql="update soignant" +
                " set prenom=?,nom=?,rue=?,numMaison=?,numTel=?,remarque=?,estBenevole=?,localite=?, mail=? " +
                "where mail=?";
                    /*9*/
        /*               1       2    3         4         5          6           7             8*/
        /*mail varchar (50),
                nom varchar(50) not null,
                prenom varchar(50) not null,
                rue varchar(100) not null,
                numMaison integer(4) not null,
                numTel integer(9),
                remarque varchar(150),
                AutreUtilisateurEstBenevole boolean not null,
                dateEmbauche date not null,
                localite integer(10) not null,*/
        try {
            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            if(soignant.getNumTel() == null)statement.setNull(5, Types.INTEGER);
            else statement.setInt(5, soignant.getNumTel());
            if(soignant.getRemarque() == null)statement.setNull(6,Types.VARCHAR);
            else statement.setString(6, soignant.getRemarque());
            statement.setString(1, soignant.getPrenom());
            statement.setString(2, soignant.getNomDeFamille());
            statement.setString(3, soignant.getRue());
            statement.setInt(4, soignant.getNumMaison());
            statement.setBoolean(7, soignant.getEstBenevole());
            statement.setInt(8, soignant.getLocalite().getIdLocalite());
            statement.setString(9,soignant.getMail());
            statement.setString(10, soignant.getMail());
            statement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            throw new BDConnexionErreur(sqlException.getMessage());
        }
    }

    public Soignant read(String id) throws SoignantInexistant, ErreurrNull, BDConnexionErreur
    {
        String sql = "select * from soignant, localite where soignant.mail = ? and soignant.localite = localite.idLocalite";

        try {
            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet data = statement.executeQuery();
            if (!data.next()) throw new SoignantInexistant();
            //recherche arraylist localoite sur l'id
            return traductionSQL(data);
        }
        catch(SQLException sqlException) {
            //System.out.println(sqlException.getMessage());
            throw new BDConnexionErreur(sqlException.getMessage());
        }
    }
    public ArrayList<String> readallMails() throws BDConnexionErreur
    {
        String sql="select mail from soignant";
        try
        {
            PreparedStatement statement=singletonDBAcces.prepareStatement(sql);
            ResultSet data=statement.executeQuery();
            ArrayList<String> mails=new ArrayList<String>();
            while(data.next())
            {
                mails.add(data.getString("mail"));
            }
            return mails;
        }

        catch (SQLException sqlException)
        {
            throw new BDConnexionErreur(sqlException.getMessage());
        }
    }
    public ArrayList<Soignant> readTousLesSoignants() throws BDConnexionErreur,ErreurrNull
    {
        String sql="select * from soignant";
        try {
            PreparedStatement requete = singletonDBAcces.prepareStatement(sql);
            ResultSet data=requete.executeQuery();
            ArrayList<Soignant> array=new ArrayList<Soignant>();
            while (data.next())
            {
                array.add(traductionSQL(data));
            }
            return array;
        }
        catch (SQLException sqlE)
        {
            throw new BDConnexionErreur(sqlE.getMessage());
        }
    }

    public Soignant traductionSQL(ResultSet data) throws SQLException,ErreurrNull
    {
        Localite localite = new Localite((data.wasNull())?null : data.getInt("idLocalite"),
                (data.wasNull())?null : data.getInt("localite.CodePostal"),
                (data.wasNull())?null : data.getString("localite.libelle"));
        //if(!Business.arrayListLocalite.containt(localite))Business.arrayListLocalite.add(localite);
        //ResultSetMetaData meta = data.getMetaData();
        GregorianCalendar dateEmbauche = new GregorianCalendar();
        dateEmbauche.setTime((data.wasNull())?null : data.getDate("dateEmbauche"));
        Integer numTel = (data.wasNull() ? null : data.getInt("numTel"));
        String remarque = (data.wasNull() ? null : data.getString("remarque"));

        return new Soignant(data.getString("mail"),
                (data.wasNull())?null : data.getString("prenom"),
                (data.wasNull())?null : data.getString("nom"),
                (data.wasNull())?null : data.getString("rue"),
                (data.wasNull())?null : data.getInt("numMAison"), numTel, remarque,
                (data.wasNull())?null : data.getBoolean("EstBenevole"),
                dateEmbauche, localite );
    }
}
