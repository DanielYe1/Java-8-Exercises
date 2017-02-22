package com.b2wdigital.offer.service;

import com.b2wdigital.offer.controller.BasketController;
import com.b2wdigital.offer.model.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @InjectMocks
    BasketService basketService;

    @Mock
    Basket basket;


    @Test
    public void deveria_remover_oferta_da_basket(){
        basketService.removeFromBasket(basket, "offerId");
        verify(basket).removeById("offerId");
    }

    @Test
    public void deveria_fechar_basket(){
        basketService.close(basket);
    }

}