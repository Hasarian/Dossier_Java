package DataAccess;

import DataAccess.DAO.DAOLocalite;
import Model.Localite;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocaliteBDAccess implements DAOLocalite {
    private Connection connection;

    public LocaliteBDAccess() throws BDConnexionError {
        connection = SingletonDB.getInstance();
    }

    @Override
    public ArrayList<Localite> readAllLocalite() throws BDConnexionError, ErrorNull{
        ArrayList<Localite> localites = new ArrayList<Localite>();

        try {
            String sql = "select * from localite";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            while (data.next()) {
                localites.add(dataToLocalite(data));
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            new BDConnexionError();
        }
        return localites;
    }

    private Localite dataToLocalite(ResultSet data)throws BDConnexionError, ErrorNull{
        Localite localite = null;
        try{
            localite = new Localite(data.getInt("idLocalite"), data.getInt("codePostal"), data.getString("libelle"));
        }
        catch(SQLException e){
            new BDConnexionError();
        }
        return localite;
    }

    @Override
    public Localite read(String libelle) {
        return null;
    }

    @Override
    public void create(Localite localite) {

    }

    @Override
    public void update(Localite localite) {

    }

    @Override
    public void delete(String libelle) {

    }
}
