package ru.diasoft.nixson.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.diasoft.nixson.domain.Author;
import ru.diasoft.nixson.domain.Book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование AuthorRepositoryImpl")
@DataMongoTest
@Import(TestRepositoryConfig.class)
class AuthorRepositoryImplTest {
    private final String NAME = "authorName";
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(Book.class);
    }

    @DisplayName("Добавить автора в книгу")
    @Test
    void addTest() {
        Book book =  mongoTemplate.insert(Book.builder()
                .name("Test book 2")
                .authors(List.of(Author.builder()
                        .id(UUID.randomUUID().toString())
                        .name("author 1").build()))
                .build());

        Author author = Author.builder()
                .name("NewAuthor")
                .build();
        authorRepository.add(book.getId(), author);

        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("authors.name").is(author.getName())), Book.class);
        assert foundBook != null;

        assertThat(foundBook.getAuthors())
                .extracting(Author::getName)
                .contains(author.getName());
    }

    @DisplayName("Обновить автора в книге")
    @Test
    void updateTest() {
        Book book =  mongoTemplate.insert(Book.builder()
                .name("Update book 3")
                .authors(List.of(Author.builder()
                        .id(UUID.randomUUID().toString())
                        .name("update author 1").build()))
                .build());

        final Author author1 = book.getAuthors().stream().findFirst().orElseThrow(RuntimeException::new);
        final Author newAuthor = Author.builder().name("new Author update").build();

        authorRepository.update(author1.getId(), newAuthor);
        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("authors._id").is(author1.getId())), Book.class);

        assertThat(foundBook.getAuthors())
                .extracting(Author::getName)
                .contains(newAuthor.getName());
    }

    @DisplayName("Получить всех авторов")
    @Test
    void findAllTest() {
        List<Author> authors1 = List.of(Author.builder()
                .id(UUID.randomUUID().toString())
                .name("Zelazny").build());

        List<Author> authors2 = List.of(Author.builder()
                .id(UUID.randomUUID().toString())
                .name("Volkov").build());

        mongoTemplate.insert(Book.builder()
                .name("Test book 1")
                .authors(authors1)
                .build());
        mongoTemplate.insert(Book.builder()
                .name("Test book 2")
                .authors(authors2)
                .build());

        List<Author> lst = authorRepository.findAll();

        assertThat(lst)
                .extracting(Author::getName)
                .containsAll(authors1.stream().map(Author::getName).collect(Collectors.toList()))
                .containsAll(authors2.stream().map(Author::getName).collect(Collectors.toList()));
    }

    @DisplayName("Получить всех авторов по книге")
    @Test
    void findByBookIdTest() {
        List<Author> authors = List.of(Author.builder()
                .id(UUID.randomUUID().toString())
                .name("Author1").build());

        Book book = Book.builder()
                .name("Test book 1")
                .authors(authors)
                .build();
        mongoTemplate.insert(book);

        List<Author> lst = authorRepository.findByBookId(book.getId());

        assertThat(lst)
                .extracting(Author::getName)
                .containsExactlyElementsOf(authors.stream().map(Author::getName).collect(Collectors.toList()));
    }

    @DisplayName("Удалить автора")
    @Test
    void deleteByIdTest() {
        final Author author = Author.builder()
                .id(UUID.randomUUID().toString())
                .name("Author1").build();

        Book book = Book.builder()
                .name("Test book 1")
                .authors(List.of(author))
                .build();

        mongoTemplate.insert(book);
        authorRepository.deleteById(author.getId());

        assertThat(mongoTemplate.find(new Query(Criteria.where("authors._id").is(author.getId())), Book.class))
                .isEmpty();
    }
}