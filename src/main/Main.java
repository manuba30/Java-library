package main;

import model.Book;
import model.Member;
import service.LibraryService;
import service.MemberService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Members and books
        LibraryService libraryService = new LibraryService();
        MemberService memberService = new MemberService();

        // take the books from the .txt
        try {
            libraryService.loadBooks("src/resources/books.txt");
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        // Add members
        Member member1 = new Member("Pierre Dupont");
        Member member2 = new Member("Gabriel Marcosí");
        memberService.addMember(member1);
        memberService.addMember(member2);

        // List of books that are available
        List<Book> availableBooks = libraryService.getAvailableBooks();
        System.out.println("Available Books:");
        for (Book book : availableBooks) {
            System.out.println(book);
        }

        // borrowed book
        boolean success = libraryService.borrowBook(member2, "978-0-452-28423-4");
        if (success) {
            System.out.println(member2.getName() + " borrowed a book.");
        } else {
            System.out.println("Failed to borrow this book.");
        }

        // List of borrowed books
        List<Book> borrowedBooks = libraryService.getBorrowedBooks();
        System.out.println("Borrowed books:");
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }
}
