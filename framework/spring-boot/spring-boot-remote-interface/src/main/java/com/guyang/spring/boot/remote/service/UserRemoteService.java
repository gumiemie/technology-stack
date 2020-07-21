package com.guyang.spring.boot.remote.service;

import com.guyang.spring.boot.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserRemoteService {


    List<User> getAll(@Valid @NotNull  Integer type);
}
