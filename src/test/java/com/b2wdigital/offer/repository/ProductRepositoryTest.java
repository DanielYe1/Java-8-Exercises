package com.b2wdigital.offer.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;

public class ProductRepositoryTest {

    @Test
    public void deveria_encontrar_o_produto() {
        ProductRepository repository = new ProductRepository();
        Product p1 = new Product("1", "p1", Arrays.asList("a", "b"), Arrays.asList("c", "d"),
                Arrays.asList(new Offer(5.2, "seller1", "sku1", 5)));
        assertThat(repository.findProduct("1").map(Product::getId).orElse(""), equalTo(p1.getId()));
    }

    @Test
    public void nao_deveria_encontrar_o_produto() {
        ProductRepository repository = new ProductRepository();
        assertThat(repository.findProduct("3").map(Product::getId).orElse(""), equalTo(""));
    }


}
