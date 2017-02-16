package com.b2wdigital.offer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by daniel.ye on 15/02/17.
 */
public class MessengerTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    Messenger messenger;


    @Test
    public void deveria_printar_mensagem_e_retornar_string_do_usuario() {
        Messenger messenger = new Messenger(new Scanner("word\nrrod1\n"));
        String mensagem = "hi";
        assertThat(messenger.askString(mensagem), equalTo("word"));
    }

}