package com.example.librarymanagementsystem.repository;
import com.example.librarymanagementsystem.entity.BorrowedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BorrowedBookRepository extends CrudRepository<BorrowedBook,Long> {
    List<BorrowedBook> findByMemberId(int memberId);
    List<BorrowedBook> findByBookId(int bookId);

}
