package dao.impl;

import dao.entity.ProductCategoryDAO;
import model.ProductCategory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import service.HibernateUtil;

import java.util.List;

public class ProductCategoryDAOImpl extends AbstractDAO<ProductCategory> implements ProductCategoryDAO {
    @Override
    public void delete(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("delete from ProductCategory where id = :id");
            query.setParameter("id", id);
//            int result = query.executeUpdate();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
//            sessionFactory.close();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        Session session = null;
        List<ProductCategory> productCategories = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
//            Query query1 = session.createSQLQuery("SELECT title FROM product_category;");
            Query query = session.createQuery("from ProductCategory");
            productCategories = query.list();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return productCategories;
    }

    @Override
    public ProductCategory getById(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        ProductCategory productCategory = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            /*
            Session session = sessionFactory.openSession();
            Criteria userCriteria = session.createCriteria(User.class);
            userCriteria.add(Restrictions.eq("login", login));
            user = (User) userCriteria.uniqueResult();
            session.close();

             */

            /*
            bus = (Bus) session.load(Bus.class, bus_id);
             */

            Criteria userCriteria = session.createCriteria(ProductCategory.class);
            userCriteria.add(Restrictions.eq("id", Long.valueOf(id)));
            productCategory = (ProductCategory) userCriteria.uniqueResult();

//            Query query = session.createQuery("from ProductCategory where id =" + id);
//            productCategory =(ProductCategory) query.uniqueResult();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return productCategory;
    }

    @Override
    public int insert(ProductCategory productCategory) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        int resultId = 0;


        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(productCategory);

            Query query = session.createQuery("from ProductCategory ");
            resultId = query.list().size();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return resultId;
    }

    @Override
    public void update(ProductCategory productCategory) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(productCategory);
            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
