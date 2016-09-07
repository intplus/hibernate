package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;
@Deprecated
public class ProductService {

    private static ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public void persist(Product entity) {
        productDAO.openCurrentSessionwithTransaction();
        productDAO.persist(entity);
        productDAO.closeCurrentSessionwithTransaction();
    }

    public void update(Product entity) {
        productDAO.openCurrentSessionwithTransaction();
        productDAO.update(entity);
        productDAO.closeCurrentSessionwithTransaction();
    }

    public Product findById(String id) {
        productDAO.openCurrentSession();
        Product product = productDAO.findById(id);
        productDAO.closeCurrentSession();
        return product;
    }

    public void delete(String id) {
        productDAO.openCurrentSessionwithTransaction();
        Product product = productDAO.findById(id);
        productDAO.delete(product);
        productDAO.closeCurrentSessionwithTransaction();
    }

    public List<Product> findAll() {
        productDAO.openCurrentSession();
        List<Product> products = productDAO.findAll();
        productDAO.closeCurrentSession();
        return products;
    }

    public void deleteAll() {
        productDAO.openCurrentSessionwithTransaction();
        productDAO.deleteAll();
        productDAO.closeCurrentSessionwithTransaction();
    }

    public ProductDAO productDAO() {
        return productDAO;
    }
}
