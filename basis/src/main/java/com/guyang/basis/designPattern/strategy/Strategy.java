package com.guyang.basis.designPattern.strategy;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 策略模式
 * @date 2020-01-15 16:31
 */
public class Strategy {


    public static void main(String[] args) {

        Car[] cars = {new Car(3), new Car(16), new Car(1), new Car(20)};
        Sorter<Car> sorter = new Sorter<>();
        sorter.sort(cars, (c1, c2) -> {
            if (c1.getPrice() > c2.getPrice()) return 1;
            else if (c1.getPrice() < c2.getPrice()) return -1;
            return 0;
        });

        System.out.println(Arrays.toString(cars));
    }

}
