package pro.sky.course.webdemo.services;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.course.webdemo.model.Book;
import pro.sky.course.webdemo.model.BookCover;
import pro.sky.course.webdemo.repositories.BookCoverRepository;
import pro.sky.course.webdemo.repositories.BookRepository;

@Service
public class BookService {

    /**
     * asfasdsa dasdsad as dasd
     */
    public static final String CONST = "saasdad";

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Поиск книги по ее идентификатору в БД. <b>жирный</b> <i>курсив</i> <u>подчеркнутый</u>
     * Используется метод репозитория {@link JpaRepository#findById(Object)}
     *
     * @param id идентификатор искомой книги, не может быть null
     * @throws EntityNotFoundException если книга с указанным id не была найдена в БД
     * @return найденная книга
     *
     * @see JpaRepository#findById(Object)
     */
    public Book findBook(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Book editBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findByName(String name) {
        return bookRepository.findByNameIgnoreCase(name);
    }

    public Collection<Book> findByAuthor(String author) {
        return bookRepository.findBooksByAuthorContainsIgnoreCase(author);
    }

    public Collection<Book> findByNamePart(String part) {
        return bookRepository.findAllByNameContainsIgnoreCase(part);
    }
}
