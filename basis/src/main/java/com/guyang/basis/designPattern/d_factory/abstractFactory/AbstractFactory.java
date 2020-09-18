package com.guyang.basis.designPattern.d_factory.abstractFactory;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-20 13:57
 */
public abstract class AbstractFactory {

    abstract Livestock createLivestock();

    abstract Vegetable createVegetable();

}
