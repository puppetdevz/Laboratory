package top.puppetdev.demo.controller;

import top.puppetdev.demo.domain.Account;
import top.puppetdev.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 表现层Demo
 *
 * @author Jack
 * @date 2020/11/09
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IAccountService accountService;

    /**
     * 表现层：查询所有
     *
     * @return
     */
    @RequestMapping("/testQueryAll")
    public String testQueryAll() {
        System.out.println("表现层：查询所有的方法执行了！");
        List<Account> accounts = accountService.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        return "success";
    }

    /**
     * 表现层：保存账户
     *
     * @return
     */
    @RequestMapping("/testSaveAccount")
    public String testSaveAccount() {
        System.out.println("表现层：保存账户方法执行了！");
        return "success";
    }
}
