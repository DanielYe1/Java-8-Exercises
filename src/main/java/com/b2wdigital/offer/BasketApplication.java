package com.b2wdigital.offer;

import com.b2wdigital.offer.controller.BasketController;
import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.Messenger;
import com.b2wdigital.offer.service.Receiver;
import com.b2wdigital.offer.service.Sender;

import java.util.Scanner;

public class BasketApplication {

    public static void main(String[] args) {
        Messenger messenger = new Messenger(new Sender(), new Receiver());
        BasketController controller = new BasketController(new ProductRepository(), messenger);
        RunnerApplication runner = new RunnerApplication();
        runner.run(messenger, controller, new Basket());
    }

}
