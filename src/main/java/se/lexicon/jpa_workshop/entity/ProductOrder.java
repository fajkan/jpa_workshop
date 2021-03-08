package se.lexicon.jpa_workshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate orderDate;
    private LocalTime orderTime;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "productOrder",
            orphanRemoval = true // jpa provide plan to remove when the child entity is removed from the collection
    )
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "app_user_id")
    private AppUser customer;

    public ProductOrder() {
    }

    public ProductOrder(LocalDate orderDate, LocalTime orderTime, AppUser customer) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.customer = customer;
    }

    public ProductOrder(LocalDate orderDate, LocalTime orderTime, List<OrderItem> orderItems, AppUser customer) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    public ProductOrder(int id, LocalDate orderDate, LocalTime orderTime, List<OrderItem> orderItems, AppUser customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    public ProductOrder(int id, LocalDate orderDate, LocalTime orderTime, AppUser customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.customer = customer;
    }

    /**
     * Convince method
     * Adds in a bidirectional wau
     * Adds orderItem to ProductOrder and productOrder to OrderItem
     *
     * @param orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }

        orderItems.add(orderItem);
        orderItem.setProductOrder(this);
    }

    /**
     * Convince method
     * Removes in a bidirectional wau
     * Removes orderItem to ProductOrder and productOrder to OrderItem
     *
     * @param orderItem
     */
    public void removeOrderItem(OrderItem orderItem) {
        if (orderItems != null) {
            orderItems = new ArrayList<>();
        }
        if (orderItem == null) throw new IllegalArgumentException("orderItem is null");

        orderItems.remove(orderItem); // false
        orderItem.setProductOrder(null);
    }

    public double calculateOrderItemsTotalPrice() {
        //return orderItems.stream().mapToDouble(OrderItem::calculateOrderItemPrice).sum();

        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.calculateOrderItemPrice();
        }
        return totalPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return id == that.id && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderTime, that.orderTime) && Objects.equals(orderItems, that.orderItems) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, orderTime, orderItems, customer);
    }
}
