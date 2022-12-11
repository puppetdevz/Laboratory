package top.puppetdev.demo.anno03_import.demo_cost_time;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author puppet
 * @since 2022-10-09 下午 09:05
 */
public class MethodCostTimeImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                MethodCostTimeProxyBeanPostProcessor.class.getName()
        };
    }
}
