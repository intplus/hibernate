package service;

import dao.impl.*;
import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class Service implements ServiceInterface {

    @Override
    public void addGoods(int productCat, Order order) {

    }

    @Override
    public double countTotalPrice(Order order) {
        return 0;
    }

    @Override
    public String[][] getOrder(int order_id) {

        String [][] items = {};
        StatusOrdersDAOImpl sodi = new StatusOrdersDAOImpl();
        OrderDAOImpl odi = new OrderDAOImpl();

        Order order = odi.getById(order_id);
        long i = order.getStatus().getId();
//        return sodi.getById((int) i);
//        List<ProductCategory> pc = null;
//
//        OrderDAOImpl odi = new OrderDAOImpl();
//        odi.getById(order_id);
//        int size = odi.getAll().size();
//        items = new String[5][size];
//
//        OrdersItemsDAOImpl oidi = new OrdersItemsDAOImpl();
//        OrdersItems oi = oidi.getById(order_id);


//        for(int i = 0; i < size; ++i) {
//            items[0][i] =
//        }




        return items;
    }

    public void makeOrder(Customer customer, List<Product> goods, List<Integer> quantitys, List<Long> idGoods, BigDecimal totalSumm, int statusId) {

        StatusOrdersDAOImpl sodi = new StatusOrdersDAOImpl();
        Status status = sodi.getById(statusId);

        OrdersItemsDAOImpl oidi = new OrdersItemsDAOImpl();
        OrdersItems oi = new OrdersItems();
        OrderDAOImpl odi = new OrderDAOImpl();
        Order order = new Order();

        BigDecimal totalPrice = new BigDecimal(0);

        order.setCustomer(customer);
        order.setStatus(status);
        order.setDate(new Date());

        order.setPrice(totalSumm);

        for (int i = 0; i < goods.size(); ++i) {
            oi.setProduct(goods.get(i));
            oi.setQuantity(quantitys.get(i));
            oi.setOrder(order);
            oidi.insert(oi);
        }

    }

    public void createStatus(Status status) {
        StatusOrdersDAOImpl sodi = new StatusOrdersDAOImpl();
        sodi.insert(status);
    }

    @Override
    public void orders(Order order) {
//        OrderDAOImpl odi = new OrderDAOImpl();
//        odi.insert(order);

    }

    @Override
    public String removeChar(String s) {
        return null;
    }

    public long statusOrder(int order_id){
        String [][] items = {};
        StatusOrdersDAOImpl sodi = new StatusOrdersDAOImpl();
        OrderDAOImpl odi = new OrderDAOImpl();

        Order order = odi.getById(order_id);
//        System.err.println(order.getStatus().getId());

        return order.getStatus().getId();

    }

    public List<OrdersItems> getProductsForOrder(int id_order) {

        OrdersItemsDAOImpl oidi = new OrdersItemsDAOImpl();

        List <OrdersItems> ordersItemses = oidi.getAll();
        List<OrdersItems> resultList = new ArrayList<OrdersItems>();
        int count = 0;

        for(int i = 0; i < ordersItemses.size(); ++i) {
            Long x = ordersItemses.get(i).getOrder().getId();
            if (x == id_order) {
                System.err.println(ordersItemses.get(i).getProduct().getTitle());
                resultList.add(ordersItemses.get(i));
                count++;
            }
        }
        System.err.println(count);

        return resultList;
    }

    public String getPhoneByOrder(int order_id) {
        OrderDAOImpl odi = new OrderDAOImpl();
        return odi.getById(order_id).getCustomer().getPhone();
    }

    public String getTotalPrice(int order_id) {
        OrderDAOImpl odi = new OrderDAOImpl();
        return odi.getById(order_id).getPrice().toString();
    }

    public void updateAll(int order_id, String[] customers, List<Product> products, List<Integer> quantitys, int statusId) {
        OrderDAOImpl odi = new OrderDAOImpl();
        CustomerDAOImpl cdi = new CustomerDAOImpl();

        Customer customer = odi.getById(order_id).getCustomer();
        customer.setName(customers[0]);
        customer.setLastname(customers[1]);
        customer.setPhone(customers[2]);
        cdi.update(customer);

        StatusOrdersDAOImpl sodi = new StatusOrdersDAOImpl();
        Status status = sodi.getById(statusId);

        Order order = odi.getById(order_id);

        int count = products.size();
        int summ = 0;

        order.setStatus(status);
//        order.setDate(new Date());
        for (int i = 0; i < count; ++i) {
            summ += products.get(i).getPrice().intValue() * quantitys.get(i);
        }
        order.setPrice(new BigDecimal(summ));
        order.setCustomer(customer);
        odi.update(order);

        OrdersItemsDAOImpl oidi = new OrdersItemsDAOImpl();
        OrdersItems oi = new OrdersItems();
        List<OrdersItems> list = new ArrayList<OrdersItems>();
        List<OrdersItems> rez = new ArrayList<OrdersItems>();
        list = oidi.getAll();
        for(int i = 0; i < count; ++i) {
            if(order_id == list.get(i).getOrder().getId()) {
                rez.add(list.get(i));
            }
        }
        for(int i = 0; i < rez.size(); ++i) {
            rez.get(i).setQuantity(quantitys.get(i));
            rez.get(i).setProduct(products.get(i));

            oidi.update(rez.get(i));
        }


    }


    @Override
    public String[][] sale() {

        String[][] data = null;

        OrderDAOImpl odi = new OrderDAOImpl();
        OrdersItemsDAOImpl oidi = new OrdersItemsDAOImpl();

        int totalColumn = odi.getAll().size();

        data = new String[totalColumn][6];
        List<Order> orders = odi.getAll();
        List<OrdersItems> ordersItemses = oidi.getAll();

        for(int i = 0; i < totalColumn; i++) {
//            if(ordersItemses.get(9) == null) break;
            data[i][0] = String.valueOf(orders.get(i).getId());
            data[i][1] = String.valueOf(orders.get(i).getDate());
            data[i][2] = orders.get(i).getCustomer().getName();
            data[i][3] = orders.get(i).getCustomer().getLastname();
            data[i][4] = String.valueOf(orders.get(i).getPrice());
            data[i][5] = String.valueOf(orders.get(i).getStatus().getDescription());
        }

        return data;
    }

    @Override
    public String[] statusItemsBox() {

        StatusOrdersDAOImpl sodi = new StatusOrdersDAOImpl();

        List<Status> status = sodi.getAll();
        String []statusItems = new String[status.size()];
        for(int i = 0; i < status.size(); ++i) {
            statusItems[i] = status.get(i).getDescription();
        }

        return statusItems;
    }

    @Override
    public String[] productsItemsBox() {

        ProductCategoryDAOImpl pcdi = new ProductCategoryDAOImpl();

        List<ProductCategory> pc = pcdi.getAll();
        String []productsItems = new String[pc.size()];
        for(int i = 0; i < pc.size(); ++i) {
            productsItems[i] = pc.get(i).getTitle();
        }

        return productsItems;
    }

    @Override
    public void updateService(Order order, int order_id) {

    }


    public List<Product> getProducts(int category) {
        ProductDAOImpl pdi = new ProductDAOImpl();
        return pdi.getAll(category);
    }
    public void createCustomer(String name, String surname, String phone) {
        CustomerDAOImpl cdi = new CustomerDAOImpl();
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLastname(surname);
        customer.setPhone(phone);
        cdi.insert(customer);

    }

    public Product productGetById(int id) {
        ProductDAOImpl pdi = new ProductDAOImpl();
        Product product = pdi.getById(id);
        return product;
    }
    public void deleteCustomer(int id) {
        CustomerDAOImpl cdi = new CustomerDAOImpl();
        cdi.delete(id);
    }
    public void createRole(String roleType) {
        RoleDAOImpl rdi = new RoleDAOImpl();
        Role role = new Role();
        role.setTitle(roleType);
        rdi.insert(role);
    }
    public void createProduct(String title, String description, BigDecimal price){

        ProductDAOImpl pdi = new ProductDAOImpl();
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        ProductCategory pc = new ProductCategory();
        Set<Product> products = new HashSet<Product>();
        products.add(product);
//        pc.setProducts(products);
        product.setProductCategory(pc);

        pdi.insert(product);
    }
    public void createProductCategory(String title, Set<Product> products, ProductCategory pcat) {
        ProductCategoryDAOImpl pcdi = new ProductCategoryDAOImpl();
        ProductCategory pc = new ProductCategory();
        pc.setTitle(title);
        pc.setProducts(products);
//        pc.setParentProductCategory(pcat);
        pcdi.insert(pc);
    }

}
