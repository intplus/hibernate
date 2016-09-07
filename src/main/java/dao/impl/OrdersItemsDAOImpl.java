package dao.impl;

import dao.entity.OrdersItemsDAO;
import model.Order;
import model.OrdersItems;
import org.hibernate.Query;
import org.hibernate.Session;
import service.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * Created by alpo123 on 9/6/16.
 */
public class OrdersItemsDAOImpl extends AbstractDAO<OrdersItems> implements OrdersItemsDAO {
    @Override
    public void delete(int id) {

        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("delete from OrdersItems where id =: id");
            query.setParameter("id", id);
//            int result = query.executeUpdate();


            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void delete(OrdersItems ordersItems) {

        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(ordersItems);
            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<OrdersItems> getAll() {

        Session session = null;
        List<OrdersItems> ordersItemses = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            ordersItemses = session.createCriteria(OrdersItems.class).list();
        } catch(Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return ordersItemses;
    }

    @Override
    public OrdersItems getById(int id) {

        Session session = null;
        OrdersItems ordersItems = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            ordersItems = (OrdersItems) session.load(OrdersItems.class, id);
        } catch(Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return ordersItems;
    }

    @Override
    public int insert(OrdersItems ordersItems) {
        Session session = null;
        int resultId = 0;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(ordersItems);

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return resultId;
    }

    @Override
    public void update(OrdersItems ordersItems) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(ordersItems);

            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
