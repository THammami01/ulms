package main.models;

public class BookToLoan {
	private int bookId;
	private String bookTitle;
	private int subscriberId;
	private String subscriberFullname;

    public BookToLoan() {
    }

    public BookToLoan(int bookId, String bookTitle) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public BookToLoan(int bookId, String bookTitle, int subscriberId, String subscriberFullname) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.subscriberId = subscriberId;
        this.subscriberFullname = subscriberFullname;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriberFullname() {
        return subscriberFullname;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void setSubscriberFullname(String subscriberFullname) {
        this.subscriberFullname = subscriberFullname;
    }

    @Override
    public String toString() {
        return String.format("BookToLoan{%d, %s, %d, %s}", bookId, bookTitle, subscriberId, subscriberFullname);
    }
}
