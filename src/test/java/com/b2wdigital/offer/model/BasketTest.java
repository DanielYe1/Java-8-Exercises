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
    public void deveria_retornar_toString_correto() {
        Basket basket = new Basket();
        basket.add(new Offer(5.5, "seller1", "sku1", 5));
        basket.add(new Offer(10.5, "seller2", "sku2", 4));
        assertThat(basket.toString(), equalTo("id=seller1-sku1 price=5.5\nid=seller2-sku2 price=10.5\n"));
    }

    @Test
    public void deveria_remover_ofertas_do_carrinho_por_id() {
        Offer oferta = new Offer(5.5, "seller1", "sku1", 5);
        Basket basket = new Basket();
        basket.add(oferta);
        basket.removeById("seller1-sku1");
        assertThat(basket.getOffers(), empty());
    }

    @Test
    public void deveria_retornar_valor_total_do_carrinho() {
        Basket basket = new Basket();
        basket.add(new Offer(5.5, "seller1", "sku1", 5));
        basket.add(new Offer(10.5, "seller2", "sku2", 4));
        assertThat(basket.getTotalValue(), equalTo(16.0));
    }

    @Test
    public void deveria_esvaziar_as_ofertas_do_carrinho(){
        Basket basket = new Basket();
        basket.add(new Offer(5.5, "seller1", "sku1", 5));
        basket.cleanOffers();
        assertThat(basket.getOffers(), empty());
    }
}
