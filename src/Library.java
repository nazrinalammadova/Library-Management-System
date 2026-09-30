import java.util.ArrayList;
import java.util.List;

public class Library {
    public static final int MAX_BORROWED_BOOKS = 3;

    private final List<Book> books;
    private final List<Member> members;
    private int nextBookId = 1;
    private int nextMemberId = 1;

    public Library(List<Book> books, List<Member> members) {
        this.books = books;
        this.members = members;

        for (Book book : books) {
            nextBookId = Math.max(nextBookId, book.getId() + 1);
        }
        for (Member member : members) {
            nextMemberId = Math.max(nextMemberId, member.getId() + 1);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Book addBook(String title, String author, int year) {
        Book book = new Book(nextBookId++, title, author, year);
        books.add(book);
        return book;
    }

    public void removeBook(int bookId) throws LibraryException {
        Book book = findBookById(bookId);
        if (!book.isAvailable()) {
            throw new LibraryException("This book is borrowed right now and cannot be removed.");
        }
        books.remove(book);
    }

    public Book findBookById(int bookId) throws LibraryException {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        throw new LibraryException("Book with ID " + bookId + " was not found.");
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lower)
                    || book.getAuthor().toLowerCase().contains(lower)) {
                result.add(book);
            }
        }
        return result;
    }

    public Member registerMember(String name) {
        Member member = new Member(nextMemberId++, name);
        members.add(member);
        return member;
    }

    public Member findMemberById(int memberId) throws LibraryException {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        throw new LibraryException("Member with ID " + memberId + " was not found.");
    }

    public void borrowBook(int memberId, int bookId) throws LibraryException {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (!book.isAvailable()) {
            throw new LibraryException("\"" + book.getTitle() + "\" is already borrowed.");
        }
        if (member.getBorrowedBookIds().size() >= MAX_BORROWED_BOOKS) {
            throw new LibraryException(member.getName() + " already has "
                    + MAX_BORROWED_BOOKS + " books. Please return a book first.");
        }

        book.setAvailable(false);
        member.borrowBook(bookId);
    }

    public void returnBook(int memberId, int bookId) throws LibraryException {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (!member.hasBook(bookId)) {
            throw new LibraryException(member.getName() + " does not have this book.");
        }

        book.setAvailable(true);
        member.returnBook(bookId);
    }

    public List<Book> getBooksOfMember(int memberId) throws LibraryException {
        Member member = findMemberById(memberId);
        List<Book> result = new ArrayList<>();
        for (int bookId : member.getBorrowedBookIds()) {
            result.add(findBookById(bookId));
        }
        return result;
    }
}
