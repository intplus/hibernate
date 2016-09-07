package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_category")
public class ProductCategory extends Model{

    private static final long serialVersionUID = 6304327532251500141L;

    @Column(name = "title")
    private String title;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "parent_product_category", referencedColumnName = "id")
//    private ProductCategory parentProductCategory;

    @OneToMany(mappedBy = "productCategory")
    private Set<Product> products = new HashSet<Product>();

    public ProductCategory() {
    }

    public ProductCategory(Long id) {
        super(id);
    }

//    public ProductCategory getParentProductCategory() {
//        return parentProductCategory;
//    }
//
//    public void setParentProductCategory(ProductCategory parentProductCategory) {
//        this.parentProductCategory = parentProductCategory;
//    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
