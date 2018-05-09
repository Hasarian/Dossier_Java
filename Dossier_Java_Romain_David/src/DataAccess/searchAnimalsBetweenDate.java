package DataAccess;

public class searchAnimalsBetweenDate {
    select*
    from ficheAnimal, vaccination, vaccin, fichesoin
    where ficheAnimal.id = 1 and fichesoin.id = ficheanimal.id and vaccination.id = ficheanimal.id and vaccination.numVaccin = vaccin.numVaccin;
}
