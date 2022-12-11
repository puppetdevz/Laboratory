package top.puppetdev.jvm;

import sun.applet.Main;

/**
 * @author puppet
 * @since 2022/6/23 23:58
 */
public class TestPlusPlus {
    public static void main(String[] args) {
        new TestPlusPlus().method1();
        new TestPlusPlus().method2();
    }

    public void method1() {
        int i = 1;
        int a = i++;
        // 打印 1
        System.out.println(a);
    }

    public void method2() {
        int i = 1;
        int a = ++i;
        // 打印 2
        System.out.println(a);
    }
}
