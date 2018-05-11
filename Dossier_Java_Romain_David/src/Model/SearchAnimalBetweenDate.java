package Model;



public class SearchAnimalBetweenDate {
    private Animal animal;
    private Vaccin vaccin;
    private Vaccination vaccination;
    private Race race;
    private Espece espece;

    public SearchAnimalBetweenDate(Animal animal, Vaccin vaccin, Vaccination vaccination, Race race, Espece espece){
        this.animal = animal;
        this.vaccin = vaccin;
        this.vaccination = vaccination;
        this.race = race;
        this.espece = espece;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Espece getEspece() {
        return espece;
    }

    public Race getRace() {
        return race;
    }

    public Vaccin getVaccin() {
        return vaccin;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }
}
