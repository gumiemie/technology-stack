package com.guyang.spring.boot.core.dubbo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-24 16:38
 */
public class DubboConfigCondition implements Condition {

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        String dubboOpenFlag = context.getEnvironment().getProperty("dubbo.open");
        return dubboOpenFlag != null && !dubboOpenFlag.isEmpty() && dubboOpenFlag.equals("true");
    }

}
