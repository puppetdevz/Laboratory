package top.puppetdev.demo;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * @author puppet
 * @since 2023-01-10 上午 01:41
 */
public class TransactionTest {
    private DataSource dataSource;

    @Before
    public void init() {
        dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo?characterEncoding=UTF-8");
        dataSource.setUsername("demo");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
    }

    @Test
    public void testPlatformTransactionManager() {
        // 定义 JdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // 定义事务管理器，为其指定一个数据源（事务管理器相当于一个人，这个人负责事务的控制操作）
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource);
        // 定义事务属性：TransactionDefinition，该属性用来配置事务的属性信息，例如隔离级别、超时时间、传播方式、是否只读等等
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        // 开启事务，调用 platformTransactionManager.getTransaction 开启事务操作，得到事务状态对象：TransactionStatus
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        // 执行业务操作：两个插入操作
        try {
            System.out.println("before: " + jdbcTemplate.queryForList("select * from subject"));
            jdbcTemplate.update("insert into subject(name) value (?)", "test-1");
            jdbcTemplate.update("insert into subject(name) value (?)", "test-2");
            // 提交事务：platformTransactionManager.commit
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            // 回滚事务：platformTransactionManager.rollback
            platformTransactionManager.rollback(transactionStatus);
        }
        System.out.println("after: " + jdbcTemplate.queryForList("select * from subject"));
    }
}
