package es.iescarrillo.android.ejemploandroidroom.services;

import android.app.Application;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.daos.LicenseDao;
import es.iescarrillo.android.ejemploandroidroom.database.DatabaseHelper;
import es.iescarrillo.android.ejemploandroidroom.models.Car;
import es.iescarrillo.android.ejemploandroidroom.models.License;

public class LicenseService implements LicenseDao {

    private LicenseDao licenseDao;

    public LicenseService(Application application){
        DatabaseHelper db = DatabaseHelper.getInstance(application);
        licenseDao = db.licenseDao();
    }
    @Override
    public long insertLicense(License license) {
        return licenseDao.insertLicense(license);
    }

    @Override
    public void updateLicense(License license) {
        licenseDao.updateLicense(license);
    }

    @Override
    public void deleteLicense(License license) {
        licenseDao.deleteLicense(license);
    }

    @Override
    public List<License> getAll() {
        return licenseDao.getAll();
    }
}
