package es.iescarrillo.android.ejemploandroidroom.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PersonWithCar {

    @Embedded
    private Person person;

    @Relation(entityColumn = "person_id", parentColumn = "id")
    private List<Car> cars;

    public PersonWithCar() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "PersonWithCar{" +
                "person=" + person.toString() +
                ", cars=" + cars.toString() +
                '}';
    }
}
