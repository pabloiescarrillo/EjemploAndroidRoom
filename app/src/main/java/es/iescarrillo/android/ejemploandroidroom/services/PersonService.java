package es.iescarrillo.android.ejemploandroidroom.services;

import android.app.Application;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.daos.PersonDao;
import es.iescarrillo.android.ejemploandroidroom.database.DatabaseHelper;
import es.iescarrillo.android.ejemploandroidroom.models.Person;

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
}
