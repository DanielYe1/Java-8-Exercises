package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.model.ProductTest;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.BasketService;
import com.b2wdigital.offer.service.Messenger;
import com.b2wdigital.offer.service.MessengerService;
import com.sun.org.apache.regexp.internal.REUtil;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BasketController {

    private ProductRepository repository;
    private Messenger messenger;

    public BasketController(ProductRepository repository, Messenger messenger) {
        this.repository = repository;
        this.messenger = messenger;
    }

    public void removeOffer(Basket basket) {
        BasketService service = new BasketService();
        MessengerService mService =  new MessengerService();
        String offerId = mService.askForOffer();
        if(offerId != null){
            service.removeFromBasket(basket, offerId);
        }else{
            mService.showInvalidOffer();
        }
    }

    public void closeBasket(Basket basket) {
        messenger.send("Fechar compra selecionado");
        messenger.send("Total do seu carrinho é:");
        messenger.send(String.valueOf(basket.getTotalValue()));
    }

    public void showBasket(Basket basket) {
        messenger.send(basket.toString());
    }

    public Optional<Product> chooseProduct() {
        messenger.send(repository.findAll());
        String productId = messenger.ask("Digite o produto:");
        return repository.findProduct(productId);
    }

    public Optional<Offer> chooseOffer(Product product){
        messenger.send("Ofertas do produto:");
        messenger.send(product.getOffers());
        String offerId = messenger.ask("Escolha a oferta:");
        return product.getOfferById(offerId);
    }

    public void addOffer(Basket basket) {
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
                messenger.send("Oferta nao encontrada.");
            }
        } else {
            messenger.send("Produto nao encontrado.");
        }
    }

}
