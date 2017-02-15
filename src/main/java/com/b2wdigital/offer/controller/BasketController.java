package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasketController {

    private ProductRepository repository;

    public BasketController(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Offer> getOffersByProductId(String productId) {
        return repository.findProduct(productId).map(Product::getOffers).orElse(Collections.emptyList());
    }

}
