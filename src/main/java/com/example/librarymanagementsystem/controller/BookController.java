package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.model.BookDto;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book addBook(@RequestBody BookDto bookDto) throws Exception {
        Book response = bookService.addBook(bookDto);
        return response;
    }

//    @RequestMapping(value = "/book", method = RequestMethod.PUT)
//    public boolean updateBook(@RequestBody BookDto bookDto) {
//        return bookService.updateBook(bookDto);
//    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public boolean updateBook(@RequestBody BookDto bookDto,
                              @PathVariable Long id) throws Exception {
        return bookService.updateBook(bookDto, id);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)

    public @ResponseBody
    Book getAllBooks(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return book;
    }

    @RequestMapping(value = "/bookBySubject/{subject}", method = RequestMethod.GET)
    public List<Book> getBookBySubject(@PathVariable String subject) {
        return bookService.getAllBookBySubject(subject);
    }


}
