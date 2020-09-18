package com.guyang.spring.boot.service.impl;

import cn.bidlink.qualification.model.QuaNumbers;
import cn.bidlink.qualification.remote.service.QuaNumbersRemoteService;
import com.guyang.spring.boot.core.event.UserRegisterEvent;
import com.guyang.spring.boot.dao.UserDao;
import com.guyang.spring.boot.model.User;
import com.guyang.spring.boot.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-14 17:16
 */
@Service
public class UserServiceImpl implements UserService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    private UserDao userDao;

    private QuaNumbersRemoteService quaNumbersRemoteService;

    @Override
    public List<User> getAll() {

        QuaNumbers quaNumbers = new QuaNumbers();
        Long totalCount = quaNumbersRemoteService.getTotalCount(quaNumbers);
        return userDao.getAll();
    }

    @Override
    public void insert(User user) {
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user.getName()));
    }

    @Override
    public User findByPk(String id) {
        return userDao.findByPk(id);
    }

    @Override
    public void update(final User user) {

    }

    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
