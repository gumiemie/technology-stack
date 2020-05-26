package com.guyang.basis.designPattern.prototype;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 浅clone->:clone对象的时候不会clone 引用对象。
 * 深clone：对象的属性是引用对象，属性也实现Cloneable接口并override clone方法。
 * 调用主对象的clone方法后，属性也要调用clone方法
 * @date 2020-01-19 10:36
 */
public class Person implements Cloneable {

    private Integer age;
    private String name;

    private Location location;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person clone = (Person)super.clone();
        clone.setLocation((Location) this.location.clone());
        return clone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Person(final Integer age, final String name) {
        this.age = age;
        this.name = name;
    }

    static class Location implements Cloneable{
        private String city;

        public Location(final String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public void setCity(final String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "city='" + city + '\'' +
                    '}';
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}
