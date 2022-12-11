package top.puppetdev.jvm;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author puppet
 * @since 2022/4/24 21:53
 */
public class DemoJvmOutOfMemory {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 1000; j++) {
                stringBuilder.append(UUID.randomUUID());
            }
            strings.add(stringBuilder.toString());
        }
        System.out.println("完成");
    }
}
