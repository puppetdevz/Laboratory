package top.puppetdev.demo.demo04;

import lombok.ToString;

/**
 * @author puppet
 * @since 2022-11-12 下午 10:33
 */
@ToString
public class Person {
    private String name;
    private Integer age;

    public Person() {
        System.out.println("调用Person()");
    }

    @MyAutowired
    public Person(String name) {
        System.out.println("调用Person(String name)");
        this.name = name;
    }

    public Person(String name, Integer age) {
        System.out.println("调用Person(String name, int age)");
        this.name = name;
        this.age = age;
    }
}
