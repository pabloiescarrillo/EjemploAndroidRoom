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

**7. Usar los Servicios en las Activityes**
