package erreurs;

public class NombreExpection extends Exception {
    private String message;

    public NombreExpection(String message){
        this.message = "Veuillez verifier le format du champ "+message+" qui n'attend que des chiffres";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
