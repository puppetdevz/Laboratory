package top.puppetdev.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.puppetdev.demo.dao.entity.Customer;

/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本 CRUD 操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 * @author puppet
 * @since 2022/4/14 17:20
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

}
