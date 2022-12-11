package top.puppetdev.demo.anno01_annos;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * @author puppet
 * @since 2022/9/28 09:43
 */
public class UseAnno7<@Anno7("T0是在类上声明的一个泛型类型变量") T0, @Anno7("T1是在类上声明的一个泛型类型变量") T1> {
    public <@Anno7("T2是在方法上声明的泛型类型变量") T2> void m1() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        for (TypeVariable typeParameter : UseAnno7.class.getTypeParameters()) {
            print(typeParameter);
        }

        for (TypeVariable typeVariable : UseAnno7.class.getDeclaredMethod("m1").getTypeParameters()) {
            print(typeVariable);
        }
    }

    private static void print(TypeVariable typeVariable) {
        System.out.println("类型变量名称：" + typeVariable.getName());
        Arrays.stream(typeVariable.getAnnotations()).forEach(System.out::println);
    }
}
