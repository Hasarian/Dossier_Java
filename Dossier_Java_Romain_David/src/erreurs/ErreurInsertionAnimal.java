package erreurs;

public class ErreurInsertionAnimal extends Exception {
    String message;

    public ErreurInsertionAnimal(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
