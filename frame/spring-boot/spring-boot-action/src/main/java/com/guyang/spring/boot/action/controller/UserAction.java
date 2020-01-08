package com.guyang.spring.boot.action.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.guyang.spring.boot.core.dto.RespDto;
import com.guyang.spring.boot.model.User;
import com.guyang.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-13 15:21
 */
@RestController
@RequestMapping("user/json")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("getAll")
    public List<User> getAll(){
        return  userService.getAll();
    }

    @RequestMapping("save")
    public RespDto save(User user){
        if (StringUtils.isBlank(user.getId())){
            userService.insert(user);
        }else {
            userService.update(user);
        }
        return new RespDto();
    }

}
