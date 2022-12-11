package top.puppetdev.demo;

/**
 * @author puppet
 * @since 2022/9/14 00:14
 */
public class BeanScopeModel {
    public BeanScopeModel(String beanScope) {
        System.out.println(String.format("create BeanScopeModel, {sope=%s}, {this=%s}", beanScope, this));
    }
}
