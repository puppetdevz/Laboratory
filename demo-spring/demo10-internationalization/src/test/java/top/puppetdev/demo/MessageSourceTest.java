package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.demo01.MainConfig;

import java.util.Locale;

/**
 * @author puppet
 * @since 2022-11-16 下午 07:27
 */
public class MessageSourceTest {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        // 未指定 Locale，此时系统会取默认的locale对象，本地默认的值中文【中国】，即：zh_CN
        System.out.println(context.getMessage("name", null, null));
        System.out.println(context.getMessage("name", null, Locale.CHINA)); // CHINA对应zh_CN
        System.out.println(context.getMessage("name", null, Locale.UK));  // UK对应en_GB
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        // 未指定Locale，此时系统会取默认的，本地电脑默认的值中文【中国】，即：zh_CN
        System.out.println(context.getMessage("personal_introduction", new String[]{"Spring高手", "Java高手"}, Locale.CHINA)); //CHINA对应：zh_CN
        System.out.println(context.getMessage("personal_introduction", new String[]{"Spring", "Java"}, Locale.UK)); //UK对应en_GB
    }
}
