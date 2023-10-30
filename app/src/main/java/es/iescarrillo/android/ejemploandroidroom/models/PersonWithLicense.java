package es.iescarrillo.android.ejemploandroidroom.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

public class PersonWithLicense {
    @Embedded
    private Person person;

    @Relation(parentColumn = "id", entityColumn = "person_id")
    private License license;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "PersonWithLicense{" +
                "person=" + person.toString() +
                ", license=" + license.toString() +
                '}';
    }
}
