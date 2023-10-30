# EjemploAndroidRoom

Proyecto ejemplo de Android Room y SQLite

## Pasos a seguir

**1. Importar dependencias en el archivo de Gradle y sincronizar**
```
// Dependencias de Room
implementation("androidx.room:room-runtime:2.5.0")
annotationProcessor ("androidx.room:room-compiler:2.5.0")
```
**2. Crear DatabaseHelper**
```
@Database(entities = {Entidad1.class, Entidad2.class, Entidad3.class ... }, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    // Insertar los DAOs
    public abstract Entidad1Dao entidad1Dao();
    public abstract Entidad2Dao entidad2Dao();
    public abstract Entidad3Dao entidad3Dao();
    ....

    // Instancia estática de la clase, para oder usarla en toda la aplicación
    private static DatabaseHelper instance;

    // Método de Android Room para crear la base de datos
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseHelper.class, "NOMBRE DE LA BASE DE DATOS")
                    .fallbackToDestructiveMigration() // Si se cambia la versión elimina y reconstruye
                    .build();
        }
        return instance;
    }

    // Resto de métodos de la clase padre
    ....
    ....
    ....
}
```
**3. Crear Entidades**
```
@Entity(tableName = "NOMBRE DE LA TABLA EN BBDD")
public class NombreEntidad {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "nombre columna en la bbdd")
    private String atributo1;

    @ColumnInfo(name = "nombre columna en la bbdd")
    private String atributo2;  

    // Constructor

    // Getters & Setters
}
```
**4. Crear DAOs**
```
@Dao
public interface Entidad1Dao {

    @Insert
    long insertEntidad1(Entidad1 entidad1);

    @Update
    void updateEntidad1(Entidad1 entidad1);

    @Delete
    void deleteEntidad1(Entidad1 entidad1);

    @Query("SELECT * FROM entidad1")
    List<Entidad1> getAll();

    //Resto de cabeceras de métodos, p.e. otras @Query
}
```
**5. Añadir Entidades y DAOs al DatabaseHelper**

**6. Crear Servicios**
```
public class Entidad1Services implements Entidad1Dao {

    // DAO relacionado (solo debe tener uno)
    private Entidad1Dao entidad1Dao;

    // Servicios relacionados (puede tener de 0 a muchos servicios relacionados)
    private Entidad2Service entidad2Service;
    private Entidad3Service entidad3Service;
    
    public CarServices(Application application){
        DatabaseHelper db = DatabaseHelper.getInstance(application);
        entidad1Dao = db.entidad1Dao();
        entidad2Service = new Entidad2Service();
        entidad3Service = new Entidad3Service();
    }
    @Override
    public long insertEntidad1(Entidad1 entidad1) {
        return carDao.insertEntidad1(entidad1);
    }

    @Override
    public void updateEntidad1(Entidad1 entidad1) {
        carDao.updateEntidad1(entidad1);
    }

    @Override
    public void deleteEntidad1(Entidad1 entidad1) {
        carDao.deleteEntidad1(entidad1);
    }

    @Override
    public List<Entidad1> getAll() {
        return entidad1Dao.getAll();
    }
}
```
**7. Usar los Servicios en las Activityes**
```
public class MainActivity extends AppCompatActivity {

    // Crear las variables de los servicios
    private Entidad1Services entidad1Services;
    private Entidad2Services entidad2Services;
    private Entidad3Services entidad3Services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos los servicios
        entidad1Services = new Entidad1Services(getApplication());
        entidad2Services = new Entidad2Services(getApplication());
        entidad3Services = new Entidad3Services(getApplication());

        // En Android tenemos que ejecutar las acciones sobre base de datos en hilos
        Thread thread = new Thread(() -> {
            populate();

            for(Entidad1 e1: entidad1Services.getAll()){
                Log.i("Entidad1", e1.toString());
            }

            for(Entidad2 e2: entidad2Services.getAll()){
                Log.i("Entidad2", e1.toString());
            }

        });

        thread.start();
        try {
            thread.join();//Esperar a que termine el hilo
        } catch (Exception e){
            Log.e("error hilo", e.getMessage());
        }

    }

    public void populate(){
        // Insertar Entidad1
        Entidad1 e1 = new Entidad1();
        e1.set...
        e1.set...

        entidad1Service.insertEntidad1(e1);

        // Insertar Entidad2
        Entidad2 e2 = new Entidad2();
        e2.set...
        e2.set...

        entidad1Services.insertEntidad2(e2);
    }
}
```
