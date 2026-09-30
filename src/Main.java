import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library(FileManager.loadBooks(), FileManager.loadMembers());
        System.out.println("=== Library Management System ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1 -> addBook(library);
                    case 2 -> printBooks(library.getBooks());
                    case 3 -> searchBooks(library);
                    case 4 -> removeBook(library);
                    case 5 -> registerMember(library);
                    case 6 -> printMembers(library);
                    case 7 -> borrowBook(library);
                    case 8 -> returnBook(library);
                    case 9 -> showMemberBooks(library);
                    case 0 -> running = false;
                    default -> System.out.println("Unknown option. Please try again.");
                }
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            }

            FileManager.save(library);
        }

        System.out.println("Data saved. Goodbye!");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add book");
        System.out.println("2. Show all books");
        System.out.println("3. Search books");
        System.out.println("4. Remove book");
        System.out.println("5. Register member");
        System.out.println("6. Show all members");
        System.out.println("7. Borrow book");
        System.out.println("8. Return book");
        System.out.println("9. Show books of a member");
        System.out.println("0. Exit");
    }

    private static void addBook(Library library) {
        String title = readText("Title: ");
        String author = readText("Author: ");
        int year = readInt("Year: ");
        Book book = library.addBook(title, author, year);
        System.out.println("Book added with ID " + book.getId() + ".");
    }

    private static void searchBooks(Library library) {
        String keyword = readText("Enter title or author: ");
        List<Book> result = library.searchBooks(keyword);
        if (result.isEmpty()) {
            System.out.println("No books found.");
        } else {
            printBooks(result);
        }
    }

    private static void removeBook(Library library) throws LibraryException {
        int id = readInt("Book ID: ");
        library.removeBook(id);
        System.out.println("Book removed.");
    }

    private static void registerMember(Library library) {
        String name = readText("Member name: ");
        Member member = library.registerMember(name);
        System.out.println("Member registered with ID " + member.getId() + ".");
    }

    private static void borrowBook(Library library) throws LibraryException {
        int memberId = readInt("Member ID: ");
        int bookId = readInt("Book ID: ");
        library.borrowBook(memberId, bookId);
        System.out.println("Book borrowed successfully.");
    }

    private static void returnBook(Library library) throws LibraryException {
        int memberId = readInt("Member ID: ");
        int bookId = readInt("Book ID: ");
        library.returnBook(memberId, bookId);
        System.out.println("Book returned successfully.");
    }

    private static void showMemberBooks(Library library) throws LibraryException {
        int memberId = readInt("Member ID: ");
        List<Book> books = library.getBooksOfMember(memberId);
        if (books.isEmpty()) {
            System.out.println("This member has no borrowed books.");
        } else {
            printBooks(books);
        }
    }

    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("The library has no books yet.");
            return;
        }
        System.out.printf("%-4s %-30s %-20s %-6s %s%n", "ID", "Title", "Author", "Year", "Status");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void printMembers(Library library) {
        if (library.getMembers().isEmpty()) {
            System.out.println("No members yet.");
            return;
        }
        for (Member member : library.getMembers()) {
            System.out.println(member);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else if (input.contains(";")) {
                System.out.println("The ';' character is not allowed.");
            } else {
                return input;
            }
        }
    }
}
