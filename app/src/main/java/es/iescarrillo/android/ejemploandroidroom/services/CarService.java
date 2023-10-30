package es.iescarrillo.android.ejemploandroidroom.services;

import android.app.Application;

import androidx.room.Transaction;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.daos.CarDao;
import es.iescarrillo.android.ejemploandroidroom.database.DatabaseHelper;
import es.iescarrillo.android.ejemploandroidroom.models.Car;


public class CarService implements CarDao {

    private CarDao carDao;

    public CarService(Application application){
        DatabaseHelper db = DatabaseHelper.getInstance(application);
        carDao = db.carDao();
    }
    @Override
    public long insertCar(Car car) {
        return carDao.insertCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    @Override
    public void deleteCar(Car car) {
        carDao.deleteCar(car);
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }
}
