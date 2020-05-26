package com.guyang.basis.thread;

import java.io.Serializable;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-03-08 10:07
 */
public class User implements Serializable {

    private String id;
    private String name;

    public User(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
