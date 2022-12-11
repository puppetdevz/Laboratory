package top.puppetdev.demo.anno01_annos;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Target({ElementType.PACKAGE,
        ElementType.TYPE,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.TYPE_PARAMETER,
        ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@interface AnnoA {
    String value();
}

@Target({ElementType.PACKAGE,
        ElementType.TYPE,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.TYPE_PARAMETER,
        ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@interface AnnoB {
    int value();
}

/**
 * @author puppet
 * @since 2022/9/28 10:48
 */
@AnnoA("Used on classes")
@AnnoB(0)
public class UseAnno<@AnnoA("Used on classes variable type V1") @AnnoB(1) V1, @AnnoA("Used on classes variable type V2") @AnnoB(2) V2> {

    @AnnoA("Used on field")
    @AnnoB(3)
    private String name;

    private Map<@AnnoA("Used on generic type: String") @AnnoB(4) String, @AnnoA("Used on generic type: Integer") @AnnoB(5) Integer> map;

    @AnnoA("Used on constructor")
    @AnnoB(6)
    public UseAnno() {
        this.name = name;
    }

    @AnnoA("Used on method")
    @AnnoB(7)
    public String m1(@AnnoA("Used on parameter") @AnnoB(8) String name) {
        return null;
    }
}
