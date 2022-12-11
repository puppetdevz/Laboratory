package top.puppetdev.demo;

import org.junit.Test;
import top.puppetdev.demo.anno01_annos.UseAnno;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author puppet
 * @since 2022/9/28 10:52
 */
public class AnnosTest {
    @Test
    public void testParseClassAnno() {
        for (Annotation annotation : UseAnno.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }

    @Test
    public void testParseClassVariableTypeAnno() {
        for (TypeVariable typeVariable : UseAnno.class.getTypeParameters()) {
            System.out.println(typeVariable.getName() + " 变量类型注解信息为：");
            for (Annotation annotation : typeVariable.getAnnotations()) {
                System.out.println(annotation);
            }
        }
    }

    @Test
    public void testParseUsedOnFieldAnno() throws NoSuchFieldException {
        for (Annotation name : UseAnno.class.getDeclaredField("name").getAnnotations()) {
            System.out.println(name);
        }
    }

    @Test
    public void testParseUsedOnGenericTypeAnno() throws NoSuchFieldException {
        Field mapField = UseAnno.class.getDeclaredField("map");
        Type genericType = mapField.getGenericType();
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();

        AnnotatedType annotatedType = mapField.getAnnotatedType();
        AnnotatedType[] annotatedActualTypeArguments = ((AnnotatedParameterizedType) annotatedType).getAnnotatedActualTypeArguments();
        int i = 0;
        for (AnnotatedType annotatedActualTypeArgument : annotatedActualTypeArguments) {
            Type actualTypeArgument = actualTypeArguments[i++];
            System.out.println(actualTypeArgument.getTypeName() + "类型上的注解如下：");
            for (Annotation annotation : annotatedActualTypeArgument.getAnnotations()) {
                System.out.println(annotation);
            }
        }
    }

    @Test
    public void testParseUsedOnConstructorAnno() throws NoSuchMethodException {
        Constructor<UseAnno> constructor = UseAnno.class.getConstructor();
        Annotation[] annotations = constructor.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    @Test
    public void testParseUsedOnMethodAnno() throws NoSuchMethodException {
        Method method = UseAnno.class.getDeclaredMethod("m1", String.class);
        for (Annotation annotation : method.getAnnotations()) {
            System.out.println(annotation);
        }
    }

    @Test
    public void testParseUsedOnMethodParamAnno() throws NoSuchMethodException {
        Method method = UseAnno.class.getDeclaredMethod("m1", String.class);
        for (Parameter parameter : method.getParameters()) {
            System.out.println(String.format("参数 %s 上的注解如下", parameter.getName()));
            for (Annotation annotation : parameter.getAnnotations()) {
                System.out.println(annotation);
            }
        }
    }
}
