package erreurs;

public class ErreurrNull extends Exception {
    private String errorMessage;

    public ErreurrNull(String message){
        errorMessage = "Un des attribut obligatoire n'a pas été rempli: "+message+" est à null";
    }
    public ErreurrNull(){
        this("Un des attribut obligatoire n'a pas été rempli");
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
