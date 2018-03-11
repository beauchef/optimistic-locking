package com.beauchef.optimiticlocking.rest;

import com.beauchef.optimiticlocking.domain.dto.Account;
import com.beauchef.optimiticlocking.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author beauchef on 2018-03-11.
 */
@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public Account post(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/id/{id}/add/{amount}")
    public ResponseEntity<Account> addAmount(@PathVariable Long id, @PathVariable int amount) {
        return accountService.addAmount(id, amount)
                .map(acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
