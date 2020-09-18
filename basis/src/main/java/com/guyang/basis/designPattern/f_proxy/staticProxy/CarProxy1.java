package com.guyang.basis.designPattern.f_proxy.staticProxy;

import com.guyang.basis.designPattern.f_proxy.Movable;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-21 14:24
 */
public class CarProxy1 implements Movable {
    private Movable car;

    @Override
    public void move() {
        System.out.println("begin move");
        car.move();
        System.out.println("move stopped!");
    }

    CarProxy1(final Movable car) {
        this.car = car;
    }
}
