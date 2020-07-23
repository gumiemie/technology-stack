package com.guyang.spring.boot.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 用户注册事件
 * @date 2019-12-31 17:31
 */
public class UserRegisterEvent extends ApplicationEvent {

    private String userName;

    public UserRegisterEvent(final Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

}
