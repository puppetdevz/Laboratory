package top.puppetdev.demo.service.impl;

import top.puppetdev.demo.dao.IAccountMapper;
import top.puppetdev.demo.domain.Account;
import top.puppetdev.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层账户的实现类
 * @author Jack
 * @date 2020/11/09
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountMapper accountMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Account> queryAll() {
        System.out.println("业务层：查询所有的方法执行了");
        List<Account> accounts = accountMapper.queryAll();
        return accounts;
    }

    /**
     * 保存账户
     *
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存账户的方法执行了。。");
    }
}
