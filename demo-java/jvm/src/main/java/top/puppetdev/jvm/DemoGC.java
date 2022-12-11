package top.puppetdev.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @author puppet
 * @since 2022/5/11 11:56
 */
public class DemoGC {
    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<>();
        int times = 10000;
        while (true) {
            int sleep = new Random().nextInt(100);
            if (System.currentTimeMillis() % 2 == 0) {
                list.clear();
            } else {
                for (int i = 0; i < times; i++) {
                    Properties properties = new Properties();
                    properties.put("key_" + i, "value_" + System.currentTimeMillis() + i);
                    list.add(properties);
                }
            }
            // System.out.println("the size of list: " + list.size());
            Thread.sleep(sleep);
        }
    }
}
