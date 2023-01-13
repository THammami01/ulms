package main.models;

public class Subscriber {
    private int id;
	private String fullname;

    public Subscriber() {
    }

    public Subscriber(int id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return String.format("Subscriber{%d, %s}", id, fullname);
    }
}
