package es.iescarrillo.android.ejemploandroidroom.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.models.Person;

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
}
