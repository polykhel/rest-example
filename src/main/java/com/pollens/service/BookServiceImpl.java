package com.pollens.service;

import com.pollens.exception.BookAlreadyExistsException;
import com.pollens.domain.Book;
import com.pollens.repo.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Validated
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Book saveBook(@NotNull @Valid Book book) {
    log.debug("Creating book {}", book);
    repository.findById(book.getId()).ifPresent(existingBook -> {
      throw new BookAlreadyExistsException(
              String.format("There already exists a book with id=%s", book.getId())
      );
    });
    return repository.save(book);
  }

  @Override
  public List<Book> getBooks() {
    log.debug("Retrieving list of books");
    return repository.findAll();
  }

  @Override
  public Optional<Book> getBook(Long id) {
    log.debug("Retrieving book with id {}", id);
    return repository.findById(id);
  }

  @Override
  public void deleteBook(Long id) {
    log.debug("Deleting book with id {}", id);
    repository.deleteById(id);
  }
}
