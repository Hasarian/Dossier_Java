package Model;

import erreurs.ErrorNull;

import java.util.GregorianCalendar;

public class Vaccination {
    private Animal animal;
    private Vaccin numVaccin;
    private GregorianCalendar date;
    private Integer idVaccination;

    public Vaccination(Animal animal, Vaccin numVaccin, GregorianCalendar date, Integer idVaccination) throws ErrorNull{
        setAnimal(animal);
        setDate(date);
        setIdVaccination(idVaccination);
        setNumVaccin(numVaccin);
    }

    public void setAnimal(Animal animal) throws ErrorNull {
        if(animal != null) this.animal = animal;
        else throw new ErrorNull("animal");
    }

    public void setDate(GregorianCalendar date) throws ErrorNull{
        if(date != null) this.date = date;
        else throw new ErrorNull("date");
    }

    public void setIdVaccination(Integer idVaccination) throws ErrorNull {
        if(idVaccination != null) this.idVaccination = idVaccination;
        else throw new ErrorNull("idVaccination");
    }

    public void setNumVaccin(Vaccin numVaccin) throws ErrorNull {
        if(numVaccin != null) this.numVaccin = numVaccin;
        else throw new ErrorNull("numVaccin");
    }
    public Animal getAnimal(){return animal;}

    public Vaccin getNumVaccin() {
        return numVaccin;
    }

    public Integer getIdVaccination() {
        return idVaccination;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
