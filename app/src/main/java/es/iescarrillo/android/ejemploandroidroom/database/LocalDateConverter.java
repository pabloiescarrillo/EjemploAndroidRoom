package es.iescarrillo.android.ejemploandroidroom.database;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalDateConverter {
    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : LocalDate.ofEpochDay(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDate date) {
        return date == null ? null : date.toEpochDay();
    }

}
