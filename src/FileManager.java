import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DATA_FOLDER = "data";
    private static final String BOOKS_FILE = DATA_FOLDER + "/books.txt";
    private static final String MEMBERS_FILE = DATA_FOLDER + "/members.txt";

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        for (String line : readLines(BOOKS_FILE)) {
            books.add(Book.fromFileLine(line));
        }
        return books;
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        for (String line : readLines(MEMBERS_FILE)) {
            members.add(Member.fromFileLine(line));
        }
        return members;
    }

    public static void save(Library library) {
        new File(DATA_FOLDER).mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : library.getBooks()) {
                writer.println(book.toFileLine());
            }
        } catch (IOException e) {
            System.out.println("Error: could not save books. " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : library.getMembers()) {
                writer.println(member.toFileLine());
            }
        } catch (IOException e) {
            System.out.println("Error: could not save members. " + e.getMessage());
        }
    }

    private static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: could not read " + path + ". " + e.getMessage());
        }
        return lines;
    }
}
