package erreurs;

public class ErrorInsertAnimal extends Exception {
    String message;

    public ErrorInsertAnimal(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
