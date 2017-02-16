package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.model.ProductTest;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.Messenger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BasketController {

    private ProductRepository repository;
    private Basket basket;
    private Messenger messenger;

    public BasketController(ProductRepository repository, Basket basket, Messenger messenger) {
        this.repository = repository;
        this.basket = basket;
        this.messenger = messenger;
    }

    public List<Offer> getBasketOffers() {
        return basket.getOffers();
    }

    public void removeOffer() {
        String offerId = messenger.ask("Digite o id do item a ser removido:");
        basket.removeById(offerId);
    }

    public void closeBasket() {
        messenger.send("Fechar compra selecionado");
        messenger.send("Total do seu carrinho é:");
        messenger.send(String.valueOf(basket.getTotalValue()));
    }

    public void showBasket() {
        messenger.send(basket.toString());
    }

    public void addOffer() {
        messenger.send(repository.findAll());
        String productId = messenger.ask("Digite o produto:");
        Optional<Product> oProduct = repository.findProduct(productId);
        if(oProduct.isPresent()){
            Product product = oProduct.get();
            messenger.send("Ofertas do produto:");
            messenger.send(product.getOffers().toString());
            String offerId = messenger.ask("Escolha a oferta:");
            Optional<Offer> oOffer = product.getOfferById(offerId);
            if(oOffer.isPresent()){
                oOffer.ifPresent(basket::add);
            }else{
                messenger.send("Oferta nao encontrada.");
            }
        }else{
            messenger.send("Produto nao encontrado.");
        }
    }
}
