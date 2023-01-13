package main.models;

public class Loan {
    private int id;
    private int bookId;
    private int subscriberId;

    public Loan() {
    }

    public Loan(int id, int bookId, int subscriberId) {
        this.id = id;
        this.bookId = bookId;
        this.subscriberId = subscriberId;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public String toString() {
        return String.format("Loan{%d, %d, %d}", id, bookId, subscriberId);
    }
}
