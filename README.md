# EjemploAndroidRoom

Proyecto ejemplo de Android Room y SQLite

## Pasos a seguir

1. Importar dependencias en el archivo de Gradle y sincronizar
```
// Dependencias de Room
implementation("androidx.room:room-runtime:2.5.0")
annotationProcessor ("androidx.room:room-compiler:2.5.0")
```
3. Crear DatabaseHelper
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
5. Crear Entidades
6. Crear DAOs
7. Añadir Entidades y DAOs al DatabaseHelper
8. Crear Servicios
9. Usar los Servicios en las Activityes
