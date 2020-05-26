package com.guyang.basis.designPattern.strategy;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-15 16:40
 */
public class Car {

    private double price;

    Car(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                '}';
    }

    public double getPrice() {
        return price;
    }
}
