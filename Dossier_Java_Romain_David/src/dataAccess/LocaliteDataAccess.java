package dataAccess;

import dataAccess.dao.DAOLocalite;
import erreurs.Erreur;
import model.Localite;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreurFormat.ErreurrNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocaliteDataAccess implements DAOLocalite {
    private Connection connexion;

    public LocaliteDataAccess() throws DonneePermanenteErreur {
        connexion = SingletonDB.getInstance();
    }

    @Override
    public ArrayList<Localite> readToutesLesLocalites() throws Erreur {
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
             throw new DonneePermanenteErreur(e.getMessage());
        }
        return localites;
    }

    private Localite resultatVersLocalite(ResultSet data)throws Erreur {
        Localite localite = null;
        try{
            localite = new Localite(data.getInt("idLocalite"), data.getInt("CodePostal"), data.getString("libelle"));
        }
        catch(SQLException e){
            throw new DonneePermanenteErreur(e.getMessage());
        }
        return localite;
    }

}
