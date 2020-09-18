package com.guyang.basis.designPattern.d_factory.factoryMethod;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-19 14:54
 */
public class SpaghettiFactory extends AbstractFactory {
    @Override
    Food create() {
        return new Spaghetti();
    }
}
