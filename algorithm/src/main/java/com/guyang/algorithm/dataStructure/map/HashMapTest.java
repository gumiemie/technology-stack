package com.guyang.algorithm.dataStructure.map;

import java.util.HashMap;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-04-23 10:05
 */
public class HashMapTest {


    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            map.put(i,i+"v");
        }

    }

}
