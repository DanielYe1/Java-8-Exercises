package com.b2wdigital.offer;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;

import java.util.Scanner;

public class BasketApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMenu();
        int menu = scanner.nextInt();
        ProductRepository repository = new ProductRepository();
        Basket basket = new Basket();
        while (menu != 5) {
            switch (menu) {
                case 1:
                    System.out.println(repository.toString());
                    System.out.println("Digite o id do produto:");
                    String idProduct = scanner.next();
                    System.out.println("Escolha id da oferta:");
                    // repository.findProduct(idProduct).map(Product::getOffers).ifPresent(System.out::println);
                    repository.findProduct(idProduct).ifPresent(Product::showOffers);
                    String idOffer = scanner.next();
                    repository.findProduct(idProduct).map(Product::getOffers).ifPresent(l -> {
                        l.stream().filter(s -> s.getId().equals(idOffer)).findAny().ifPresent(basket::add);
                    });

                    break;
                case 2:
                    System.out.println("Este é seu carrinho:");
                    System.out.println(basket.toString());
                    System.out.println("Digite o id do item a ser removido:");
                    String idRemove = scanner.next();
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
                    break;

            }

            printMenu();
            menu = scanner.nextInt();
        }
    }

    private static void printMenu() {
        System.out.println("1 - Adicionar oferta");
        System.out.println("2 - Remover oferta");
        System.out.println("3 - Fechar compra");
        System.out.println("4 - Ver carrinho");
        System.out.println("5 - Sair");
        System.out.println("Digite a opcao desejada:");
    }
}
