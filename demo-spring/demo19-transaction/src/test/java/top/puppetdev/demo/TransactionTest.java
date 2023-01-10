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
import org.springframework.transaction.support.TransactionTemplate;


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

    @Test
    public void testTransactionTemplate() {
        // 定义 JdbcTemplate，用来方便执行数据库增删改查
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // 定义事务管理器，为其指定一个数据源（事务管理器相当于一个人，这个人负责事务的控制操作）
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource);
        // 定义事务属性：TransactionDefinition，该属性用来配置事务的属性信息，例如隔离级别、超时时间、传播方式、是否只读等等
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(10);  // 设置超时时间为：10s
        // 创建 TransactionTemplate 对象
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager, transactionDefinition);
        /*
         通过 TransactionTemplate 提供的方法执行业务操作，主要有2个方法：
           1. executeWithoutResult(Consumer<TransactionStatus> action)
                没有返回值的，需传递一个 Consumer 对象，在 accept 方法中做业务操作
           2. <T> T execute(TransactionCallback<T> action)：
                有返回值的，需要传递一个 TransactionCallback 对象，在 doInTransaction 方法中做业务操作
         调用 execute 方法或者 executeWithoutResult 方法，待其执行完毕之后，事务管理器会自动提交事务或者回滚事务。
         那么什么时候事务会回滚，有 2 种方式：
           1. transactionStatus.setRollbackOnly();  将事务状态标注为回滚状态
           2. execute 方法或者 executeWithoutResult 方法内部抛出异常
         什么时候事务会提交？
         方法没有异常 && 未调用过transactionStatus.setRollbackOnly();
         */
        transactionTemplate.executeWithoutResult((transactionStatus) -> {
            jdbcTemplate.update("insert into subject (name) values (?)", "transactionTemplate-1");
            jdbcTemplate.update("insert into subject (name) values (?)", "transactionTemplate-2");
        });
        System.out.println("after: " + jdbcTemplate.queryForList("SELECT * from subject"));
    }
}
