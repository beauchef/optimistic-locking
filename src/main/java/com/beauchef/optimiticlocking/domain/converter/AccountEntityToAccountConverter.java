package com.beauchef.optimiticlocking.domain.converter;

import com.beauchef.optimiticlocking.domain.dto.Account;
import com.beauchef.optimiticlocking.domain.entity.AccountEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author beauchef on 2018-03-11.
 */
@Component
public class AccountEntityToAccountConverter implements Converter<AccountEntity, Account> {

    @Override
    public Account convert(AccountEntity entity) {
        Account account = new Account();
        account.setId(entity.getId());
        account.setUsername(entity.getUsername());
        account.setBalance(entity.getBalance());
        return account;
    }
}
