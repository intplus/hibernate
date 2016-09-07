package service;

import dao.ProductCategoryDAO;
import model.ProductCategory;

import java.util.List;
@Deprecated
public class ProductCategoryService {

    private static ProductCategoryDAO productCategoryDAO;

    public ProductCategoryService() {
        productCategoryDAO = new ProductCategoryDAO();
    }

    public void persist(ProductCategory entity) {
        productCategoryDAO.openCurrentSessionwithTransaction();
        productCategoryDAO.persist(entity);
        productCategoryDAO.closeCurrentSessionwithTransaction();
    }

    public void update(ProductCategory entity) {
        productCategoryDAO.openCurrentSessionwithTransaction();
        productCategoryDAO.update(entity);
        productCategoryDAO.closeCurrentSessionwithTransaction();
    }

    public ProductCategory findById(String id) {
        productCategoryDAO.openCurrentSession();
        ProductCategory productCategory = productCategoryDAO.findById(id);
        productCategoryDAO.closeCurrentSession();
        return productCategory;
    }

    public void delete(String id) {
        productCategoryDAO.openCurrentSessionwithTransaction();
        ProductCategory productCategory = productCategoryDAO.findById(id);
        productCategoryDAO.delete(productCategory);
        productCategoryDAO.closeCurrentSessionwithTransaction();
    }

    public List<ProductCategory> findAll() {
        productCategoryDAO.openCurrentSession();
        List<ProductCategory> productCategories = productCategoryDAO.findAll();
        productCategoryDAO.closeCurrentSession();
        return productCategories;
    }

    public void deleteAll() {
        productCategoryDAO.openCurrentSessionwithTransaction();
        productCategoryDAO.deleteAll();
        productCategoryDAO.closeCurrentSessionwithTransaction();
    }

    public ProductCategoryDAO productCategoryDAO() {
        return productCategoryDAO;
    }
}

