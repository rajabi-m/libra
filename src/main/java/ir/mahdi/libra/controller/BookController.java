package ir.mahdi.libra.controller;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;
import ir.mahdi.libra.service.BookManagerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookManagerService bookManagerService;

    public BookController(BookManagerService bookManagerService) {
        this.bookManagerService = bookManagerService;
    }

    @GetMapping("/")
    public List<BookDto> getBooks() {
        return bookManagerService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        BookDto bookDto = bookManagerService.getBook(id);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/sort")
    public List<BookDto> getBooksSortByReleaseYear() {
        return bookManagerService.getAllBooksSortByReleaseYear();
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.ok(bookManagerService.createBook(createBookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookManagerService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody CreateBookDto createBookDto) {
        BookDto bookDto = bookManagerService.updateBook(id, createBookDto);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/find/{title}")
    public List<BookDto> findBooksByTitle(@PathVariable String title) {
        return bookManagerService.findBooksByTitle(title);
    }

}
