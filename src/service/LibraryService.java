package service;

import model.Book;
import model.Member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books;

    public LibraryService() {
        this.books = new ArrayList<>();
    }

    // Load the list of the books
    public void loadBooks(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0];
                    String author = parts[1];
                    String isbn = parts[2];
                    books.add(new Book(title, author, isbn));
                }
            }
        }
    }

    // list of books that are available
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    // List of borrowed books
    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    // Borrowing a book
    public boolean borrowBook(Member member, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isBorrowed()) {
                book.borrow();
                System.out.println(member.getName() + " borrowed this book: " + book.getTitle());
                return true;
            }
        }
        return false;
    }

    // list of returned books
    public boolean returnBook(Member member, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isBorrowed()) {
                book.returnBook();
                System.out.println(member.getName() + " returned " + book.getTitle());
                return true;
            }
        }
        return false;
    }
}
