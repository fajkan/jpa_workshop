package se.lexicon.jpa_workshop.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshop.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Product create(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        Query query = entityManager.createQuery("select p from Product p");
        List<Product> result = query.getResultList();
        return result;
    }

    @Override
    public void remove(int id) {
        Product result = findById(id);
        if (result != null) {
            entityManager.remove(result);
        }
    }

    @Override
    public Product merge(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        for (Product product : products) {
            create(product);
        }
        return products;
    }

    @Override
    public List<Product> findProductByName(String productName) {
        Query query = entityManager.createQuery("select p from Product p where p.name = :pn");
        query.setParameter("pn", productName);
        List<Product> result = query.getResultList();
        return result;
    }
}
