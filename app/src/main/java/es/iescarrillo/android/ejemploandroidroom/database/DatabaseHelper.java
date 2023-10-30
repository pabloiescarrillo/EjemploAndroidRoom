package es.iescarrillo.android.ejemploandroidroom.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import es.iescarrillo.android.ejemploandroidroom.daos.BookDao;
import es.iescarrillo.android.ejemploandroidroom.daos.CarDao;
import es.iescarrillo.android.ejemploandroidroom.daos.LicenseDao;
import es.iescarrillo.android.ejemploandroidroom.daos.PersonDao;
import es.iescarrillo.android.ejemploandroidroom.models.Book;
import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.License;
import es.iescarrillo.android.ejemploandroidroom.models.Person;

@Database(entities = {Person.class, Car.class, License.class, Book.class}, version = 4)
@TypeConverters({Converters.class})
public abstract class DatabaseHelper extends RoomDatabase {

    // Insertar los DAOs
    public abstract PersonDao personDao();
    public abstract CarDao carDao();
    public abstract LicenseDao licenseDao();
    public abstract BookDao bookDao();

    // Instancia estática de la clase, para oder usarla en toda la aplicación
    private static DatabaseHelper instance;

    // Método de Android Room para crear la base de datos
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseHelper.class, "EjemploRoom")
                    .fallbackToDestructiveMigration() // Si se cambia la versión elimina y reconstruye
                    .build();
        }
        return instance;
    }

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
