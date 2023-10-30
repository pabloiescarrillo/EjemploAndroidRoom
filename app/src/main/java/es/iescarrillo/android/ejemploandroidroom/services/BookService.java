package es.iescarrillo.android.ejemploandroidroom.services;

import android.app.Application;

import java.util.List;

import es.iescarrillo.android.ejemploandroidroom.daos.BookDao;
import es.iescarrillo.android.ejemploandroidroom.database.DatabaseHelper;
import es.iescarrillo.android.ejemploandroidroom.models.Book;

public class BookService implements BookDao {

    private BookDao bookDao;

    public BookService(Application application){
        DatabaseHelper db = DatabaseHelper.getInstance(application);
        bookDao = db.bookDao();
    }


    @Override
    public long insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
