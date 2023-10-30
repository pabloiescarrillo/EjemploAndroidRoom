package es.iescarrillo.android.ejemploandroidroom.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.models.License;
import es.iescarrillo.android.ejemploandroidroom.models.Person;

@Dao
public interface LicenseDao {

    @Insert
    long insertLicense(License license);

    @Update
    void updateLicense(License license);

    @Delete
    void deleteLicense(License license);

    @Query("SELECT * FROM license")
    List<License> getAll();
}
