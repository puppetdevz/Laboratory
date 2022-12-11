package top.puppetdev.demo.anno04_conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author puppet
 * @since 2022-10-13 上午 11:52
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// 这个地方加了 @Conditional，入参为自定义的 Condition 实现类：EnvCondition
@Conditional(EnvCondition.class)
public @interface EnvConditional {

    // 用 value 存储环境
    Env value() default Env.DEV;

    /**
     * 枚举值：开发、测试、生成
     */
    enum Env {
        DEV,
        TEST,
        PROD
    }
}
