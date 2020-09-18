package com.guyang.spring.boot.core.validator;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-27 11:13
 */
public abstract class AbstractValidator implements EnvironmentAware {
     protected Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

}
