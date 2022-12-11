package top.puppetdev.demo.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author puppet
 * @since 2022/3/31 14:00
 */
public class JPAUtils {
    private static EntityManagerFactory factory;
    
    static {
        factory = Persistence.createEntityManagerFactory("demoJpa");
    }
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
