package ru.diasoft.nixson.service;

import org.springframework.shell.standard.ShellOption;
import ru.diasoft.nixson.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Iterable<Book> getAll();

    Optional<Book> getById(Long id);

    String writeList(Iterable<Book> bookList);

    String write(Book book);

    void delete(Long id);

    String update(Long id
            , String bookName
            , String author
            , String genre
            , String year
            , String description);

    String insert(String bookName
            , String author
            , String genre
            , String year
            , String description);

    List<Book> findByName(String name);

    List<Book> findByAuthor(String name);

    List<Book> findByGenre(String name);
}
