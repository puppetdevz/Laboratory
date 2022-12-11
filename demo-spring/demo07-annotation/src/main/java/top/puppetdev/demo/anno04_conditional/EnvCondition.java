package top.puppetdev.demo.anno04_conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author puppet
 * @since 2022-10-13 上午 11:54
 */
public class EnvCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 假定当前环境为“开发环境”，获取当前使用环境
        EnvConditional.Env currentEnv = EnvConditional.Env.DEV;
        // 获取使用了 @EnvConditional 注解的配置类上的环境信息
        EnvConditional.Env env = (EnvConditional.Env) metadata.getAnnotationAttributes(EnvConditional.class.getName()).get("value");
        return currentEnv == env;
    }
}
