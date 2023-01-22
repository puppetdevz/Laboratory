package top.puppetdev.demo.demo02;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puppet
 * @since 2023/1/16 00:06
 */
@Service
public class User2Service {

    private final JdbcTemplate jdbcTemplate;

    public User2Service(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void required(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUE (?)", name);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void requiredAndWillThrowException(String name) {
        this.jdbcTemplate.update("insert into user2(name) VALUE (?)", name);
        throw new RuntimeException();
    }
}
