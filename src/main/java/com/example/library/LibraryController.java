package com.example.library;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook = libraryService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PutMapping("/books/{id}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id) {
        Book borrowedBook = libraryService.borrowBook(id);
        if (borrowedBook != null) {
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/books/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long id) {
        Book returnedBook = libraryService.returnBook(id);
        if (returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/books/id/{id}")
    public Book getBooksByTitle(@PathVariable Long id) {
        return libraryService.findBookById(id);
    }

    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return libraryService.findByTitle(title);
    }

    @GetMapping("/books/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return libraryService.findByAuthor(author);
    }

    @GetMapping("/books/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return libraryService.findByGenre(genre);
    }

    @GetMapping("/books/year/{year}")
    public List<Book> getBooksByYear(@PathVariable int year) {
        return libraryService.findByYearOfPublish(year);
    }

    @GetMapping("/books/available/{available}")
    public List<Book> getBooksByAvailability(@PathVariable boolean available) {
        return libraryService.findByAvailable(available);
    }
}
