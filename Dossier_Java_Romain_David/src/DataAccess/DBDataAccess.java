package DataAccess;


import DAO.DAOCareGiver;
import Model.CareGiver;
import Model.Localite;

import javax.swing.*;
import java.sql.*;
import java.util.GregorianCalendar;

public class DBDataAccess implements DAOCareGiver {
    private Connection singletonDBAcces;
    public DBDataAccess(){
        singletonDBAcces = SingletonDB.getInstance();
    }


    public void create(CareGiver careGiver) throws ErreurInsertCareGiver {
        String sql = "insert into careGiver(mail, prenomn nom, rue, numMaison, numtel, remarque, estBenevole, dateEmbauche, localite) values(?,?,?,?,?,?,?,?,?,?)";
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
        }catch (SQLException e){
            throw new ErreurInsertCareGiver(e.getMessage());
        }
    }



    public void delete(String id) {

    }


    public void update(CareGiver careGiver) {

    }

    public CareGiver read(String id) {
        CareGiver careGiver = null;
        String sql = "select soignant where mail = ?";
        try {
            PreparedStatement statement = singletonDBAcces.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet data = statement.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            GregorianCalendar hireDate = new GregorianCalendar();
            hireDate.setTime(data.getDate("dateEmbauche"));
            return new CareGiver(data.getString("mail"),data.getString("prenom"),data.getString("nom"),
                    data.getString("rue"), data.getInt("numMAison"),data.getInt("numTel"), data.getString("remarque"),
                    data.getBoolean("estBenevole"),hireDate , (Localite) data.getObject("localite"));
        }
        catch (SQLException e){

        }
        return careGiver;
    }
}
