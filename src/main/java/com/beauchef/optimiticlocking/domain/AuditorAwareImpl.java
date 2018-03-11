package com.beauchef.optimiticlocking.domain;

import org.springframework.data.domain.AuditorAware;

/**
 * @author beauchef on 2018-03-11.
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return "system";
    }
}