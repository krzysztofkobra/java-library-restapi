package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book borrowBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent() && book.get().isAvailable()){
            book.get().setAvailable(false);
            return bookRepository.save(book.get());
        }
        return null;
    }

    public Book returnBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent() && !book.get().isAvailable()){
            book.get().setAvailable(true);
            return bookRepository.save(book.get());
        }
        return null;
    }

    public Book findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> findByYearOfPublish(int yearOfPublish) {
        return bookRepository.findByYearOfPublish(yearOfPublish);
    }

    public List<Book> findByAvailable(boolean available) {
        return bookRepository.findByAvailable(available);
    }
}
