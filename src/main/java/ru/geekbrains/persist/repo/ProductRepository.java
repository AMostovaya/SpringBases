package ru.geekbrains.persist.repo;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products;
    private final AtomicLong identityGen;

    public ProductRepository() {
        this.products = new ConcurrentHashMap<>();
        this.identityGen = new AtomicLong(0);;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public void saveProduct(Product product) {
        long id = identityGen.incrementAndGet();
        product.setId(id);
        products.put(id, product);
    }

    public Product findById(long id) {
        return products.get(id);
    }
}


