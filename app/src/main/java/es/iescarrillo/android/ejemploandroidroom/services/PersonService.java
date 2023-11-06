package es.iescarrillo.android.ejemploandroidroom.services;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import es.iescarrillo.android.ejemploandroidroom.daos.PersonDao;
import es.iescarrillo.android.ejemploandroidroom.database.DatabaseHelper;
import es.iescarrillo.android.ejemploandroidroom.models.Book;
import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.Person;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithBooks;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithCar;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithLicense;

public class PersonService implements PersonDao {

    private PersonDao personDao;

    public PersonService(Application application) {
        DatabaseHelper db = DatabaseHelper.getInstance(application);
        personDao = db.personDao();
    }

    @Override
    public long insertPerson(Person person) {
        return personDao.insertPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        personDao.deletePerson(person);
    }

    @Override
    public List<Person> getAll() {
        return personDao.getAll();
    }

    @Override
    public List<Person> getOlderThan(Integer age) {
        return personDao.getOlderThan(age);
    }

    @Override
    public List<Person> getAgeBetween(Integer start, Integer end) {
        return personDao.getAgeBetween(start, end);
    }

    @Override
    public List<PersonWithLicense> getPersonWithLicense() {
        return personDao.getPersonWithLicense();
    }

    @Override
    public List<PersonWithCar> getPersonWithCar() {
        return personDao.getPersonWithCar();
    }

    @Override
    public Map<Person, List<Car>> getPersonWithCarMap() {
        return personDao.getPersonWithCarMap();
    }

    @Override
    public List<PersonWithBooks> getPersonWithBooks() {
        return personDao.getPersonWithBooks();
    }

    @Override
    public Map<Person, List<Book>> getPersonWithBooksMap() {
        return personDao.getPersonWithBooksMap();
    }

    @Override
    public Person getPersonByUsername(String username) {
        return personDao.getPersonByUsername(username);
    }


}
