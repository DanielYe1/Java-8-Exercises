package com.b2wdigital.offer.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OfferTest {

    @Test
    public void deveria_criar_toString_corretamente() {
        Offer p1 = new Offer(1, "1", "sku1", 1);
        assertThat(p1.toString(), equalTo("Offer [price=1.0, seller=1, sku=sku1, quantity=1, id=1-sku1]"));
    }

    @Test
    public void deveria_criar_id_corretamente() {
        Offer p1 = new Offer(1, "1", "sku1", 1);
        assertThat(p1.getId(), equalTo("1-sku1"));
    }

    @Test
    public void deveria_retornar_string_com_preco_e_id(){
        Offer p1 = new Offer(1, "1", "sku1", 1);
        assertThat(p1.idPriceString(), equalTo("id=1-sku1 price=1.0\n"));
    }

}