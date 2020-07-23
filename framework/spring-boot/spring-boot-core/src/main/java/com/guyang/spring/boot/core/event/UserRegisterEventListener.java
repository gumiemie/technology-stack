package com.guyang.spring.boot.core.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-02 09:57
 */
@Component
public class UserRegisterEventListener implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(final UserRegisterEvent event) {
        String userName = event.getUserName();
        System.out.printf("用户:%s注册成功;\n", userName);
        System.out.print("用户:%s注册成功;\n");
    }
}
