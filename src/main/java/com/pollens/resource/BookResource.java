package com.pollens.resource;

import com.pollens.domain.Book;
import com.pollens.exception.BookAlreadyExistsException;
import com.pollens.exception.BookNotFoundException;
import com.pollens.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookResource {

  private final BookService bookService;

  public BookResource(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getBooks() {
    log.debug("Received request to list all books");
    return bookService.getBooks();
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable Long id) {
    log.debug("Received request to get book with id {}", id);
    return bookService.getBook(id).orElseThrow(() ->
            new BookNotFoundException(String.format("Book with id=%s doesn't exist", id))
    );
  }

  @PostMapping
  public Book saveBook(@RequestBody @Valid final Book book) {
    log.debug("Received request to create book {}", book);
    return bookService.saveBook(book);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    log.debug("Received request to delete book with id {}", id);
    bookService.deleteBook(id);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.CONFLICT)
  public String handleAlreadyExistsException(BookAlreadyExistsException e) {
    return e.getMessage();
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(BookNotFoundException e) {
    return e.getMessage();
  }
}
