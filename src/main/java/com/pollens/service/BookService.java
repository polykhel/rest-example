package com.pollens.service;

import com.pollens.domain.Book;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BookService {
  Book saveBook(@NotNull @Valid final Book book);

  List<Book> getBooks();

  Optional<Book> getBook(Long id);

  void deleteBook(final Long id);
}
