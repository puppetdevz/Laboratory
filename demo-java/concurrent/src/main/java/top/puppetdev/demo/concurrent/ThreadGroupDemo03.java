package top.puppetdev.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-07-29 下午 11:42
 */
public class ThreadGroupDemo03 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().getThreadGroup());
        System.out.println(Thread.currentThread().getThreadGroup().getParent());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getParent());
    }
}
