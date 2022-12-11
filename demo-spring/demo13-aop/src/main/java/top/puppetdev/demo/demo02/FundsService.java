package top.puppetdev.demo.demo02;

/**
 * @author puppet
 * @since 2022-12-12 上午 01:00
 */
public class FundsService {
    /**
     * 账户余额
     */
    private double balance = 1000;

    /**
     * 模拟充值
     */
    public double recharge(String username, double price) {
        System.out.printf("%s提现%s%n", username, price);
        balance += price;
        return balance;
    }

    /**
     * 模拟提现
     */
    public double withdrawal(String username, double price) {
        if (balance < price) {
            throw new RuntimeException("余额不足!");
        }
        System.out.printf("%s提现%s%n", username, price);
        balance -= price;
        return balance;
    }

    /**
     * 获取余额
     */
    public double getBalance(String username) {
        return balance;
    }
}
