package dao.impl;

import dao.entity.StatusOrdersDAO;
import model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.HibernateUtil;

import java.util.List;


/**
 * Created by alpo123 on 9/2/16.
 */
public class StatusOrdersDAOImpl extends AbstractDAO<Status> implements StatusOrdersDAO {
    @Override
    public void delete(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("delete from Status where id =: id");
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
        }
    }
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Status> getAll() {
        /*
        Transaction tx = session.beginTransaction();
SQLQuery query = session.createSQLQuery("select emp_id, emp_name, emp_salary from Employee");
List<Object[]> rows = query.list();
         */

//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        List<Status> statuses = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Status");
            statuses = query.list();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return statuses;
    }

    @Override
    public Status getById(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        Status status = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Status where id =" + id);
            status =(Status) query.uniqueResult();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return status;
    }

    @Override
    public int insert(Status status) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        int resultId = 0;

        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(status);

            Query query = session.createQuery("from Status");
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
    public void update(Status status) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(status);
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
