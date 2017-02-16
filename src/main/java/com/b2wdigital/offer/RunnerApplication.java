package com.b2wdigital.offer;

import com.b2wdigital.offer.controller.BasketController;
import com.b2wdigital.offer.service.Messenger;

/**
 * Created by daniel.ye on 16/02/17.
 */
public class RunnerApplication {

    public void run(Messenger messenger, BasketController controller) {
        String menu = printMenu(messenger);

        while (!menu.equals("5")) {
            switch (menu) {
                case "1":
                    controller.addOffer();
                    break;
                case "2":
                    controller.showBasket();
                    controller.removeOffer();
                    controller.showBasket();

                    break;
                case "3":
                    controller.closeBasket();
                    break;
                case "4":
                    controller.showBasket();
                    break;

            }

            menu = printMenu(messenger);
        }
    }

    private String printMenu(Messenger messenger) {
        messenger.send("\n<---- MENU ---->");
        messenger.send("1 - Adicionar oferta");
        messenger.send("2 - Remover oferta");
        messenger.send("3 - Fechar compra");
        messenger.send("4 - Ver carrinho");
        messenger.send("5 - Sair");
        messenger.send("Digite a opcao desejada:");
        return messenger.ask("");
    }
}
