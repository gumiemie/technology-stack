package com.guyang.spring.boot.core.event;

import com.guyang.spring.boot.model.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 用户注册事件
 * @date 2019-12-31 17:31
 */
public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    public UserRegisterEvent(final Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
