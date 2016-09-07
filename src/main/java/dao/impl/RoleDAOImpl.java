package dao.impl;

import dao.entity.RoleDAO;
import model.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.HibernateUtil;

import java.util.List;

public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO{
    @Override
    public void delete(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("delete from Role where id =: id");
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

    @Override
    public List<Role> getAll() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        Session session = null;
        List<Role> roles = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Role");
            roles = query.list();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return roles;
    }

    @Override
    public Role getById(int id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        Role role = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Role where id =" + id);
            role =(Role) query.uniqueResult();

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return role;
    }

    @Override
    public int insert(Role role) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

        Session session = null;
        int resultId = 0;


        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(role);

            Query query = session.createQuery("from Role");
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
    public void update(Role role) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(role);
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
