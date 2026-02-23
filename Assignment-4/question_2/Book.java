import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public String title;
    public String author;
    public double price;

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book Found ✅\n" +
               "ID: " + id + "\n" +
               "Title: " + title + "\n" +
               "Author: " + author + "\n" +
               "Price: " + price;
    }
}