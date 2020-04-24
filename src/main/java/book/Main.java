package book;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:bookDb");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            BookDao bookDao = handle.attach((BookDao.class));
            bookDao.createTable();
            bookDao.insert(new Book("9781472154668", "Delia Owens", "Where the Crawdads Sing", Book.Format.PAPERBACK, "Little, Brown Book Group", LocalDate.parse("2019-12-20"), 384, true));
            bookDao.insert(new Book("9781529029581", "Toshikazu Kawaguchi", "Before the Coffee Gets Cold", Book.Format.PAPERBACK, "Pan MacMillan", LocalDate.parse("2019-11-07"), 224, true));
            bookDao.insert(new Book("123456", "Example Arthor", "Example Title", Book.Format.HARDBACK, "Example Publisher", LocalDate.parse("1900-01-01"),100,false));
            System.out.println("-----Original Book-----");
            bookDao.findAll().stream().forEach(System.out::println);
            Book book = bookDao.find("123456").get();
            bookDao.delete(book);
            System.out.println("---After Deleted Book---");
            bookDao.findAll().stream().forEach(System.out::println);
        }
    }

}
