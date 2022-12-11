package top.puppetdev.jvm;

/**
 * @author puppet
 * @since 2022/6/24 0:11
 */
public class TestStringSplicing {
    public static void main(String[] args) {
        new TestStringSplicing().m1();
        new TestStringSplicing().m2();
    }

    public void m1() {
        String s1 = "123";
        String s2 = "456";
        String s3 = s1 + s2;
        System.out.println(s3);
    }

    public void m2() {
        String s1 = "123";
        String s2 = "456";
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        String s3 = sb.toString();
        System.out.println(s3);
    }
}
