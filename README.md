# Library Management System (Java)

A console application for managing a small library. Users can add books, register members, borrow and return books. All data is saved in text files, so it is not lost when the program closes.

I built this project to practice **object-oriented programming** in Java.

## Features

- Add, remove, list and search books (by title or author)
- Register members and view their borrowed books
- Borrow and return books
- Rules: a member can borrow at most **3 books**, and a borrowed book cannot be removed
- Data is saved automatically to `data/books.txt` and `data/members.txt`
- Input validation (wrong numbers or empty fields do not crash the program)

## Concepts used

- Classes and objects, encapsulation (private fields + getters)
- Collections (`ArrayList`, `List`)
- Custom exception (`LibraryException`) for business rule errors
- File input/output (`BufferedReader`, `PrintWriter`)
- Separation of logic: `Library` (rules), `FileManager` (saving), `Main` (user interface)

## Project structure

```
src/
├── Main.java              # Console menu
├── Library.java           # Main logic
├── Book.java              # Book class
├── Member.java            # Member class
├── FileManager.java       # Save / load data
└── LibraryException.java  # Custom exception
```

## How to run

Requires Java 17 or newer.

```bash
javac -d out src/*.java
java -cp out Main
```

## Example

```
=== Library Management System ===

1. Add book
2. Show all books
...
Choose an option: 2
ID   Title                          Author               Year   Status
#1   1984                           George Orwell        1949   Available
#2   Clean Code                     Robert C. Martin     2008   Borrowed
```

## What I learned

- How to split a program into classes with clear responsibilities
- How to use exceptions to handle errors in a clean way
- How to save objects to a file and load them back

## Possible improvements

- Add due dates and late fees
- Use a MySQL database instead of text files
- Add a graphical interface (JavaFX)
