public class InsertionBooks {

    public static void main(String[] args) {

        // INSERT 10 BOOK RECORDS
        BookDBOperations.insertBook(101, "Java Programming",
                "Herbert Schildt", "McGraw Hill", "10-01-2023", 550, 2);

        BookDBOperations.insertBook(102, "Effective Java",
                "Joshua Bloch", "Pearson", "15-03-2022", 700, 1);

        BookDBOperations.insertBook(103, "Clean Code",
                "Robert C. Martin", "Prentice Hall", "20-07-2021", 650, 3);

        BookDBOperations.insertBook(104, "Head First Java",
                "Kathy Sierra", "O'Reilly", "05-05-2020", 480, 2);

        BookDBOperations.insertBook(105, "Design Patterns",
                "Erich Gamma", "Pearson", "12-09-2019", 900, 1);

        BookDBOperations.insertBook(106, "Spring in Action",
                "Craig Walls", "Manning", "18-11-2021", 720, 2);

        BookDBOperations.insertBook(107, "Java Concurrency",
                "Brian Goetz", "Addison-Wesley", "01-02-2022", 850, 1);

        BookDBOperations.insertBook(108, "Microservices",
                "Sam Newman", "O'Reilly", "14-08-2020", 780, 2);

        BookDBOperations.insertBook(109, "Hibernate in Action",
                "Christian Bauer", "Manning", "22-06-2019", 690, 1);

        BookDBOperations.insertBook(110, "Algorithms",
                "Robert Sedgewick", "Pearson", "30-01-2018", 950, 1);

        // DISPLAY ALL BOOKS
        BookDBOperations.displayBooks();
    }
}
