package com.b2wdigital.offer.repository;

import java.util.*;

import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;

public class ProductRepository {
    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product("1", "p1", Arrays.asList("a", "b"), Arrays.asList("c", "d"),
                Collections.singletonList(new Offer(5.2, "seller5", "sku5", 5))));
        products.add(new Product("2", "p2", Arrays.asList("a1", "b1"), Arrays.asList("c1", "d1"),
                Arrays.asList(new Offer(22, "seller1", "sku1", 10), new Offer(10.2, "seller2", "sku2", 120),
                        new Offer(30, "seller3", "sku3", 105), new Offer(45, "seller4", "sku4", 102))));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public String toString() {
        StringBuilder productString = new StringBuilder().append("Produtos disponiveis:\n");
        for (Product product : products) {
            productString.append("id=").append(product.getId()).append("\n");
        }
        return productString.toString();
    }

    public Optional<Product> findProduct(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Offer> findOffersByProduct(String productId) {
        return Collections.emptyList();
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }
}