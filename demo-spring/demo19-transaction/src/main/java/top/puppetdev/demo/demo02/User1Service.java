package top.puppetdev.demo.demo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author puppet
 * @since 2023/1/16 00:05
 */
@Service
public class User1Service {
    private final JdbcTemplate jdbcTemplate;

    public User1Service(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void required(String name) {
        this.jdbcTemplate.update("insert into user1(name) VALUE (?)", name);
    }
}
