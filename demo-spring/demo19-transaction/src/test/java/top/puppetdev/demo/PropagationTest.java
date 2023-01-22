package top.puppetdev.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import top.puppetdev.demo.demo02.MainConfig;
import top.puppetdev.demo.demo02.TxService;

/**
 * @author puppet
 * @since 2023/1/16 00:48
 */
public class PropagationTest {

    private TxService txService;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        txService = context.getBean(TxService.class);
        jdbcTemplate = context.getBean(JdbcTemplate.class);
        // 每次运行前清理一下表数据
        jdbcTemplate.update("truncate table user1");
        jdbcTemplate.update("truncate table user2");
    }

    @After
    public void after() {
        System.out.println("the data of user1 table: " + jdbcTemplate.queryForList("select * from user1"));
        System.out.println("the data of user2 table: " + jdbcTemplate.queryForList("select * from user2"));
    }

    @Test
    public void test_no_transaction_exception_required_required() {
        txService.noTransactionExceptionRequiredRequired();
    }

    @Test
    public void test_no_transaction_required_required_exception() {
        txService.noTransactionExceptionRequiredRequiredException();
    }

    @Test
    public void test_transaction_exception_required_required() {
        txService.transactionExceptionRequiredRequired();
    }

    @Test
    public void test_transaction_required_required_exception() {
        txService.transactionRequiredRequiredException();
    }

    @Test
    public void test_transaction_required_required_exception_try() {
        txService.transactionRequiredRequiredExceptionTry();
    }
}
