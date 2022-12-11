package top.puppetdev.jvm;

/**
 * @author puppet
 * @since 2022/4/19 22:50
 */
public class DemoJVM {
    public static void main(String[] args) {
        String myParam = System.getProperty("myParam");
        if (myParam == null) System.out.println("defaultParam");
        else System.out.println(myParam);
        System.gc();
    }
}
