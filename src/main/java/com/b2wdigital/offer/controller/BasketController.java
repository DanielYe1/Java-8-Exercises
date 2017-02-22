package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;
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

/*    public void addOffer(Basket basket) {
        BasketService service = new BasketService();
        MessengerService mService =  new MessengerService();

        String productId = mService.askForProduct();

        if(productId != null){
            Optional<Product> product = service.findProduct(productId);
            if(product.isPresent()){
                String offerId = mService.askForOffer();
                Optional<Offer> offer = service.findOffer(product.get(), offerId);
                if(offer.isPresent()){
                    service.addOfferIntoBasket(basket, offer.get());
                }else{
                    mService.showNotFoundOffer();
                }
            } else{
                mService.showNotFoundProduct();
            }
        }else{
            mService.showInvalidProduct();
        }

        BasketControllerAdder adder = new BasketControllerAdder();
        String productId = adder.askForProduct();
        Optional<Product> oProduct = repository.findProduct(productId);

        oProduct.ifPresent(product -> {
            String offerId = adder.askForOffer();
            product.getOfferById(offerId).ifPresent(basket::add);
        });

        if (oProduct.isPresent()) {
            Product product = oProduct.get();
            Optional<Offer> offer = adder.askForOffer(product);
            if (offer.isPresent()) {
                basket.add(offer.get());
            } else {
                mService.showNotFoundOffer();
            }
        } else {
            messenger.send("Produto nao encontrado.");
        }
    }*/

}
