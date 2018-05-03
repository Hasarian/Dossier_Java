package erreurs;

public class AttributNull extends Exception {
    String message;

    public AttributNull(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
