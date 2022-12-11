package top.puppetdev.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author puppet
 * @since 2022/9/21 15:47
 */
@Getter
@Setter
@ToString
public class MenuModel {
    //菜单名称
    private String label;
    //同级别排序
    private Integer theSort;
}
