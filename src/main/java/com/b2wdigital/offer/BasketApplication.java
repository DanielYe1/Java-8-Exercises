package com.b2wdigital.offer;

import com.b2wdigital.offer.controller.BasketController;
import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.Messenger;

import java.util.Optional;
import java.util.Scanner;

public class BasketApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BasketController controller = new BasketController(new ProductRepository(), new Basket());
        Messenger messenger = new Messenger(new Scanner(System.in));

        printMenu(messenger);
        int menu = scanner.nextInt();
        while (menu != 5) {
            switch (menu) {
                case 1:
                    messenger.sendString(controller.getRepositoryProductsToString());
                    String productId = messenger.askString("Digite o produto:");
                    messenger.sendString("Ofertas do produto:");
                    messenger.sendToString(controller.getOffersByProductId(productId));
                    String offerId = messenger.askString("Escolha a oferta:");

                    controller.addOfferById(productId, offerId);
                    break;
                case 2:
                    messenger.sendString("Este é seu carrinho:");
                    messenger.sendString(controller.basketToString());
                    String idRemove = messenger.askString("Digite o id do item a ser removido:");
                    // controller
                    controller.removeOfferById(idRemove);
                    messenger.sendString("Carrinho atual:");
                    messenger.sendString(controller.basketToString());
                    break;
                case 3:
                    messenger.sendString("Fechar compra selecionado");
                    messenger.sendString("Total do seu carrinho é:");
                    messenger.sendToString(controller.getBasketTotalValue());
                    break;
                case 4:
                    messenger.sendString(controller.basketToString());
                    break;

            }

            printMenu(messenger);
            menu = scanner.nextInt();
        }
    }

    private static void printMenu(Messenger messenger) {
        messenger.sendString("\n<---- MENU ---->");
        messenger.sendString("1 - Adicionar oferta");
        messenger.sendString("2 - Remover oferta");
        messenger.sendString("3 - Fechar compra");
        messenger.sendString("4 - Ver carrinho");
        messenger.sendString("5 - Sair");
        messenger.sendString("Digite a opcao desejada:");
    }
}
