package top.puppetdev.demo.demo02;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puppet
 * @since 2023/1/16 00:45
 */
@Service
public class TxService {
    private final User1Service user1Service;

    private final User2Service user2Service;

    public TxService(User1Service user1Service, User2Service user2Service) {
        this.user1Service = user1Service;
        this.user2Service = user2Service;
    }

    public void noTransactionExceptionRequiredRequired() {
        this.user1Service.required("张三");
        this.user2Service.required("李四");
        throw new RuntimeException();
    }

    public void noTransactionExceptionRequiredRequiredException() {
        this.user1Service.required("张三");
        this.user2Service.requiredAndWillThrowException("李四");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void transactionExceptionRequiredRequired() {
        user1Service.required("张三");
        user2Service.required("李四");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void transactionRequiredRequiredException() {
        user1Service.required("张三");
        user2Service.requiredAndWillThrowException("李四");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void transactionRequiredRequiredExceptionTry() {
        user1Service.required("张三");
        try {
            user2Service.requiredAndWillThrowException("李四");
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

}
