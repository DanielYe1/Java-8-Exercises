package com.b2wdigital.offer.model;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import sun.invoke.empty.Empty;

/**
 * Created by daniel.ye on 13/02/17.
 */
public class BasketTest {

    @Test
    public void deveria_adicionar_ofertas_no_carrinho() {
        Offer oferta = new Offer(5.5, "seller1", "sku1", 5);
        Basket basket = new Basket();
        basket.add(oferta);
        assertThat(basket.getOffers(), hasItem(oferta));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deveria_adicionar_oferta_nula() {
        Basket basket = new Basket();
        basket.add(null);
    }

    @Test
    public void deveria_remover_ofertas_do_carrinho() {
        Offer oferta = new Offer(5.5, "seller1", "sku1", 5);
        Basket basket = new Basket();
        basket.add(oferta);
        basket.remove(oferta);
        assertThat(basket.getOffers(), not(hasItem(oferta)));
    }

    @Test
    public void nao_deveria_dar_erro_ao_remover_oferta_inexistente() {
        Offer oferta = new Offer(5.5, "seller1", "sku1", 5);
        Basket basket = new Basket();
        basket.remove(oferta);
        assertThat(basket.getOffers(), empty());
    }

    @Test
    public void deveria_retornar_toString_correto() {
        Basket basket = new Basket();
        basket.add(new Offer(5.5, "seller1", "sku1", 5));
        basket.add(new Offer(10.5, "seller2", "sku2", 4));
        assertThat(basket.toString(), equalTo("id=seller1-sku1 price=5.5\nid=seller2-sku2 price=10.5\n"));
    }

}
