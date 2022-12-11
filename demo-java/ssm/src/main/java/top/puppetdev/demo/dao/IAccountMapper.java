package top.puppetdev.demo.dao;

import top.puppetdev.demo.domain.Account;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的dao接口
 * @author Jack
 * @date 2020/11/06
 */
@Repository("accountMapper")
public interface IAccountMapper {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from account")
    List<Account> queryAll();

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);
}
