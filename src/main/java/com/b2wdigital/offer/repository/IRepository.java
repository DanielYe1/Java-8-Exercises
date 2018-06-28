package com.b2wdigital.offer.repository;

import java.util.*;

import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;

public interface IRepository {
    public Optional<Product> findProduct(String id);

    public List<Offer> findOffersByProduct(String productId);

    public List<Product> findAll();
}