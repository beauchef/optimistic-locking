package com.beauchef.optimiticlocking.service;

import com.beauchef.optimiticlocking.domain.dto.Account;
import com.beauchef.optimiticlocking.domain.entity.AccountEntity;
import com.beauchef.optimiticlocking.domain.repository.AccountRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author beauchef on 2018-03-11.
 */
@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final ConversionService conversionService;

    @Autowired
    public AccountService(final AccountRepository accountRepository, final ConversionService conversionService) {
        this.accountRepository = accountRepository;
        this.conversionService = conversionService;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(a -> conversionService.convert(a, Account.class))
                .collect(Collectors.toList());
    }

    public Account createAccount(Account account) {
        Assert.notNull(account, "Account cannot be null.");
        Assert.isNull(account.getId(), "Account ID must be null for creation.");
        AccountEntity entity = conversionService.convert(account, AccountEntity.class);
        entity = accountRepository.save(entity);
        return conversionService.convert(entity, Account.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Retryable(StaleStateException.class)
    @SneakyThrows
    public Optional<Account> addAmount(Long id, int amount) {
        Optional<Account> account;
        try {
            AccountEntity entity = accountRepository.getOne(id);
            entity.setBalance(entity.getBalance() + amount);
            account = Optional.of(conversionService.convert(entity, Account.class));
            Thread.sleep(10_000L); // SLEEP!
        } catch (EntityNotFoundException ex) {
            account = Optional.empty();
        }
        return account;
    }
}
