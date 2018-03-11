package com.beauchef.optimiticlocking.domain.converter;

import com.beauchef.optimiticlocking.domain.dto.Account;
import com.beauchef.optimiticlocking.domain.entity.AccountEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author beauchef on 2018-03-11.
 */
@Component
public class AccountToAccountEntityConverter implements Converter<Account, AccountEntity> {

    @Override
    public AccountEntity convert(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setUsername(account.getUsername());
        entity.setBalance(account.getBalance());
        return entity;
    }
}
