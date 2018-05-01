package DataAccess;

public class ErreurInsertCareGiver extends Exception {
    private String errorMessage;
    public ErreurInsertCareGiver(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
