package com.b2wdigital.offer.service;


import com.b2wdigital.offer.model.Basket;

public class BasketService {

    public void removeFromBasket(Basket basket, String offerId){
        basket.removeById(offerId);
    }

    public void close(Basket basket) {
        basket.cleanOffers();
    }
}
