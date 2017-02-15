package com.b2wdigital.offer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by daniel.ye on 13/02/17.
 */
public class Basket {

    private List<Offer> offers = new ArrayList<>();


    @Override
    public String toString() {
        StringBuilder offerString = new StringBuilder();
        for (Offer offer : offers) {
            offerString.append("id=").append(offer.getId()).append(" ").append("price=").append(offer.getPrice()).append("\n");
        }
        return offerString.toString();
    }

    public void add(Offer oferta) {
        if (oferta == null) {
            throw new IllegalArgumentException("Oferta nula não pode ser adicionada no carrinho");
        }
        offers.add(oferta);
    }

    public List<Offer> getOffers() {
        return Collections.unmodifiableList(offers);
    }


    public void removeById(String idRemoved) {
        for (int i = 0; i < offers.size(); i++) {
            if (offers.get(i).getId().equals(idRemoved)) {
                offers.remove(offers.get(i));
            }
        }
    }

    public double getTotalValue() {
        return offers.stream().map(Offer::getPrice).reduce(0.0, Double::sum);
    }
}
