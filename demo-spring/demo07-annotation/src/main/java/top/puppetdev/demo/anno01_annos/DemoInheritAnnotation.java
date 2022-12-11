package top.puppetdev.demo.anno01_annos;

import java.lang.annotation.*;

/**
 * @author puppet
 * @since 2022/9/29 21:31
 */
public class DemoInheritAnnotation {
    // 使用了 @Inherited，表示可以被继承
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface A1 {
    }

    // 使用了 @Inherited，表示可以被继承
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface A2 {
    }

    // 定义接口 I1，上面使用了 @A1 注解，此时哪怕 I1 被实现，A1 注解也不会被继承
    @A1
    interface I1 {
    }

    // 定义了一个 C1 类，使用了 A2 注解，此时如果 C1 被继承，则 A2 注解也会被继承
    @A2
    static class C1 {
    }

    // C2 继承了 C1，并实现了 I1 接口，由于 C1 上有一个可以被继承的注解 A2，所以 C2 也继承了 A2
    static class C2 extends C1 implements I1 {
    }

    // 获取 C2 上以及从父类继承过来的所有注解，然后输出
    public static void main(String[] args) {
        for (Annotation annotation : C2.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
