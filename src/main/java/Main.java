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

    }
}
