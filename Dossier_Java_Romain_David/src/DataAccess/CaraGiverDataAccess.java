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



    public void delete(String id) {

    }


    public void update(CareGiver careGiver) {

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
}
