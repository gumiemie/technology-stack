package com.guyang.basis.designPattern.c_prototype;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-01-19 10:40
 */
public class Prototype {

    public static void main(String[] args) {
        Person gu = new Person(1, "gu");
        gu.setLocation(new Person.Location("bj"));
        Person clone = null;
        try {
            clone = (Person) gu.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(gu);
        System.out.println(clone);

        gu.getLocation().setCity("sh");

        System.out.println(clone.getLocation().toString());
    }

}
