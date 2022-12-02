package com.example.accountsystem.controller;

import com.example.accountsystem.domain.Account;
import com.example.accountsystem.dto.CreateAccount;
import com.example.accountsystem.service.AccountService;
import com.example.accountsystem.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @PostMapping("/account")
    public CreateAccount.Response createAccount(
            @RequestBody @Valid CreateAccount.Request request) {
        accountService.createAccount(request.getUserId(), request.getInitialBalance());
        return "success";
    }

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock();
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }
}
