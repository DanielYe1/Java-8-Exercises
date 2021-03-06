package com.b2wdigital.offer.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.lang.model.type.NullType;

import org.junit.Test;

public class ProductTest {

    @Test
    public void deveria_criar_toString_corretamente() {
        Product p1 = new Product("1", "p1", Arrays.asList("a", "b"), Arrays.asList("c", "d"),
                Collections.singletonList(new Offer(5.2, "seller1", "sku1", 5)));
        assertThat(p1.toString(), equalTo(
                "Product [id=1, name=p1, tags=[a, b], groups=[c, d], offers=[Offer [price=5.2, seller=seller1, sku=sku1, quantity=5, id=seller1-sku1]]]"));
    }


    @Test
    public void deveria_retornar_oferta_com_id_dado() {
        Offer offer = new Offer(5.2, "seller1", "sku1", 5);
        Product p1 = new Product("1", "p1", Arrays.asList("a", "b"), Arrays.asList("c", "d"),
                Collections.singletonList(offer));
        assertThat(p1.getOfferById("seller1-sku1"), equalTo(Optional.of(offer)));
    }
}
