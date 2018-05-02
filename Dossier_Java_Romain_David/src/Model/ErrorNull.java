package Model;

public class ErrorNull extends Exception {
    private String errorMessage;

    public ErrorNull(String message){
        errorMessage = "Un des attribut obligatoire n'a pas été rempli: "+message+" est à null";
    }
    public ErrorNull(){
        this("Un des attribut obligatoire n'a pas été rempli");
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
