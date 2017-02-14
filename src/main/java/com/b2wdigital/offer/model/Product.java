package com.b2wdigital.offer.model;

import org.hamcrest.Matcher;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Product {
    private String id;
    private String name;
    private List<String> tags;
    private List<String> groups;
    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public Product(String id, String name, List<String> tags, List<String> groups, List<Offer> offers) {
        super();
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.groups = groups;
        this.offers = offers;
        if (offers != null) {
            offers.sort(Comparator.comparing(Offer::getPrice));
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", tags=" + tags + ", groups=" + groups + ", offers=" + offers + "]";
    }

    public void showOffers() {
        for (Offer offer : offers) {
            System.out.print(offer.idPriceString());
        }
    }


    public Optional<Offer> getOfferById(String idOffer) {
        for (Offer offer : offers) {
            if (offer.getId().equals(idOffer)) {
                return Optional.of(offer);
            }
        }
        return Optional.empty();
    }
}
