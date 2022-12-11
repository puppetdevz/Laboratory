package top.puppetdev.demo.demo01;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author puppet
 * @since 2022/11/8 14:07
 */
@Data
public class CompositeObj {
    private String name;
    private Integer salary;

    private Car car1;
    private List<String> stringList;
    private List<Car> carList;

    private Set<String> stringSet;
    private Set<Car> carSet;

    private Map<String, String> stringMap;
    private Map<String, Car> stringCarMap;
}
