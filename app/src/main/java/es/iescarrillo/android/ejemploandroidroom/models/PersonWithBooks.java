package es.iescarrillo.android.ejemploandroidroom.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PersonWithBooks {

    // Embedded => para traer el objeto completo
    @Embedded
    private Person person;

    @Relation(parentColumn = "id", entityColumn = "person_id")
    private List<Book> bookList;

    public PersonWithBooks() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "PersonWithBooks{" +
                "person=" + person.toString() +
                ", bookList=" + bookList.toString() +
                '}';
    }
}
