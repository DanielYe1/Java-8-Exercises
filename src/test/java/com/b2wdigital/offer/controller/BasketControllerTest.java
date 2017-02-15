package com.b2wdigital.offer.controller;

import com.b2wdigital.offer.model.Basket;
import com.b2wdigital.offer.model.Offer;
import com.b2wdigital.offer.model.Product;
import com.b2wdigital.offer.repository.ProductRepository;
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
        Basket basket = new Basket();
        BasketController controller = new BasketController(new ProductRepository(), basket);
        Offer offer = mock(Offer.class);
        basket.add(offer);
        assertThat(controller.getBasketOffers(), equalTo(Arrays.asList(offer)));
    }

    @Test
    public void deveria_adicionar_na_basket_pelo_id_da_oferta() {
        String productId = "2";
        String offerId = "seller1-sku1";
        Product product = mock(Product.class);
        Offer offer = mock(Offer.class);

        when(repository.findProduct(eq(productId))).thenReturn(Optional.of(product));
        when(product.getOfferById(eq(offerId))).thenReturn(Optional.of(offer));
        when(basket.add(eq(offer))).thenReturn(true);

        assertThat(controller.addOfferById(productId, offerId), equalTo(true));
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
}
