package com.b2wdigital.offer;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;

import java.util.Scanner;

public class CarrinhoApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMenu();
        int menu = scanner.nextInt();
        ProductRepository repository = new ProductRepository();
        Basket basket = new Basket();
        while (menu != 5) {
            switch (menu) {
                case 1:
                    System.out.println("Digite o produto:");
                    String idProduct = scanner.next();
                    System.out.println("Escolha id da oferta:");
                    repository.findProduct(idProduct).map(Product::getOffers).ifPresent(System.out::println);
                    String idOffer = scanner.next();
                    repository.findProduct(idProduct).map(Product::getOffers).ifPresent(l -> {
                        l.stream().filter(s -> s.getId().equals(idOffer)).findAny().ifPresent(basket::add);
                    });

                    break;

                case 4:
                    System.out.println(basket.toString());

            }

            printMenu();
            menu = scanner.nextInt();
        }
    }

    static void printMenu() {
        System.out.println("1 - Adicionar oferta");
        System.out.println("2 - Remover oferta");
        System.out.println("3 - Fechar compra");
        System.out.println("4 - Ver carrinho");
        System.out.println("5 - Sair");
        System.out.println("Digite a opcao desejada:");
    }
}
