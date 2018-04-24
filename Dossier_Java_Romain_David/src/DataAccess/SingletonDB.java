package DataAccess;

public class SingletonDB {
    private static PrincipalDBAccess connectionSingleton;

    private SingletonDB(){
    }
    public static PrincipalDBAccess getInstance(){
        if(connectionSingleton == null){
            return connectionSingleton = new PrincipalDBAccess();
        }
        return  connectionSingleton;
    }
    //faire le close
}
