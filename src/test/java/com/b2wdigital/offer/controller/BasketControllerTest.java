package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.Messenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketControllerTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private Basket basket;
    @Mock
    private Messenger messenger;
    @InjectMocks
    private BasketController controller;

    @Test
    public void deveria_remover_oferta_da_basket_do_controller() {
        when(messenger.ask("Digite o id do item a ser removido:")).thenReturn("seller1-sku1");
        controller.removeOffer(basket);

        verify(basket).removeById("seller1-sku1");
        verify(messenger).ask("Digite o id do item a ser removido:");
    }

    @Test
    public void deveria_fechar_a_compra() {
        when(basket.getTotalValue()).thenReturn(5.0);
        controller.closeBasket(basket);
        verify(messenger).send("Fechar compra selecionado");
        verify(messenger).send("Total do seu carrinho é:");
        verify(messenger).send("5.0");
    }

    @Test
    public void deveria_exibir_basket() {
        when(basket.toString()).thenReturn("basket");
        controller.showBasket(basket);
        verify(messenger).send("basket");
    }

}
