package com.example.librarymanagementsystem.service;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.model.BookDto;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public Book addBook(BookDto bookDto) throws Exception {

        String errorMessage = validateBookRequest(bookDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Book book = new Book();
        book.setId(bookDto.getId());
        book.setAuthor(bookDto.getAuthor());
        book.setCount(bookDto.getCount());
        book.setSubject(bookDto.getSubject());
        book.setTitle(bookDto.getTitle());
        book.setPublisher(bookDto.getPublisher());
        book.setCopyright(bookDto.getCopyright());
        book.setEdition(bookDto.getEdition());
        book.setPages(bookDto.getPages());
        book.setAvailable(bookDto.getAvailable());

        return bookRepository.save(book);
    }

    private String validateBookRequest(BookDto bookDto) {
        String errorMessage = "";
        if (bookDto.getCount() <= 0) {
            errorMessage = errorMessage + "Count can not be 0 or less.";
        }

        if (bookDto.getPages() <= 0) {
            errorMessage = errorMessage + "Page can not be 0 or less.";
        }

        return errorMessage;
    }


    @Transactional
    public boolean updateBook(BookDto bookDto, Long id) throws Exception {

        String errorMessage = validateBookUpdate(bookDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<Book> bookToUpdateOptional = bookRepository.findById(id);
        if (bookToUpdateOptional.isPresent()) {
            Book bookToUpdate = bookToUpdateOptional.get();
            //bookToUpdate.setAuthor(bookDto.getAuthor());
            bookToUpdate.setCopyright(bookDto.getAvailable());
            bookToUpdate.setEdition(bookDto.getEdition());
            bookToUpdate.setCount(bookDto.getCount());
            return bookRepository.save(bookToUpdate) != null;
        } else {
            return false;
        }
    }

    private String validateBookUpdate(BookDto bookDto) {
        String errorMessage = "";
        if (bookDto.getAvailable() != null) {
            errorMessage = errorMessage + "Available can not be null.";
        }

        if (bookDto.getPages() <= 0) {
            errorMessage = errorMessage + "Page can not be 0 or less.";
        }

        return errorMessage;
    }


    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public List<Book> getAllBookBySubject(String subject) {
        return (List<Book>) bookRepository.findBySubject(subject);
    }

    @Transactional
    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }


}
