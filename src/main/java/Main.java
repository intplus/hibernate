import dao.impl.ProductCategoryDAOImpl;
import model.Product;
import model.ProductCategory;
import view.ShopUI;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Map<Product, Integer> order = new HashMap<Product, Integer>() ;
        new ShopUI(order);
//        ProductCategoryDAOImpl pcdi = new ProductCategoryDAOImpl();
//
//        List<ProductCategory> pc = pcdi.getAll();
//        String []productsItems = new String[pc.size()];
//        for(int i = 0; i < pc.size(); ++i) {
//            productsItems[i] = pc.get(i).getTitle();
//        }
//        for(int i = 0; i < productsItems.length; ++i) {
//            System.out.println(productsItems[i]);
//        }

    }
}
