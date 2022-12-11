package cn.jackdev.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 业务层AccountService测试类
 * @author Jack
 * @date 2020/11/09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:application.xml"})
public class AccountServiceTest {
    @Autowired
    private IAccountService accountService;

    @Test
    public void testQueryAll() {
        accountService.queryAll();
    }
}
