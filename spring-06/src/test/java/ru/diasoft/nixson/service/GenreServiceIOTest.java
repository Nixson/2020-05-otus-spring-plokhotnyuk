package ru.diasoft.nixson.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.nixson.domain.Genre;
import ru.diasoft.nixson.repository.GenreRepository;

import static org.mockito.Mockito.verify;

@DisplayName("Тестирование GenreServiceIO")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GenreServiceIO.class)
class GenreServiceIOTest {
    @Autowired
    private GenreService genreService;
    @MockBean
    private GenreRepository genreDao;

    @DisplayName("Получить все жанры")
    @Test
    void getAll() {
        genreService.getAll();
        verify(genreDao).getAll();
    }

    @DisplayName("Получить по id")
    @Test
    void getById() {
        genreService.getById(1L);
        verify(genreDao).findById(1L);
    }

    @DisplayName("Получить по name")
    @Test
    void getByName() {
        genreService.getByName("Научная");
        verify(genreDao).findByName("Научная");
    }

    @Test
    void insert() {
        Genre genre = Genre.builder().id(100L).name("genreName").build();
        genreService.insert(genre);
        verify(genreDao).save(genre);
    }
}