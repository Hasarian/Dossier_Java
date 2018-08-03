package accesDonnees;


import accesDonnees.dao.DAOSoignant;
import erreurs.Erreur;
import erreurs.erreursExternes.DonneeInexistante;
import modèle.Soignant;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreursExternes.ErreurInsertionSoignant;
import modèle.Localite;
import erreurs.erreurFormat.ErreurrNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoignantDonnees implements DAOSoignant {
    private Connection singletonDBAcces;
    public SoignantDonnees() throws DonneePermanenteErreur {
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



    public void delete(String id) throws DonneePermanenteErreur {
        String sql="delete from soignant where mail=?";
        try{
            PreparedStatement statement=singletonDBAcces.prepareStatement(sql);
            statement.setString(1,id);
            statement.execute();
        }
        catch (SQLException sqlException)
        {
            throw new DonneePermanenteErreur(sqlException.getMessage());
        }
    }


    public void update(String ancianMail,Soignant soignant) throws DonneePermanenteErreur {
        String sql="update soignant" +
                " set prenom=?,nom=?,rue=?,numMaison=?,numTel=?,remarque=?,estBenevole=?,localite=?, mail=?, dateEmbauche=?" +
                "where mail=?";
                    /*11*/
        /*               1       2    3         4         5          6           7             8       9             10*/
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
            statement.setDate(10,new java.sql.Date(soignant.getDateEmbauche().getTimeInMillis()));
            statement.setString(11, ancianMail);
            statement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            throw new DonneePermanenteErreur(sqlException.getMessage());
        }
    }

    public Soignant read(String id) throws Erreur
    {
        String sql = "select * from soignant, localite where soignant.mail = ? and soignant.localite = localite.idLocalite";

        try {
            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet data = statement.executeQuery();
            if (!data.next()) throw new DonneeInexistante(id);
            //recherche arraylist localoite sur l'id
            return traductionSQL(data);
        }
        catch(SQLException sqlException) {
            //System.out.println(sqlException.getMessage());
            throw new DonneePermanenteErreur(sqlException.getMessage());
        }
    }
    public ArrayList<String> readallMails() throws DonneePermanenteErreur
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
            throw new DonneePermanenteErreur(sqlException.getMessage());
        }
    }
    public ArrayList<Soignant> readTousLesSoignants() throws DonneePermanenteErreur,ErreurrNull
    {
        String sql="select * from soignant, localite where soignant.localite=localite.idlocalite";
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
            throw new DonneePermanenteErreur(sqlE.getMessage());
        }
    }
    public Soignant traductionSQL(ResultSet data) throws SQLException,ErreurrNull
    {
        Localite localite = new Localite(data.getInt("localite.idLocalite"),
                data.getInt("localite.CodePostal"),
                data.getString("localite.libelle"));
        //if(!business.arrayListLocalite.containt(localite))business.arrayListLocalite.add(localite);
        //ResultSetMetaData meta = data.getMetaData();
        GregorianCalendar dateEmbauche = new GregorianCalendar();
        dateEmbauche.setTime(data.getDate("dateEmbauche"));
        Integer numTel = data.getInt("numTel");
        if(data.wasNull())numTel=null;
        String remarque = data.getString("remarque");
        if(data.wasNull()) remarque=null;
        return new Soignant(data.getString("mail"),
                data.getString("prenom"),
                data.getString("nom"),
                data.getString("rue"),
                data.getInt("numMAison"), numTel, remarque,
                data.getBoolean("EstBenevole"),
                dateEmbauche, localite );

        /*String mail, String prenom, String nomDeFamille, String rue, Integer numMaison, Integer numTel,
                    String remarque, Boolean estBenevole, GregorianCalendar dateEmbauche, Localite localite*/
    }
}
