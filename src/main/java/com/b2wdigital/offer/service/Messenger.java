package com.b2wdigital.offer.service;

import java.util.Scanner;

/**
 * Created by daniel.ye on 15/02/17.
 */
public class Messenger {

    Scanner scanner;

    public Messenger(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askString(String mensagem) {
        System.out.println(mensagem);
        return scanner.nextLine();
    }
}
