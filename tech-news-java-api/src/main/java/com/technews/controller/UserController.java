// Update doesn't work unless all fields filled out
// How to validate, if id doesn't exist and throw errors
// Need to create login

package com.technews.controller;

import com.technews.models.Post;
import com.technews.models.User;
import com.technews.repository.UserRepository;
import com.technews.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    VoteRepository voteRepository;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        List<User> userList = repository.findAll();
        for (User u : userList) {
            List<Post> postList = u.getPosts();
            for (Post p : postList) {
                p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
            }
        }
        return userList;
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        User returnUser = repository.getReferenceById(id);
        List<Post> postList = returnUser.getPosts();
        for (Post p : postList) {
            p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
        }

        return returnUser;
    }

    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) {
        // Encrypt password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        repository.save(user);
        return user;
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User tempUser = repository.getReferenceById(id);

        tempUser.setUsername(user.getUsername());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        repository.save(tempUser);
        return tempUser;
    }

//    @PutMapping("/api/users/login")
//    public User login(@PathVariable int id, @RequestBody User user) {
//        User tempUser = repository.getReferenceById(id);
//
//        tempUser.setUsername(user.getUsername());
//        tempUser.setLoggedIn(true);
//        tempUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
//        repository.save(tempUser);
//        return tempUser;
//    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
}
