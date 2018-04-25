package DataAccess;

public class SingletonDB {
    private static DBConnection connectionSingleton;

    private SingletonDB(){
    }
    public static DBConnection getInstance(){
        if(connectionSingleton == null){
            return connectionSingleton = new DBConnection();
        }
        return  connectionSingleton;
    }
    //faire le close
}
