package com.guyang.basis.designPattern.f_proxy.staticProxy;

import com.guyang.basis.designPattern.f_proxy.Car;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-21 14:25
 */
public class ProxyTest {
    public static void main(String[] args) {
        CarProxy1 carProxy1 = new CarProxy1(new Car());
        carProxy1.move();

        System.out.println("S5300000000320001018C1".length());

    }
}
