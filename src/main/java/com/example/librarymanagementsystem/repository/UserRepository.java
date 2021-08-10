package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByMembershipCard(String membershipCard);
}
