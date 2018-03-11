package com.beauchef.optimiticlocking.domain.repository;

import com.beauchef.optimiticlocking.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author beauchef on 2018-03-11.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
