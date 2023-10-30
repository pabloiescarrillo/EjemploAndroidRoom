package es.iescarrillo.android.ejemploandroidroom.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
import java.util.Map;

import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.Person;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithBooks;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithCar;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithLicense;

@Dao
public interface PersonDao {

    @Insert
    long insertPerson(Person person);

    @Update
    void updatePerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE age > :age")
    List<Person> getOlderThan(Integer age);

    @Query("SELECT * FROM person WHERE age BETWEEN :start AND :end")
    List<Person> getAgeBetween(Integer start, Integer end);

    @Transaction
    @Query("SELECT * FROM person")
    List<PersonWithLicense> getPersonWithLicense();

    @Transaction
    @Query("SELECT * FROM person")
    List<PersonWithCar> getPersonWithCar();

    @Query("SELECT * FROM person p JOIN car c ON p.id = c.person_id")
    Map<Person, List<Car>> getPersonWithCarMap();


    @Transaction
    @Query("SELECT * FROM person")
    List<PersonWithBooks> getPersonWithBooks();

}
