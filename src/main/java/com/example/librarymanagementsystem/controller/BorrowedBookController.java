package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.BorrowedBook;
import com.example.librarymanagementsystem.model.BorrowedBookDto;
import com.example.librarymanagementsystem.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowedBookController {

    @Autowired
    BorrowedBookService borrowedBookService;

    @RequestMapping(value = "/borrowedBookService", method = RequestMethod.POST)
    public BorrowedBook addBorrowedBook(@RequestBody BorrowedBookDto borrowedBookDto) throws Exception {
        BorrowedBook response = borrowedBookService.addBorrowedBook(borrowedBookDto);
        return response;
    }

//    @RequestMapping(value = "/borrowedBookService", method = RequestMethod.PUT)
//    public boolean updateBorrowedBook(@RequestBody BorrowedBook borrowedBook) {
//        return borrowedBookService.updateBorrowedBook(borrowedBook);
//    }

    @RequestMapping(value = "/borrowedBookService/{id}", method = RequestMethod.PUT)
    public boolean updateBook(@RequestBody BorrowedBookDto borrowedBookDto, @PathVariable Long id) throws Exception {
        return borrowedBookService.updateBook(borrowedBookDto, id);
    }


    @RequestMapping(value = "/borrowedBookService/{id}", method = RequestMethod.GET)

    public @ResponseBody
    BorrowedBook getBorrowedBooks(@PathVariable Long id) {
        BorrowedBook borrowedBook = borrowedBookService.findBookById(id);
        return borrowedBook;
    }

    @RequestMapping(value = "/borrowedBook/findByMemberId/{memberId}", method = RequestMethod.GET)

    public @ResponseBody
    List<BorrowedBook> getBorrowedBooksByUser(@PathVariable int memberId) {
        List<BorrowedBook> borrowedBookList = borrowedBookService.getBorrowedBooksByMemberId(memberId);
        return borrowedBookList;
    }

    @RequestMapping(value = "/borrowedBook/findByBookId/{bookId}", method = RequestMethod.GET)
    public @ResponseBody
    List<BorrowedBook> getBorrowedBook(@PathVariable int bookId) {
        List<BorrowedBook> borrowedBookList = borrowedBookService.getBorrowedBooksByBookId(bookId);
        return borrowedBookList;
    }

}
