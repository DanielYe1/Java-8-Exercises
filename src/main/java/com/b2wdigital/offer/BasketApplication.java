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

        printMenu();
        int menu = scanner.nextInt();
        BasketController controller = new BasketController(new ProductRepository(), new Basket());
        Messenger messenger = new Messenger();
        while (menu != 5) {
            switch (menu) {
/*                case 1:
                    String productId = messenger.ask("Digite o produto:");
                    messenger.send("ofertas do produto");
                    String offerId = messenger.ask("Escolha a oferta:");

                    System.out.println(controller.getOffersByProductId(productId));
                    String idOffer = scanner.next();

                    controller.addOfferById(productId, idOffer);
                    break;*/
/*                case 2:
                    System.out.println("Este é seu carrinho:");
                    System.out.println(basket.toString());
                    System.out.println("Digite o id do item a ser removido:");
                    String idRemove = scanner.next();
                    // controller
                    basket.removeById(idRemove);
                    System.out.println("Carrinho atual:");
                    System.out.println(basket.toString());
                    break;
                case 3:
                    System.out.println("Fechar compra selecionado");
                    System.out.println("Total do seu carrinho é:");
                    System.out.println(basket.getTotalValue());
                    break;
                case 4:
                    System.out.println(basket.toString());
                    break;*/

            }

            printMenu();
            menu = scanner.nextInt();
        }
    }

    private static void printMenu() {
        System.out.println("\n<---- MENU ---->");
        System.out.println("1 - Adicionar oferta");
        System.out.println("2 - Remover oferta");
        System.out.println("3 - Fechar compra");
        System.out.println("4 - Ver carrinho");
        System.out.println("5 - Sair");
        System.out.println("Digite a opcao desejada:");
    }
}
