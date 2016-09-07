package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends Model{

    private static final long serialVersionUID = -695323610975164466L;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers = new HashSet<Customer>();

    public Role() {
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
