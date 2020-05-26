package com.guyang.basis.designPattern.strategy;

import java.util.Comparator;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 排序器
 * @date 2020-01-15 16:32
 */
public class Sorter<T> {

    public void sort(T[] ts, Comparator<T> comparator) {
        for (int i = 0; i < ts.length - 1; i++) {
            for (int j = 0; j < ts.length - 1; j++) {
                if (comparator.compare(ts[i], ts[j]) > 0) {
                    swap(ts, i, j);
                }
            }
        }
    }

    private void swap(final T[] ts, final int i, final int j) {
        T temp = ts[i];
        ts[i] = ts[j];
        ts[j] = temp;
    }

}
