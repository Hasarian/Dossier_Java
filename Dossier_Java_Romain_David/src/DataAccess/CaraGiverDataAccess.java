package DataAccess;


import DataAccess.DAO.DAOCareGiver;
import Model.CareGiver;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.Localite;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CaraGiverDataAccess implements DAOCareGiver {
    private Connection singletonDBAcces;
    public CaraGiverDataAccess() throws BDConnexionError{
        singletonDBAcces = SingletonDB.getInstance();
    }


    public void create(CareGiver careGiver) throws ErreurInsertCareGiver {
        String sql = "insert into soignant(mail, prenom, nom, rue, numMaison, numtel, remarque, estBenevole, dateEmbauche, localite) values(?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            if(careGiver.getNumTel() == null)statement.setNull(6, Types.INTEGER);
            else statement.setInt(6,careGiver.getNumTel());
            if(careGiver.getRemarque() == null)statement.setNull(7,Types.VARCHAR);
            else statement.setString(7,careGiver.getRemarque());

            statement.setString(1, careGiver.getMail());
            statement.setString(2, careGiver.getFirstName());
            statement.setString(3, careGiver.getName());
            statement.setString(4, careGiver.getStreet());
            statement.setInt(5, careGiver.getNumMaison());
            statement.setBoolean(8, careGiver.getEstBenevole());
            statement.setDate(9, new java.sql.Date(careGiver.getDateEmbauche().getTimeInMillis()));
            statement.setInt(10, careGiver.getLocalite().getIdLocalite());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new ErreurInsertCareGiver(e.getMessage());
        }
    }



    public void delete(String id) throws BDConnexionError{
        String sql="delete from soignant where mail=?";
        try{
            PreparedStatement statement=singletonDBAcces.prepareStatement(sql);
            statement.setString(1,id);
            statement.executeQuery();
        }
        catch (SQLException sqlException)
        {
            JOptionPane.showMessageDialog(null,sqlException.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
            throw new BDConnexionError();
        }
    }


    public void update(CareGiver careGiver) throws BDConnexionError{
        String sql="update soignant" +
                " set prenom=?,nom=?,rue=?,numMaison=?,numTel=?,remarque=?,estBenevole=?,localite=? " +
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
                estBenevole boolean not null,
                dateEmbauche date not null,
                localite integer(10) not null,*/
        try {
            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            if(careGiver.getNumTel() == null)statement.setNull(5, Types.INTEGER);
            else statement.setInt(5,careGiver.getNumTel());
            if(careGiver.getRemarque() == null)statement.setNull(6,Types.VARCHAR);
            else statement.setString(6,careGiver.getRemarque());
            statement.setString(1, careGiver.getFirstName());
            statement.setString(2, careGiver.getName());
            statement.setString(3, careGiver.getStreet());
            statement.setInt(4, careGiver.getNumMaison());
            statement.setBoolean(7, careGiver.getEstBenevole());
            statement.setInt(8, careGiver.getLocalite().getIdLocalite());
            statement.executeQuery();
        }
        catch (SQLException sqlException)
        {
            JOptionPane.showMessageDialog(null,sqlException.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
            throw new BDConnexionError();
        }
    }

    public CareGiver read(String id) throws InexistantCareGiver, ErrorNull, BDConnexionError
    {
        String sql = "select * from soignant, localite where soignant.mail = ? and soignant.localite = localite.idLocalite";

        try {
            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet data = statement.executeQuery();
            if (!data.next()) throw new InexistantCareGiver();
            //recherche arraylist localoite sur l'id
            Localite localite = new Localite(data.getInt("idLocalite"),data.getInt("codePostal"),data.getString("libelle"));
            //if(!Business.arrayListLocalite.containt(localite))Business.arrayListLocalite.add(localite);
            ResultSetMetaData meta = data.getMetaData();
            GregorianCalendar hireDate = new GregorianCalendar();
            hireDate.setTime(data.getDate("dateEmbauche"));
            Integer numTel = (data.wasNull() ? null : data.getInt("numTel"));
            String remarque = (data.wasNull() ? null : data.getString("remarque"));

            return new CareGiver(data.getString("mail"), data.getString("prenom"), data.getString("nom"),
                    data.getString("rue"), data.getInt("numMAison"), numTel, remarque,
                    data.getBoolean("estBenevole"), hireDate, localite );
        }
        catch(SQLException sqlException) {
            JOptionPane.showMessageDialog(null,sqlException.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
            throw new BDConnexionError();
        }
    }
    public ArrayList<String> readallMails() throws BDConnexionError
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
            throw new BDConnexionError();
        }
    }
}
