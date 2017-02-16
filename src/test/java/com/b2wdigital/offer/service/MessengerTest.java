package com.b2wdigital.offer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Scanner;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by daniel.ye on 15/02/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessengerTest {

    @Mock
    Sender sender;

    @Mock
    Receiver receiver;

    @InjectMocks
    Messenger messenger;


    @Test
    public void deveria_printar_mensagem_e_retornar_string_do_usuario() {
        String message = "message";
        when(receiver.receive()).thenReturn("digitei");
        assertThat(messenger.ask(message), equalTo("digitei"));
        verify(sender).send(message);
        verify(receiver).receive();
    }

    @Test
    public void deveria_printar_mensagem() {
        String message = "mensagem";
        messenger.send(message);
        verify(sender).send(message);
    }

}