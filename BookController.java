package com.example.bookexplorer;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
        @DeleteMapping("/{id}")
public void deleteBook(@PathVariable Long id) {

    bookRepository.deleteById(id);
}
    
@PutMapping("/{id}")
public Book updateBook(@PathVariable Long id, @RequestBody Book book) {

    Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));

    existingBook.setBookName(book.getBookName());
    existingBook.setAuthorName(book.getAuthorName());
    existingBook.setIsbn(book.getIsbn());
    existingBook.setCategory(book.getCategory());
    existingBook.setPrice(book.getPrice());
    existingBook.setDescription(book.getDescription());
    existingBook.setAvailability(book.getAvailability());

    return bookRepository.save(existingBook);
}
}