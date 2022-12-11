package top.puppetdev.demo.anno01_annos;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author puppet
 * @since 2022/9/29 21:38
 */
@Anno12("puppet")
@Anno12("Spring")
public class UseAnno12 {
    @Anno12s(
            {@Anno12("Java High Concurrency"), @Anno12("MySQL")}
    )
    private String name;

    public static void main(String[] args) throws NoSuchFieldException {
        Annotation[] annotations = UseAnno12.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        System.out.println("----------------------");

        Field nameField = UseAnno12.class.getDeclaredField("name");
        for (Annotation annotation : nameField.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
