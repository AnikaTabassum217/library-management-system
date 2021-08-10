package com.example.librarymanagementsystem.service;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.model.UserDto;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User addUser(UserDto userdto) throws Exception {
        String errorMessage = validateUserRequest(userdto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        User user = new User();

        user.setId(userdto.getId());
        user.setUserName(userdto.getUserName());
        user.setPassword(userdto.getPassword());
        user.setType(userdto.getType());
        user.setMembershipCard(userdto.getMembershipCard());


        return userRepository.save(user);
    }

    private String validateUserRequest(UserDto userdto) {
        String errorMessage = "";
        if (userdto.getPassword() != null) {
            errorMessage = errorMessage + "Password is not valid.";
        }

        if (userdto.getUserName() != null) {
            errorMessage = errorMessage + "User Name is not valid.";
        }

        return errorMessage;
    }

    @Transactional
    public boolean updateUser(UserDto userDto, Long id) throws Exception {

        String errorMessage = validateUserUpdate(userDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<User> userToUpdateOptional = userRepository.findById(id);
        if (userToUpdateOptional.isPresent()) {
            //Optional to main class type

            User userToUpdate = userToUpdateOptional.get();
            userToUpdate.setPassword(userDto.getPassword());
            userToUpdate.setType(userDto.getType());
            return userRepository.save(userToUpdate) != null;
        } else {
            return false;
        }
    }

    private String validateUserUpdate(UserDto userDto) {
        String errorMessage = "";
        if (userDto.getPassword() != null) {
            errorMessage = errorMessage + "Available can not be null.";
        }

        if (userDto.getType() != null) {
            errorMessage = errorMessage + "Page can not be 0 or less.";
        }

        return errorMessage;
    }


    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User getAllBookByMembershipCard(String membershipCard) {
        return userRepository.findByMembershipCard(membershipCard);
    }

}
