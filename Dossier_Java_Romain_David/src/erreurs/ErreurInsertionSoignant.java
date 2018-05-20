package erreurs;

public class ErreurInsertionSoignant extends Exception {
    private String errorMessage;
    public ErreurInsertionSoignant(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
