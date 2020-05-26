package com.guyang.basis.designPattern.factory.abstractFactory;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-20 14:00
 */
public class BFactory extends AbstractFactory {
    @Override
    Livestock createLivestock() {
        return new Tup();
    }

    @Override
    Vegetable createVegetable() {
        return new Tomato();
    }
}
