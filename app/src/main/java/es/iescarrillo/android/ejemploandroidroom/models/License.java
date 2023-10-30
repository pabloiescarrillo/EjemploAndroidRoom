package es.iescarrillo.android.ejemploandroidroom.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "license", foreignKeys = @ForeignKey(entity = License.class, parentColumns = "id", childColumns = "person_id", onDelete = ForeignKey.CASCADE))
public class License {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "start_date")
    private LocalDate startDate;

    @ColumnInfo(name = "end_date")
    private LocalDate endDate;

    // Foreignkey to Person
    @ColumnInfo(name = "person_id")
    private long personId;

    public License() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", personId=" + personId +
                '}';
    }
}
