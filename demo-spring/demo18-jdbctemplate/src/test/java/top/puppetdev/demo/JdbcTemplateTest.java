package top.puppetdev.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author puppet
 * @since 2023-01-08 下午 08:50
 */
public class JdbcTemplateTest {

    @Test
    public void testJdbcTemplateSimpleUsage() {
        // 创建数据源
        DataSource dataSource = DataSourceUtils.getDataSource();
        // 创建 JdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // 调用 JdbcTemplate 方法操作数据库
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select  * from subject");
        System.out.println(maps);
    }

    @Test
    public void testUpdateWithNoArgs() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int update = jdbcTemplate.update("insert into subject(name) value ('maven')");
        System.out.println("number of rows affected is " + update);
    }

    @Test
    public void testUpdateWithPlaceholder() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int update = jdbcTemplate.update("insert into subject(name) value (?)", "mybatis");
        System.out.println("number of rows affected is " + update);
    }

    @Test
    public void testUpdateWithPreparedStatementSetter() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int update = jdbcTemplate.update("insert into subject(name) value (?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "netty");
            }
        });
        System.out.println("number of rows affected is " + update);
    }

    @Test
    public void test() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "insert into subject (name) value (?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 手动创建 PreparedStatement，注意第二个参数：Statement.RETURN_GENERATED_KEYS
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, "获取自增列的值");
                return ps;
            }
        }, keyHolder);
        System.out.println("new record id: " + Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    @Test
    public void testBatchInsert() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Object[]> list = Stream.of("刘德华", "郭富城", "张学友", "黎明").map(item -> new Object[]{item}).toList();
        int[] ints = jdbcTemplate.batchUpdate("insert into subject(name) values (?)", list);
    }

    @Test
    public void testQuerySingleRow() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String name = jdbcTemplate.queryForObject("select name from subject where id = ?", String.class, 1);
        System.out.println(name);
    }

    @Test
    public void testQuerySingleRowWithNoResult() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Assert.assertThrows(EmptyResultDataAccessException.class,
                () -> jdbcTemplate.queryForObject("select name from subject where id = ?", String.class, 0));
    }

    @Test
    public void testQueryMultipleRow() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // <T> List<T> queryForList(String sql, Class<T> elementType);
        List<String> list1 = jdbcTemplate.queryForList("select name from subject where id > 1", String.class);
        System.out.println("list1: " + list1);

        // <T> List<T> queryForList(String sql, Class<T> elementType, @Nullable Object... args);
        List<String> list2 = jdbcTemplate.queryForList("select name from subject where id > ?", String.class, 2);
        System.out.println("list2: " + list2);

        // <T> List<T> queryForList(String sql, Object[] args, Class<T> elementType);
        List<String> list3 = jdbcTemplate.queryForList("select name from subject where id > ?", new Object[]{1}, String.class);
        System.out.println("list3: " + list3);

        // <T> List<T> queryForList(String sql, Object[] args, int[] argTypes, Class<T> elementType);
        List<String> list4 = jdbcTemplate.queryForList("select name from subject where id > ?", new Object[]{2}, new int[]{java.sql.Types.INTEGER}, String.class);
        System.out.println("list4: " + list4);
    }

    @Test
    public void testResultMapToCustomObject() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id, name from subject where id = ?";
        //查询 id 为 2 的科目信息
        Subject subject = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Subject subject1 = new Subject();
            subject1.setId(rs.getInt(1));
            subject1.setName(rs.getString(2));
            return subject1;
        }, 2);
        System.out.println(subject);
    }

    @Test
    public void testResultMapToJavaBean() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id, name from subject where id = ?";
        //查询 id 为 1 的用户信息
        RowMapper<Subject> rowMapper = new BeanPropertyRowMapper<>(Subject.class);
        Subject subject = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(subject);
    }

    @Test
    public void testResultMapToMap() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id, name from subject where id > ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, 1);
        System.out.println(maps);
    }

    @Test
    public void testMultipleRowsMapToObject() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id, name from subject where id > ?";
        List<Subject> maps = jdbcTemplate.query(sql, new RowMapper<Subject>() {
            @Override
            public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
                Subject subject = new Subject();
                subject.setId(rs.getInt(1));
                subject.setName(rs.getString(2));
                return subject;
            }
        }, 1);
        System.out.println(maps);
    }

    @Test
    public void testMultipleRowsMapToJavaBean() {
        DataSource dataSource = DataSourceUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select id, name from subject where id > ?";
        List<Subject> maps = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Subject>(Subject.class), 6);
        System.out.println(maps);
    }
}
