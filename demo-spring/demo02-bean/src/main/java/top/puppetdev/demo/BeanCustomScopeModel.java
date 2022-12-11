package top.puppetdev.demo;

/**
 * @author puppet
 * @since 2022/9/14 00:14
 */
public class BeanCustomScopeModel {
    public BeanCustomScopeModel(String beanScope) {
        System.out.println(String.format("线程: %s, create BeanCustomScopeModel, {sope=%s}, {this=%s}", Thread.currentThread(), beanScope, this));
    }
}
