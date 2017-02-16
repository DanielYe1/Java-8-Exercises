package com.b2wdigital.offer.service;


import java.util.Scanner;

public class Receiver {
    private Scanner scanner = new Scanner(System.in);

    public String receive(){
        return scanner.next();
    }
}
