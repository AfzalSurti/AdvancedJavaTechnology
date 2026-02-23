public class BookDBTest {

    public static void main(String[] args) {

        // INSERT
        BookDBOperations.insertBook(
                101, "Java Programming",
                "Herbert Schildt", "McGraw Hill",
                "10-01-2023", 550, 2
        );

        // DISPLAY
        BookDBOperations.displayBooks();

        // UPDATE
        BookDBOperations.updateBookPrice(101, 600);

        // DISPLAY AGAIN
        BookDBOperations.displayBooks();

        // DELETE
        BookDBOperations.deleteBook(101);

        // FINAL DISPLAY
        BookDBOperations.displayBooks();
    }
}
