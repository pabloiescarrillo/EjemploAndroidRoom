package es.iescarrillo.android.ejemploandroidroom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.iescarrillo.android.ejemploandroidroom.R;
import es.iescarrillo.android.ejemploandroidroom.models.Book;
import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.License;
import es.iescarrillo.android.ejemploandroidroom.models.Person;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithBooks;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithCar;
import es.iescarrillo.android.ejemploandroidroom.models.PersonWithLicense;
import es.iescarrillo.android.ejemploandroidroom.services.BookService;
import es.iescarrillo.android.ejemploandroidroom.services.CarService;
import es.iescarrillo.android.ejemploandroidroom.services.LicenseService;
import es.iescarrillo.android.ejemploandroidroom.services.PersonService;

public class MainActivity extends AppCompatActivity {

    // Crear las variables de los servicios
    private PersonService personService;
    private CarService carServices;
    private LicenseService licenseService;

    private BookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar servicios
        personService = new PersonService(getApplication());
        carServices = new CarService(getApplication());
        licenseService = new LicenseService(getApplication());
        bookService = new BookService(getApplication());

        // En Android tenemos que ejecutar las acciones sobre base de datos en hilos
        Thread thread = new Thread(() -> {
            populate();

           /* for(Person p: personService.getAll()){
                Log.i("Persona", p.toString());
            }

            for(Car c: carServices.getAll())
                Log.i("Car", c.toString());*/

           for(PersonWithLicense p: personService.getPersonWithLicense()){
                Log.i("Persona con licencia", p.toString());
            }

            /* Map<Person, List<Car>> map = personService.getPersonWithCarMap();
            for(Person p: map.keySet()){
                Log.i("Persona con coches", p.toString() + " Coche " + map.get(p).toString());
            }*/


            for(PersonWithBooks p: personService.getPersonWithBooks()){
                Log.i("Persona con libros", p.toString());
            }

            Map<Person, List<Book>> personListMap = personService.getPersonWithBooksMap();
            for(Person p:personListMap.keySet()){
                Log.i("Persona con libros MAP", p.toString() + " Su lista de libros es: " +
                        personListMap.get(p).toString());
            }

        });

        thread.start();
        try {
            thread.join();//Esperar a que termine el hilo
        } catch (Exception e){
            Log.e("error hilo", e.getMessage());
        }




    }

    public void populate() {
        // Insertar persona
        Person p = new Person();
        p.setName("Pablo");
        p.setSurname("Segundo");
        p.setAge(30);

        long personId = personService.insertPerson(p);

        // Insertar coche
        Car c = new Car();
        c.setModel("Seat Panda");
        c.setPlate("3069BNF");
        c.setPersonId(personId);

        carServices.insertCar(c);

        Car c1 = new Car();
        c1.setModel("Renault Clio");
        c1.setPlate("9489CWX");
        c1.setPersonId(personId);

        carServices.insertCar(c1);

        // Insertar licencia
        License l = new License();
        l.setName("Carnet de conducir");
        l.setPersonId(personId);
        l.setStartDate(LocalDate.of(2020, 5, 10));
        l.setEndDate(LocalDate.of(2030, 5, 10));
        l.setStartTime(LocalTime.of(12,35));

        licenseService.insertLicense(l);

        // Insertar libros
        Book b = new Book();
        b.setTitle("El Quijote");
        b.setPersonId(personId);

        bookService.insertBook(b);

        Book b1 = new Book();
        b1.setTitle("La rueda del tiempo");
        b1.setPersonId(personId);

        bookService.insertBook(b1);
    }
}