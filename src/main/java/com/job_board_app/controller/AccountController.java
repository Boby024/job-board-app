package com.job_board_app.controller;

import com.job_board_app.model.User;
import com.job_board_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            User user = accountService.getUserById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user-email-username")
    public ResponseEntity<User> getUserByEmailOrUsername(@RequestParam String query) {
        try {
            User user = accountService.getUserByEmailOrUsername(query, query);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            return ResponseEntity.ok(accountService.getAllUsers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> seyHello() {
        return ResponseEntity.ok("Hello from Account Controller!");
    }
}
