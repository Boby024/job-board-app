package com.job_board_app.controller;

import com.job_board_app.model.User;
import com.job_board_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("user")
    public ResponseEntity<User> getUser(@RequestParam String id) {
        User user = accountService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("user-email-username")
    public ResponseEntity<User> getUserByEmailOrUsername(@RequestParam String query) {
        User user = accountService.getUserByEmailOrUsername(query, query);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(accountService.getAllUsers());
    }

    @GetMapping("/test")
    public ResponseEntity<String> seyHello() {
        return ResponseEntity.ok("Hello from User Controller!");
    }
}
