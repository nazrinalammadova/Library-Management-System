public class Book {
    private final int id;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toFileLine() {
        return id + ";" + title + ";" + author + ";" + year + ";" + available;
    }

    public static Book fromFileLine(String line) {
        String[] parts = line.split(";");
        Book book = new Book(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]));
        book.setAvailable(Boolean.parseBoolean(parts[4]));
        return book;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return String.format("#%-3d %-30s %-20s %-6d %s", id, title, author, year, status);
    }
}
