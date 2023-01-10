package top.puppetdev.demo.demo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author puppet
 * @since 2023-01-10 下午 10:18
 */
@Service
public class SubjectService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)  // 让 Spring 去管理事务
    public int insertBatch(String... names) {
        int result = 0;
        jdbcTemplate.update("truncate table subject");
        for (String name : names) {
            result += jdbcTemplate.update("insert into subject(name) value (?)", name);
        }
        return result;
    }

    // 获取所有信息
    public List<Map<String, Object>> subjectList() {
        return jdbcTemplate.queryForList("SELECT * FROM subject");
    }
}
