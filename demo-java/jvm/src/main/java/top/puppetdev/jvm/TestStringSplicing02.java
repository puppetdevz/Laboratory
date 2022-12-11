package top.puppetdev.jvm;

/**
 * @author puppet
 * @since 2022/6/24 0:11
 */
public class TestStringSplicing02 {
    public static void main(String[] args) {
        new TestStringSplicing02().m1();
        new TestStringSplicing02().m2();
    }

    public void m1() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            str = str + i;
        }
        System.out.println(str);
    }

    public void m2() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }
}
