package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;
import com.b2wdigital.offer.service.BasketService;
import com.b2wdigital.offer.service.Messenger;
import com.b2wdigital.offer.service.MessengerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

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
    private MessengerService mService;
    @Mock
    private BasketService service;
    @Mock
    private ProductRepository repository;

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

    @Test
    public void deveria_adicionar_oferta_na_cesta() {
        Product product = mock(Product.class);
        Offer offer = mock(Offer.class);
        when(repository.findAll()).thenReturn(Collections.singletonList(product));
        when(mService.askForProduct(Collections.singletonList(product))).thenReturn("productId");
        when(repository.findProduct("productId")).thenReturn(Optional.of(product));

        when(mService.askForOffer()).thenReturn("offerId");
        when(product.getOfferById("offerId")).thenReturn(Optional.of(offer));
        when(basket.add(offer)).thenReturn(true);

        controller.addOffer(basket);

        verify(repository).findAll();
        verify(mService).askForProduct(Collections.singletonList(product));
        verify(repository).findProduct("productId");

        verify(mService).askForOffer();
        verify(product).getOfferById("offerId");
        verify(basket).add(offer);
    }

    @Test
    public void nao_deveria_adicionar_produto_na_cesta_e_deveria_informar_mensagem_produto_nao_encontrado(){
        Product product = mock(Product.class);
        when(repository.findAll()).thenReturn(Collections.singletonList(product));
        when(mService.askForProduct(Collections.singletonList(product))).thenReturn("emptyId");
        when(repository.findProduct("emptyId")).thenReturn(Optional.empty());

        controller.addOffer(basket);

        verify(repository).findAll();
        verify(mService).askForProduct(Collections.singletonList(product));
        verify(repository).findProduct("emptyId");
        verify(mService).showNotFoundProduct();
    }

    @Test
    public void nao_deveria_adicionar_oferta_na_cesta_e_deveria_informar_mensagem_oferta_nao_encontrada(){
        Product product = mock(Product.class);
        Offer offer = mock(Offer.class);
        when(repository.findAll()).thenReturn(Collections.singletonList(product));
        when(mService.askForProduct(Collections.singletonList(product))).thenReturn("productId");
        when(repository.findProduct("productId")).thenReturn(Optional.of(product));

        when(mService.askForOffer()).thenReturn("emptyId");
        when(product.getOfferById("emptyId")).thenReturn(Optional.empty());

        controller.addOffer(basket);

        verify(repository).findAll();
        verify(mService).askForProduct(Collections.singletonList(product));
        verify(repository).findProduct("productId");

        verify(mService).askForOffer();
        verify(product).getOfferById("emptyId");
        verify(mService).showNotFoundOffer();
    }

}
