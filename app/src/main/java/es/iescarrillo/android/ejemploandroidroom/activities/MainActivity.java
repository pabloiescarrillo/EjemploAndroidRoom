package es.iescarrillo.android.ejemploandroidroom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import es.iescarrillo.android.ejemploandroidroom.R;
import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.Person;
import es.iescarrillo.android.ejemploandroidroom.services.CarServices;
import es.iescarrillo.android.ejemploandroidroom.services.PersonService;

public class MainActivity extends AppCompatActivity {

    // Crear las variables de los servicios
    private PersonService personService;
    private CarServices carServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personService = new PersonService(getApplication());
        carServices = new CarServices(getApplication());

        // En Android tenemos que ejecutar las acciones sobre base de datos en hilos
        Thread thread = new Thread(() -> {
            populate();

            for(Person p: personService.getAll()){
                Log.i("Persona", p.toString());
            }

            for(Car c: carServices.getAll())
                Log.i("Car", c.toString());
        });

        thread.start();
        try {
            thread.join();//Esperar a que termine el hilo
        } catch (Exception e){
            Log.e("error hilo", e.getMessage());
        }




    }

    public void populate(){
        // Insertar persona
        Person p = new Person();
        p.setName("Pablo");
        p.setSurname("Segundo");
        p.setAge(30);

        personService.insertPerson(p);

        // Insertar coche
        Car c = new Car();
        c.setModel("Seat Panda");
        c.setPlate("3069BNF");

        carServices.insertCar(c);
    }
}