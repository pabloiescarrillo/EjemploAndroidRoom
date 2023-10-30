package es.iescarrillo.android.ejemploandroidroom.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "car", foreignKeys = @ForeignKey(entity = Car.class, parentColumns = "id",
        childColumns = "person_id", onDelete = ForeignKey.CASCADE))
public class Car {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "plate")
    private String plate;
    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "person_id")
    private long personId;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
