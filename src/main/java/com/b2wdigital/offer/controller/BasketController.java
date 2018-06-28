package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.impl.ProductRepository;
import com.b2wdigital.offer.service.BasketService;
import com.b2wdigital.offer.service.MessengerService;

import java.util.Optional;

public class BasketController {

    private MessengerService mService;
    private BasketService service;
    private ProductRepository repository;

    public BasketController(MessengerService mService, BasketService service, ProductRepository repository) {
        this.mService = mService;
        this.service = service;
        this.repository = repository;
    }

    public void removeOffer(Basket basket) {
        String offerId = mService.askForOffer();
        if(offerId != null){
            service.removeFromBasket(basket, offerId);
        }else{
            mService.showInvalidOffer();
        }
    }

    public void closeBasket(Basket basket) {
        mService.showCloseBasket(basket.getTotalValue());
        service.close(basket);

    }

    public void showBasket(Basket basket) {
        mService.showBasketString(basket.toString());
    }

    public void addOffer(Basket basket) {
        String productId = mService.askForProduct(repository.findAll());
        Optional<Product> oProduct = repository.findProduct(productId);

        if (oProduct.isPresent()) {
            Product product = oProduct.get();
            Optional<Offer> offer = product.getOfferById(mService.askForOffer());
            if (offer.isPresent()) {
                basket.add(offer.get());
            } else {
                mService.showNotFoundOffer();
            }
        } else {
            mService.showNotFoundProduct();
        }
    }

}
