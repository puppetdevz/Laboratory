package top.puppetdev.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.puppetdev.demo.dao.entity.Customer;

import javax.persistence.criteria.*;
import java.util.Optional;

/**
 * @author puppet
 * @since 2022/4/14 18:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;
    
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("Test");
        customerDao.save(customer);
    }
    
    @Test
    public void testFindById() {
        Optional<Customer> one = customerDao.findById(1l);
        if (one.isPresent()) {
            System.out.println(one.get());
        }
    }
    
    @Test
    public void testUpdate() {
        Optional<Customer> one = customerDao.findById(1L);
        if (one.isPresent()) {
            Customer customer = one.get();
            customer.setCustName("haha");
            customerDao.save(customer);
        }
    }
    
    @Test
    public void testDelete() {
        customerDao.deleteById(1l);
    }
    
    /**
     * 查询所有客户，通过 findAll()
     */
    @Test
    public void testFindAll() {
        for (Customer customer : customerDao.findAll()) {
            System.out.println(customer);
        }
    }
    
    @Test
    public void testSimpleSpec() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // criteriaBuilder: 构建查询，添加查询方式（所有的查询条件的构建，都由 criteriaBuilder 进行）
                // root：查询的根对象，可以通过 root 获取实体中的属性
                
                // 通过 root 从实体 Customer 对象中获取 custName 属性
                Path<Object> custName = root.get("custName");
                // like：模糊匹配
                Predicate test = criteriaBuilder.like(custName.as(String.class), "Tes%");
                // 返回值类型必须为 Predicate
                return test;
            }
        };
        Optional<Customer> one = customerDao.findOne(spec);
        if (one.isPresent()) {
            System.out.println(one.get());
        }
    }
    
    @Test
    public void testMultiConditionsSpec() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate test = criteriaBuilder.like(custName.as(String.class), "Tes%");
                // 如果是多个条件拼接呢？也是需要借助 criteriaBuilder，同时需要考虑条件之间的关系：并？还是或？
                Predicate itEqual = criteriaBuilder.equal(root.get("custIndustry"), "it");
                // 其实也可以简化，因为 criteriaBuilder.and 方法的入参既可以是 Predicate 类型，也可以是 Expression 类型
                return criteriaBuilder.and(test, itEqual);
            }
        };
        Optional<Customer> one = customerDao.findOne(spec);
        if (one.isPresent()) {
            System.out.println(one.get());
        }
    }
}
