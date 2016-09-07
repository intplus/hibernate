package model;


import javax.persistence.*;

@Entity
@Table(name = "orders_items")
public class OrdersItems extends Model {

    private static final long serialVersionUID = -7079714758815860180L;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "qnt")
    private int quantity;

    public OrdersItems() {
    }

    public OrdersItems(Long id) {
        super(id);
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
