import java.util.ArrayList;
import java.util.List;

public class Member {
    private final int id;
    private final String name;
    private final List<Integer> borrowedBookIds;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void borrowBook(int bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(int bookId) {
        borrowedBookIds.remove(Integer.valueOf(bookId));
    }

    public boolean hasBook(int bookId) {
        return borrowedBookIds.contains(bookId);
    }

    public String toFileLine() {
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < borrowedBookIds.size(); i++) {
            if (i > 0) {
                ids.append(",");
            }
            ids.append(borrowedBookIds.get(i));
        }
        return id + ";" + name + ";" + ids;
    }

    public static Member fromFileLine(String line) {
        String[] parts = line.split(";", -1);
        Member member = new Member(Integer.parseInt(parts[0]), parts[1]);
        if (!parts[2].isEmpty()) {
            for (String bookId : parts[2].split(",")) {
                member.borrowBook(Integer.parseInt(bookId));
            }
        }
        return member;
    }

    @Override
    public String toString() {
        return String.format("#%-3d %-25s Borrowed books: %d", id, name, borrowedBookIds.size());
    }
}
