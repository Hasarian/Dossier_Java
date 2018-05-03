package DataAccess;

public class ErrorReadCareGiver extends Exception {
    String message;
    ErrorReadCareGiver(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
