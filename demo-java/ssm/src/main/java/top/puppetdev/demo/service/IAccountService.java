package top.puppetdev.demo.service;

import top.puppetdev.demo.domain.Account;

import java.util.List;

/**
 * 账户业务层接口
 * @author Jack
 * @date 2020/11/06
 */
public interface IAccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> queryAll();

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);
}
