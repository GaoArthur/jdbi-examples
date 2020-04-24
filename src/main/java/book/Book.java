package book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {

    public static enum Format {
        HARDBACK,
        PAPERBACK
    }

    private String isbn13;
    private String author;
    private String title;
    private Format format;
    private String publisher;
    private LocalDate publicationDate;
    private int pages;
    private boolean available;

}
