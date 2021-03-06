package ru.diasoft.nixson.service;

import ru.diasoft.nixson.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Iterable<Author> getAll();
    Optional<Author> getById(Long id);
    Author insert(Author author);
    Optional<Author> getByParam(String authorNameOrId);
    Optional<Author> getByName(String name);

}
