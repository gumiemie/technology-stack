package com.guyang.basis.designPattern.strategy;

import java.util.Comparator;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 比较器
 * @date 2020-01-15 16:47
 */
public class CarComparator implements Comparator<Car> {
    @Override
    public int compare(final Car car1, final Car car2) {
        if (car1.getPrice() - car2.getPrice() > 0) {
            return 1;
        } else if (car1.getPrice() - car2.getPrice() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
