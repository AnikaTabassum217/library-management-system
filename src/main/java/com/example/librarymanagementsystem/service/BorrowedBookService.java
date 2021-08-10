package com.example.librarymanagementsystem.service;
import com.example.librarymanagementsystem.entity.BorrowedBook;
import com.example.librarymanagementsystem.model.BorrowedBookDto;
import com.example.librarymanagementsystem.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowedBookService {

    @Autowired
    BorrowedBookRepository borrowedBookRepository;

    @Transactional
    public BorrowedBook addBorrowedBook(BorrowedBookDto borrowedBookDto) throws Exception {

        String errorMessage = validateBorrowedRequest(borrowedBookDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        BorrowedBook borrowedBook = new BorrowedBook();

        borrowedBook.setId(borrowedBookDto.getId());
        borrowedBook.setMemberId(borrowedBookDto.getMemberId());
        borrowedBook.setBookId(borrowedBookDto.getBookId());
        borrowedBook.setBorrowedDate(borrowedBookDto.getBorrowedDate());
        borrowedBook.setReturnDate(borrowedBookDto.getReturnDate());
        borrowedBook.setFine(borrowedBookDto.getFine());

        return borrowedBookRepository.save(borrowedBook);
    }

    private String validateBorrowedRequest(BorrowedBookDto borrowedBookDto) {
        String errorMessage = "";

        if (borrowedBookDto.getMemberId() <= 0) {
            errorMessage = errorMessage + "Member is not valid.";
        }

        if (borrowedBookDto.getBookId() <= 0) {
            errorMessage = errorMessage + "Book is not valid.";
        }

        if (borrowedBookDto.getBorrowedDate() != null) {
            errorMessage = errorMessage + "Borrowed Date is not valid.";
        }

        if (borrowedBookDto.getReturnDate() != null) {
            errorMessage = errorMessage + "Return Date is not valid.";
        }

        if (borrowedBookDto.getFine() < 0) {
            errorMessage = errorMessage + "Fine is not valid.";
        }

        return errorMessage;
    }

//    @Transactional
//    public boolean updateBorrowedBook(BorrowedBook borrowedBook) {
//        return borrowedBookRepository.save(borrowedBook) != null;
//    }

    @Transactional
    public boolean updateBook(BorrowedBookDto borrowedBookDto, Long id) throws Exception {

        String errorMessage = validateBorrowedBookUpdate(borrowedBookDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<BorrowedBook> bookToUpdateOptional = borrowedBookRepository.findById(id);
        if (bookToUpdateOptional.isPresent()) {
            //Optional to main class type
            BorrowedBook bookToUpdate = bookToUpdateOptional.get();
            bookToUpdate.setReturnDate(borrowedBookDto.getReturnDate());
            bookToUpdate.setFine(borrowedBookDto.getFine());
            return borrowedBookRepository.save(bookToUpdate) != null;
        } else {
            return false;
        }
    }

    private String validateBorrowedBookUpdate(BorrowedBookDto borrowedBookDto) {
        String errorMessage = "";
        if (borrowedBookDto.getReturnDate() != null) {
            errorMessage = errorMessage + "Available can not be null.";
        }

        if (borrowedBookDto.getFine() <= 0) {
            errorMessage = errorMessage + "Page can not be 0 or less.";
        }

        return errorMessage;
    }

    @Transactional
    public BorrowedBook findBookById(Long id) {
        return borrowedBookRepository.findById(id).get();
    }

    @Transactional
    public List<BorrowedBook> getBorrowedBooksByMemberId(int memberId) {
        return borrowedBookRepository.findByMemberId(memberId);
    }

    @Transactional
    public List<BorrowedBook> getBorrowedBooksByBookId(int bookId) {
        return borrowedBookRepository.findByBookId(bookId);
    }

}
