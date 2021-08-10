package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.model.UserDto;
import com.example.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User addUser(@RequestBody UserDto userdto) throws Exception {
        User response = userService.addUser(userdto);
        return response;
    }

//    @RequestMapping(value = "/user", method = RequestMethod.PUT)
//    public boolean updateUser(@RequestBody User user) {
//        return userService.updateUser(user);
//    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody UserDto userDto,
                              @PathVariable Long id) throws Exception {
        return userService.updateUser(userDto, id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)

    public @ResponseBody
    User getAllUsers(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return user;
    }

    @RequestMapping(value = "/SearchUserByMembershipCard/{membershipCard}", method = RequestMethod.GET)
    public User getUserByMembershipCard(@PathVariable String membershipCard) {
        return userService.getAllBookByMembershipCard(membershipCard);
    }


}
