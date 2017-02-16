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
    public void deveria_retornar_ofertas_do_produto_por_id() {
        final String productId = "1";

        final Product product = mock(Product.class);
        final Offer offer = mock(Offer.class);
        final List<Offer> offersReturnedByProduct = Arrays.asList(offer);

        when(repository.findProduct(eq(productId))).thenReturn(Optional.of(product));
        when(product.getOffers()).thenReturn(offersReturnedByProduct);

        List<Offer> offers = controller.getOffersByProductId(productId);

        assertThat(offers, equalTo(offersReturnedByProduct));

        verify(repository).findProduct(eq(productId));
        verify(product).getOffers();
    }

    @Test
    public void deveria_retornar_lista_vazia_com_id_para_produto_inexistente() {
        String productId = "3";
        Offer offer = mock(Offer.class);
        when(repository.findProduct(eq(productId))).thenReturn(Optional.empty());
        List<Offer> offers = controller.getOffersByProductId(productId);

        assertThat(offers, empty());
    }

    @Test
    public void deveria_retornar_lista_vazia_com_id_para_produto_com_oferta_vazia() {
        String productId = "4";
        Product product = mock(Product.class);

        when(repository.findProduct(eq(productId))).thenReturn(Optional.of(product));
        when(product.getOffers()).thenReturn(Collections.emptyList());

        assertThat(controller.getOffersByProductId(productId), empty());
    }

    @Test
    public void deveria_retornar_offers_da_basket() {
        Offer offer = mock(Offer.class);
        when(basket.getOffers()).thenReturn(Collections.singletonList(offer));
        assertThat(controller.getBasketOffers(), equalTo(Collections.singletonList(offer)));
    }

    @Test
    public void deveria_adicionar_oferta_na_basket() {
        when(messenger.ask("Digite o produto:")).thenReturn("productId");
        when(messenger.ask("Escolha a oferta:")).thenReturn("offerId");
        when(repository.findOffersByProduct("productId")).thenReturn(Collections.emptyList());
        when(repository.findAll()).thenReturn(Collections.emptyList());
        controller.addOffer();

        verify(messenger).send(Collections.emptyList().toString());
        verify(messenger).ask("Digite o produto:");
        verify(messenger).send("Ofertas do produto:");
        verify(messenger).send(Collections.emptyList().toString());
        verify(messenger).ask("Escolha a oferta:");



    }

    @Test
    public void nao_deveria_adicionar_na_basket_oferta_com_id_inexistente() {
        String productId = "2";
        String offerId = "nao tem";
        Product product = mock(Product.class);

        when(repository.findProduct(eq(productId))).thenReturn(Optional.of(product));
        when(product.getOfferById(eq(offerId))).thenReturn(Optional.empty());

        assertThat(controller.addOfferById(productId, offerId), equalTo(false));
    }

    @Test
    public void nao_deveria_adicionar_na_basket_com_produto_inexistente() {
        String productId = "3";
        String offerId = "nao tem";

        Product product = mock(Product.class);

        when(repository.findProduct(eq(productId))).thenReturn(Optional.empty());

        assertThat(controller.addOfferById(productId, offerId), equalTo(false));
    }

    @Test
    public void deveria_remover_oferta_da_basket_do_controller() {
        when(messenger.ask("Digite o id do item a ser removido:")).thenReturn("seller1-sku1");
        controller.removeOffer();

        verify(basket).removeById("seller1-sku1");
        verify(messenger).ask("Digite o id do item a ser removido:");
    }

    @Test
    public void deveria_retornar_string_com_produtos_do_repository() {
        when(repository.toString()).thenReturn("");
        assertThat(controller.getRepositoryProductsToString(), equalTo(repository.toString()));
    }

    @Test
    public void deveria_fechar_a_compra() {
        when(basket.getTotalValue()).thenReturn(5.0);
        controller.closeBasket();
        verify(messenger).send("Fechar compra selecionado");
        verify(messenger).send("Total do seu carrinho é:");
        verify(messenger).send("5.0");
    }

    @Test
    public void deveria_exibir_basket() {
        when(basket.toString()).thenReturn("basket");
        controller.showBasket();
        verify(messenger).send("basket");
    }

}
