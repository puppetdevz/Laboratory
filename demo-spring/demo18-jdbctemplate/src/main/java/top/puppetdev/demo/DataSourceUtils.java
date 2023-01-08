package top.puppetdev.demo;

import javax.sql.DataSource;

/**
 * @author puppet
 * @since 2023-01-08 下午 08:37
 */
public class DataSourceUtils {
    public static DataSource getDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        /*
            MySQL 8 之前的驱动：com.mysql.jdbc.Driver
            MySQL 8 及其之后的驱动：com.mysql.cj.jdbc.Driver：
        */
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo?characterEncoding=UTF-8");
        dataSource.setUsername("demo");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
        return dataSource;
    }
}
