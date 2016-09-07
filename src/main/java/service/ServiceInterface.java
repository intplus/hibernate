package service;

import model.Order;

/**
 * Created by alpo123 on 9/2/16.
 */
public interface ServiceInterface {

    public void addGoods(int productCategory, Order order);

    public String[][] sale();

    public void orders(Order order);

    public long statusOrder(int order_id);

    public double countTotalPrice(Order order);

    public String[] productsItemsBox();

    public String[] statusItemsBox();

    public String removeChar(String s);

    public void updateService(Order order, int order_id);

    public String [][] getOrder(int order_id);
}
