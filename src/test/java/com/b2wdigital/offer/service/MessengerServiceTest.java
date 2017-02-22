package com.b2wdigital.offer.service;

import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessengerServiceTest {

    @InjectMocks
    MessengerService mService;

    @Mock
    Messenger messenger;

    @Mock
    ProductRepository repository;

    @Test
    public void deveria_mostrar_lista_de_ofertas_para_perguntar_e_retornar_produto() {
        Product product = mock(Product.class);
        when(messenger.ask("Digite o produto:")).thenReturn("productId");
        doNothing().when(messenger).send(any());

        assertThat(mService.askForProduct(Collections.singletonList(product)), equalTo("productId"));
        verify(messenger).send(Collections.singletonList(product));
        verify(messenger).ask("Digite o produto:");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveria_lancar_excecao_ao_receber_lista_vazia_ao_perguntar_produto(){
        mService.askForProduct(Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveria_lancar_excecao_ao_receber_null_ao_perguntar_produto(){
        mService.askForProduct(null);
    }

    @Test
    public void deveria_perguntar_e_retornar_oferta(){
        when(messenger.ask("Digite a Oferta:")).thenReturn("offerId");
        assertThat(mService.askForOffer(), equalTo("offerId"));
        verify(messenger).ask("Digite a Oferta:");
    }

    @Test
    public void deveria_dizer_oferta_invalida(){
        mService.showInvalidOffer();
        verify(messenger).send("Oferta invalida");
    }

    @Test
    public void deveira_dizer_que_nao_encontrou_oferta(){
        mService.showNotFoundOffer();
        verify(messenger).send("Oferta nao encontrada");
    }

    @Test
    public void deveria_dizer_que_nao_encontrou_produto(){
        mService.showNotFoundProduct();
        verify(messenger).send("Produto nao encontrado");
    }

    @Test
    public void deveria_dizer_produto_invalido(){
        mService.showInvalidProduct();
        verify(messenger).send("Produto invalido");
    }

    @Test
    public void deveria_mostrar_basket(){
        mService.showBasketString("basket");
        verify(messenger).send("Carrinho:");
        verify(messenger).send("basket");
    }

    @Test
    public void deveria_mostrar_fechamento_da_basket(){
        mService.showCloseBasket(50.0);
        verify(messenger).send("Fechar compra selecionado");
        verify(messenger).send("Total do seu carrinho é:");
        verify(messenger).send(50.0);
    }

}