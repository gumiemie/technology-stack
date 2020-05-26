package com.guyang.basis.designPattern.factory.factoryMethod;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-19 15:00
 */
public class BreadFactory extends AbstractFactory {
    @Override
    Food create() {
        return new Bread();
    }
}
