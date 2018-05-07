package erreurs;

public class NumberExpection extends Exception {
    private String message;

    public NumberExpection(String message){
        this.message = "Veuillez verifier le format du champ "+message+" qui n'attend que des chiffres";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
