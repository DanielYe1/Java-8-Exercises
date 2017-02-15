package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.model.ProductTest;
import com.b2wdigital.offer.repository.ProductRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BasketController {

    private ProductRepository repository;
    private Basket basket;

    public BasketController(ProductRepository repository, Basket basket) {
        this.repository = repository;
        this.basket = basket;
    }

    public List<Offer> getOffersByProductId(String productId) {
        return repository.findProduct(productId).map(Product::getOffers).orElse(Collections.emptyList());
    }

    public boolean addOfferById(String productId, String offerId) {
        return repository.findProduct(productId)
                .flatMap(prod -> prod.getOfferById(offerId))
                .map(o -> basket.add(o))
                .orElse(false);
    }

    public List<Offer> getBasketOffers() {
        return basket.getOffers();
    }
}
