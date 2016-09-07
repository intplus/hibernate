package dao.impl;import dao.entity.OrderDAO;import model.Order;import model.ProductCategory;import org.hibernate.Criteria;import org.hibernate.Query;import org.hibernate.Session;import org.hibernate.SessionFactory;import org.hibernate.criterion.Restrictions;import service.HibernateUtil;import javax.swing.*;import java.util.List;public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {    @Override    public void delete(int id) {//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();//        Session session = sessionFactory.openSession();        Session session = null;        try{            session = HibernateUtil.getSessionFactory().openSession();            session.beginTransaction();            Query query = session.createQuery("delete from Order where id =: id");            query.setParameter("id", id);//            int result = query.executeUpdate();            session.getTransaction().commit();        } catch(Exception e) {            session.getTransaction().rollback();            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);        } finally {            if (session != null && session.isOpen()) {                session.close();            }        }    }    public void delete(Order order) {        Session session = null;        try{            session = HibernateUtil.getSessionFactory().openSession();            session.beginTransaction();            session.delete(order);            session.getTransaction().commit();        } catch(Exception e) {            session.getTransaction().rollback();            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);        } finally {            if (session != null && session.isOpen()) {                session.close();            }        }    }    @Override    public List<Order> getAll() {//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();//        Session session = sessionFactory.openSession();        Session session = null;        List<Order> orders = null;        try{            session = HibernateUtil.getSessionFactory().openSession();            orders = session.createCriteria(Order.class).list();        } catch(Exception e) {            session.getTransaction().rollback();            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);        } finally {            if (session != null && session.isOpen()) {                session.close();            }        }        return orders;    }    @Override    public Order getById(int id) {//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();//        Session session = sessionFactory.openSession();        Session session = null;        Order order = null;        try{            session = HibernateUtil.getSessionFactory().openSession();            order = (Order) session.load(Order.class, id);        } catch(Exception e) {            session.getTransaction().rollback();            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);        } finally {            if (session != null && session.isOpen()) {                session.close();            }        }        return order;    }    @Override    public int insert(Order order) {//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();//        Session session = sessionFactory.openSession();        Session session = null;        int resultId = 0;        try{            session = HibernateUtil.getSessionFactory().openSession();            session.beginTransaction();            session.save(order);//            Query query = session.createQuery("from Order");//            resultId = query.list().size();            session.getTransaction().commit();        } catch(Exception e) {            session.getTransaction().rollback();            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);        } finally {            if (session != null && session.isOpen()) {                session.close();            }        }        return resultId;    }    @Override    public void update(Order order) {//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();//        Session session = sessionFactory.openSession();        Session session = null;        try{            session = HibernateUtil.getSessionFactory().openSession();            session.beginTransaction();            session.update(order);//            Query query = session.createQuery("update Order set product_id = :product_id, status_id = :status_id, customer_id = :customer_id, count = :count, price = :price");//            query.setParameter("product_id", order.getProducts());//            query.setParameter("status_id", order.getStatus());//            query.setParameter("customer_id", order.getCustomer());//            query.setParameter("count", order.getCount());//            query.setParameter("price", order.getPrice());            session.getTransaction().commit();        } catch(Exception e) {            session.getTransaction().rollback();            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);        } finally {            if (session != null && session.isOpen()) {                session.close();            }        }    }}