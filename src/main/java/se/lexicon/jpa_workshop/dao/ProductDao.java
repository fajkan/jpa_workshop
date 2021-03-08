package se.lexicon.jpa_workshop.dao;

import se.lexicon.jpa_workshop.entity.Product;

import java.util.List;

public interface ProductDao {
    // basic CRUD
    Product create(Product product);

    Product findById(int id);

    List<Product> findAll();

    void remove(int id);

    Product merge(Product product);

    List<Product> saveAllProducts(List<Product> products);

    // custom CRUD
    List<Product> findProductByName(String productName);


}
