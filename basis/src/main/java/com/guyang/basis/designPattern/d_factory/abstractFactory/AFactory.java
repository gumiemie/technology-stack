package com.guyang.basis.designPattern.d_factory.abstractFactory;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-20 13:59
 */
public class AFactory extends AbstractFactory {
    @Override
    Livestock createLivestock() {
        return new Cow();
    }

    @Override
    Vegetable createVegetable() {
        return new Potato();
    }
}
