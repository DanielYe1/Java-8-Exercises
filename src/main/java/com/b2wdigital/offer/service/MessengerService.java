package com.b2wdigital.offer.service;


import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;

import java.util.List;

public class MessengerService {
    Messenger messenger;

    public MessengerService(Messenger messenger) {
        this.messenger = messenger;
    }

    public String askForProduct(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException();
        }
        messenger.send(products);
        return messenger.ask("Digite o produto:");
    }


    public String askForOffer() {
        return messenger.ask("Digite a Oferta:");
    }

    public void showInvalidOffer() {
        messenger.send("Oferta invalida");
    }

    public void showNotFoundOffer() {
        messenger.send("Oferta nao encontrada");
    }

    public void showNotFoundProduct() {
        messenger.send("Produto nao encontrado");
    }

    public void showInvalidProduct() {
        messenger.send("Produto invalido");
    }


    public void showBasketString(String basket) {
        messenger.send("Carrinho:");
        messenger.send(basket);
    }

    public void showCloseBasket(double total) {
        messenger.send("Fechar compra selecionado");
        messenger.send("Total do seu carrinho é:");
        messenger.send(total);
    }
}
