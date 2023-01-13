package main.models;

import main.Main;
import main.utils.Logger;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static Connection connection;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String query;

    public static boolean connected;

    static {
        try {
            Main.initDirs();
            Thread.sleep(2000);
            Class.forName("org.sqlite.JDBC");
            String dbUrl = String.format("jdbc:sqlite:%sDB.db", Main.appDir);
            connection = DriverManager.getConnection(dbUrl);
            st = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            connected = false;
        }

        connected = true;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public static void init() {
        try {
            query = "CREATE TABLE IF NOT EXISTS Book(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL" +
                    ");";
            st.executeUpdate(query);

            query = "CREATE TABLE IF NOT EXISTS Subscriber(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "fullname TEXT NOT NULL" +
                    ");";
            st.executeUpdate(query);

            query = "CREATE TABLE IF NOT EXISTS Loan(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "bookId INTEGER NOT NULL, " +
                    "subscriberId INTEGER NOT NULL, " +
                    "FOREIGN KEY(bookId) REFERENCES Book(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY(subscriberId) REFERENCES Subscriber(id) ON DELETE CASCADE" +
                    ");";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public static void backup() {
        try {
            String datetime = Logger.getDatetime();

            String command = String.format("mkdir \"%s\\%s\\\"", Main.backupsDir, datetime);
            Runtime.getRuntime().exec("cmd /c " + command);

            command = String.format("copy %sDB.db \"%s\\%s\\\"", Main.appDir, Main.backupsDir, datetime);
            Runtime.getRuntime().exec("cmd /c " + command);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public static ArrayList<Book> getBooks(Book book) {
        query = "SELECT * FROM Book WHERE id = ? OR title = ? ORDER BY id DESC, title;";

        ArrayList<Book> books = new ArrayList<>();

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, book.getId());
            pst.setString(2, book.getTitle());
            rs = pst.executeQuery();

            while (rs.next()) {
                Book iterBook = new Book(
                        rs.getInt("id"),
                        rs.getString("title")
                );
                books.add(iterBook);
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return null;
    }

    public static boolean insertBook(Book book) {
        query = "INSERT INTO Book(title) VALUES(?);";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, book.getTitle());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static boolean updateBook(Book book) {
        query = "UPDATE Book SET title = ? WHERE id = ?;";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, book.getTitle());
            pst.setInt(2, book.getId());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static boolean deleteBook(Book book) {
        query = "DELETE FROM Book WHERE id = ?;";

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, book.getId());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static ArrayList<Subscriber> getSubscribers(Subscriber subscriber) {
        query = "SELECT * FROM Subscriber WHERE id = ? OR fullname = ? ORDER BY id DESC, fullname;";

        ArrayList<Subscriber> subscribers = new ArrayList<>();

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, subscriber.getId());
            pst.setString(2, subscriber.getFullname());
            rs = pst.executeQuery();

            while (rs.next()) {
                Subscriber iterBook = new Subscriber(
                        rs.getInt("id"),
                        rs.getString("fullname")
                );
                subscribers.add(iterBook);
            }

            return subscribers;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return null;
    }

    public static boolean insertSubscriber(Subscriber subscriber) {
        query = "INSERT INTO Subscriber(fullname) VALUES(?);";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, subscriber.getFullname());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static boolean updateSubscriber(Subscriber subscriber) {
        query = "UPDATE Subscriber SET fullname = ? WHERE id = ?;";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, subscriber.getFullname());
            pst.setInt(2, subscriber.getId());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static boolean deleteSubscriber(Subscriber subscriber) {
        query = "DELETE FROM Subscriber WHERE id = ?;";

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, subscriber.getId());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static boolean loanBook(Loan loan) {
        query = "INSERT INTO Loan(bookId, subscriberId) VALUES(?, ?);";

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, loan.getBookId());
            pst.setInt(2, loan.getSubscriberId());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static boolean returnBook(Loan loan) {
        query = "DELETE FROM Loan WHERE bookId = ? AND subscriberId = ?;";

        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, loan.getBookId());
            pst.setInt(2, loan.getSubscriberId());
            if (pst.executeUpdate() > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public static ArrayList<BookToLoan> getLoanedBooks() {
        return getLoanedBooks(false);
    }

    public static ArrayList<BookToLoan> getLoanedBooks(boolean joinSubscriber) {
        ArrayList<BookToLoan> books = new ArrayList<>();

        try {
            if (joinSubscriber) {
                query = "SELECT B.id as bookId, B.title as bookTitle, S.id AS subscriberId, S.fullname AS subscriberFullname " +
                        "FROM Loan AS L, Book as B, Subscriber as S " +
                        "WHERE L.bookId = B.id AND L.subscriberId = S.id;";

                rs = st.executeQuery(query);

                while (rs.next()) {
                    BookToLoan iterBook = new BookToLoan(
                            rs.getInt("bookId"),
                            rs.getString("bookTitle"),
                            rs.getInt("subscriberId"),
                            rs.getString("subscriberTitle")
                    );
                    books.add(iterBook);
                }
            } else {
                query = "SELECT B.id as bookId, B.title as bookTitle " +
                        "FROM Loan AS L, Book as B " +
                        "WHERE L.bookId = B.id;";

                rs = st.executeQuery(query);

                while (rs.next()) {
                    BookToLoan iterBook = new BookToLoan(
                            rs.getInt("bookId"),
                            rs.getString("bookTitle")
                    );
                    books.add(iterBook);
                }
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return null;
    }

    public static ArrayList<BookToLoan> getAvailableBooks() {
        query = "SELECT id as bookId, title as bookTitle " +
                "FROM Book " +
                "WHERE id NOT IN (SELECT bookId FROM Loan);";

        ArrayList<BookToLoan> books = new ArrayList<>();

        try {
            rs = st.executeQuery(query);

            while (rs.next()) {
                BookToLoan iterBook = new BookToLoan(
                        rs.getInt("bookId"),
                        rs.getString("bookTitle")
                );
                books.add(iterBook);
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return null;
    }
}
