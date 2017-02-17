package com.b2wdigital.offer.service;

import com.b2wdigital.offer.model.Product;

import java.util.List;
import java.util.Scanner;

/**
 * Created by daniel.ye on 15/02/17.
 */
public class Messenger {

    private Sender sender;
    private Receiver receiver;

    public Messenger(Sender sender, Receiver receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String ask(String message) {
        sender.send(message);
        return receiver.receive();
    }

    public void send(Object object) {
        sender.send(object);
    }
}
