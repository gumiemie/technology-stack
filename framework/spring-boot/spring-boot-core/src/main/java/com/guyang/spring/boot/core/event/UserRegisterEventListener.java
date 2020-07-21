package com.guyang.spring.boot.core.event;

import com.guyang.spring.boot.model.User;
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
        User user = event.getUser();
        System.out.printf("用户:%s注册成功;\n", user.getLoginName());
        System.out.print("用户:%s注册成功;\n");
    }
}
