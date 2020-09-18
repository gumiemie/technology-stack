package com.guyang.basis.designPattern.d_factory.factoryMethod;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-19 14:55
 */
public class FactoryMethod {
    public static void main(String[] args) {
        AbstractFactory factory = new BreadFactory();
        Food food = factory.create();
        food.showName();
    }
}
