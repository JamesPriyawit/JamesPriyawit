package com.example.liquidbase.controllers;

import com.example.liquidbase.entities.User;
import com.example.liquidbase.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.liquidbase.repositories.*;

import java.util.List;

@RestController
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam(value = "age", required = false) Integer age
    ){
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        userRepository.save(user);
        return user;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(
            @PathVariable("id") Integer id,
            @RequestParam(value = "firstname", required = false) String firstname,
            @RequestParam(value = "lastname", required = false) String lastname,
            @RequestParam(value = "age", required = false) Integer age
    ){
        User user = userRepository.findOneById(id);
        if (user == null){
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Not found id");
        }
        user.setFirstname(firstname != null ? firstname : user.getFirstname());
        user.setLastname(lastname != null ? lastname : user.getLastname());
        user.setAge(age != null ? age : user.getAge());
        userRepository.saveAndFlush(user);
        return user;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<User> list(

    ){
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") Integer id
    ){
        userRepository.deleteById(id);
    }
}
