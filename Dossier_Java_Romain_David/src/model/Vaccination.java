package model;

import erreurs.ErreurrNull;

import java.util.GregorianCalendar;

public class Vaccination {
    private Animal animal;
    private Vaccin numVaccin;
    private GregorianCalendar date;
    private Integer idVaccination;

    public Vaccination(Animal animal, Vaccin numVaccin, GregorianCalendar date, Integer idVaccination) throws ErreurrNull {
        setAnimal(animal);
        setDate(date);
        setIdVaccination(idVaccination);
        setNumVaccin(numVaccin);
    }

    public void setAnimal(Animal animal) throws ErreurrNull {
        if(animal != null) this.animal = animal;
        else throw new ErreurrNull("animal");
    }

    public void setDate(GregorianCalendar date) throws ErreurrNull {
        if(date != null) this.date = date;
        else throw new ErreurrNull("date");
    }

    public void setIdVaccination(Integer idVaccination) throws ErreurrNull {
        if(idVaccination != null) this.idVaccination = idVaccination;
        else throw new ErreurrNull("idVaccination");
    }

    public void setNumVaccin(Vaccin numVaccin) throws ErreurrNull {
        if(numVaccin != null) this.numVaccin = numVaccin;
        else throw new ErreurrNull("numVaccin");
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
