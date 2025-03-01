package ir.mahdi.libra.controller;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;
import ir.mahdi.libra.service.BookCrudService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookCrudService bookCrudService;

    public BookController(BookCrudService bookCrudService) {
        this.bookCrudService = bookCrudService;
    }

    @GetMapping("")
    public List<BookDto> getBooks() {
        return bookCrudService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        BookDto bookDto = bookCrudService.getBook(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.ok(bookCrudService.createBook(createBookDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookCrudService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody CreateBookDto createBookDto) {
        BookDto bookDto = bookCrudService.updateBook(id, createBookDto);
        return ResponseEntity.ok(bookDto);
    }

}
