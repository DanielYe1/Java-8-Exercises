package com.b2wdigital.offer.service;


import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;

public class MessengerService {
    Messenger messenger;
    ProductRepository repository;



    public String askForProduct(Product product) {
        messenger.send(repository.findAll());
        return messenger.ask("Digite o produto:");
    }
}
