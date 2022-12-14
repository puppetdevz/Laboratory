package top.puppetdev.demo.demo02;

/**
 * @author puppet
 * @since 2022-12-14 下午 10:21
 */
public class MyService implements IService {
    @Override
    public void say(String name) {
        System.out.println("hello, " + name);
    }
}
