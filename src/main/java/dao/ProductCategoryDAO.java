package dao;

import java.util.List;

import model.ProductCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
@Deprecated
public class ProductCategoryDAO implements ProductCategoryDAOInterface<ProductCategory, String> {
    private Session currentSession;
    private Transaction currentTransaction;

    public ProductCategoryDAO() {
    }
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(ProductCategory entity) {
        getCurrentSession().save(entity);
    }

    public void update(ProductCategory entity) {
        getCurrentSession().update(entity);
    }

    public ProductCategory findById(String id) {
        ProductCategory productCategory = (ProductCategory) getCurrentSession().get(ProductCategory.class, id);
        return productCategory;
    }

    public void delete(ProductCategory entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCategory> findAll() {
        List<ProductCategory> productCategories = (List<ProductCategory>) getCurrentSession().createQuery("from ProductCategory").list();
        return productCategories;
    }

    public void deleteAll() {
        List<ProductCategory> entityList = findAll();
        for (ProductCategory entity : entityList) {
            delete(entity);
        }
    }

}
