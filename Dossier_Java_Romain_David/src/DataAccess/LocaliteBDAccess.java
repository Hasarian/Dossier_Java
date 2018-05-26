package DataAccess;

import DataAccess.DAO.DAOLocalite;
import Model.Localite;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocaliteBDAccess implements DAOLocalite {
    private Connection connexion;

    public LocaliteBDAccess() throws BDConnexionErreur {
        connexion = SingletonDB.getInstance();
    }

    @Override
    public ArrayList<Localite> readToutesLesLocalites() throws BDConnexionErreur, ErreurrNull {
        ArrayList<Localite> localites = new ArrayList<Localite>();

        try {
            String sql = "select * from localite";
            PreparedStatement statement = connexion.prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            while (data.next()) {
                localites.add(resultatVersLocalite(data));
            }
        }
        catch(SQLException e){
            //JOptionPane.showMessageDialog(null, e.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
             throw new BDConnexionErreur(e.getMessage());
        }
        return localites;
    }

    private Localite resultatVersLocalite(ResultSet data)throws ErreurrNull {
        Localite localite = null;
        try{
            localite = new Localite(data.getInt("idLocalite"), data.getInt("CodePostal"), data.getString("libelle"));
        }
        catch(SQLException e){
            new BDConnexionErreur(e.getMessage());
        }
        return localite;
    }

}
