package top.puppetdev.demo.demo04;

/**
 * @author puppet
 * @since 2022/12/19 22:08
 */
public class MyService {
    public void m1() {
        System.out.println("我是 m1 方法");
    }

    public void m2() {
        System.out.println(10 / 0);
        System.out.println("我是 m2 方法");
    }
}
