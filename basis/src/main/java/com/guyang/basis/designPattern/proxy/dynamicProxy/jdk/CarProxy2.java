package com.guyang.basis.designPattern.proxy.dynamicProxy.jdk;

import com.guyang.basis.designPattern.proxy.Car;
import com.guyang.basis.designPattern.proxy.Movable;

import java.lang.reflect.Proxy;

/**
 * @author guyang <guyang@ebnew.com>
 * @description jdk动态代理的局限性：代理目标对象必须实现接口
 * @date 2020-01-21 14:37
 */
public class CarProxy2 {

    public static void main(String[] args) {
        Movable proxyInstance = (Movable) Proxy.newProxyInstance(Car.class.getClassLoader(), new Class[]{Movable.class},
                (proxy, method, arg) -> {
                    System.out.println("move started!");
                    Object result = method.invoke(proxy, arg);
                    System.out.println("move stopped!");
                    return result;
                });

        proxyInstance.move();
    }
}
