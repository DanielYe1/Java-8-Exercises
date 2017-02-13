package com.b2wdigital.offer;

import java.util.Arrays;
import java.util.Optional;

import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;

public class OfferApplication {

    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        Arrays.asList(args).forEach(arg -> {
            Optional<Product> foundProduct = repository.findProduct(arg);
            String message = foundProduct.map(Product::toString).orElse("Nao encontrado");
            System.out.println(message);

        });

    }

}
