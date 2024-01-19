package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public abstract class Repository<T> {

    protected StandardServiceRegistry registry;

    protected SessionFactory sessionFactory;

    protected Session session;

    public Repository() {
        if (registry == null) {
            registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
    }
    public abstract boolean create(T o);

    public abstract void endSession();

}
