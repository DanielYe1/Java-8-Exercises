package com.b2wdigital.offer.service;

import com.b2wdigital.offer.model.Product;

import java.util.List;
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

    public void sendToString(Object object) {
        System.out.println(object.toString());
    }

    public void sendString(String string) {
        System.out.println(string);
    }

}
