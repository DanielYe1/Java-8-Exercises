package com.b2wdigital.offer;

import com.b2wdigital.offer.controller.BasketController;
import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.*;

import java.util.Scanner;

public class BasketApplication {

    public static void main(String[] args) {
        Messenger messenger = new Messenger(new Sender(), new Receiver());
        BasketController controller = new BasketController(new MessengerService(messenger), new BasketService(), new ProductRepository());
        RunnerApplication runner = new RunnerApplication();
        runner.run(messenger, controller, new Basket());
    }

}
