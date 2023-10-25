package es.iescarrillo.android.ejemploandroidroom.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.Person;

@Dao
public interface CarDao {

    @Insert
    long insertCar(Car car);

    @Update
    void updateCar(Car car);

    @Delete
    void deleteCar(Car car);

    @Query("SELECT * FROM car")
    List<Car> getAll();
}
