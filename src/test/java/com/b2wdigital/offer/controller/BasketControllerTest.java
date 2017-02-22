package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.BasketService;
import com.b2wdigital.offer.service.Messenger;
import com.b2wdigital.offer.service.MessengerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketControllerTest {

    @InjectMocks
    private BasketController controller;
    @Mock
    private Basket basket;
    @Mock
    MessengerService mService;
    @Mock
    BasketService service;

    @Test
    public void deveria_remover_oferta_da_basket() {
        when(mService.askForOffer()).thenReturn("offerId");
        controller.removeOffer(basket);
        verify(service).removeFromBasket(basket, "offerId");
    }

    @Test
    public void deveria_fechar_a_compra() {
        when(basket.getTotalValue()).thenReturn(5.0);
        controller.closeBasket(basket);
        verify(mService).showCloseBasket(5.0);
        verify(service).close(basket);
    }

    @Test
    public void deveria_exibir_basket() {
        when(basket.toString()).thenReturn("basket");
        controller.showBasket(basket);
        verify(mService).showBasketString("basket");
    }

}
