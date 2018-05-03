package DataAccess;

import DAO.DAOAnimal;
import Model.Animal;
import Model.CareGiver;
import Model.Localite;
import sun.java2d.loops.GeneralRenderer;

import java.sql.*;
import java.util.GregorianCalendar;

public class AnimalDBAccess implements DAOAnimal {
    private Connection connection;

    public AnimalDBAccess(){
        connection = SingletonDB.getInstance();
    }

    @Override
    public Animal read(int id) {
        String sql = "select * from ficheSoin, ficheAnimal where ficheSoin = ? and ficheSoin.id = ficheAnimal.id";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            GregorianCalendar dateArrive =  new GregorianCalendar();
            GregorianCalendar dateDesces = new GregorianCalendar();

            dateArrive.setTime(data.getDate("dateArrive"));
            dateDesces.setTime(data.getDate("dateDesces"));


            return null;
        }
        catch(SQLException e){

        }
        return null;
    }

    @Override
    public void create(Animal animal) {

    }

    @Override
    public void update(Animal animal) {

    }

    @Override
    public void delete(int id) {

    }
}
    /*private Integer id;
    private String remarqueAnimal;
    private Integer numCellule;
    private String nomAnimal;
    private Race race;
    private GregorianCalendar dateArrive;
    private GregorianCalendar dateDesces;
    private Boolean estDangereux;
    private String etatAnimal;
    private String remaqueSoin;
    private Integer etatFicheSoin;*/
