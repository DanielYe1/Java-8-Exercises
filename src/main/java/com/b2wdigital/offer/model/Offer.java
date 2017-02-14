package com.b2wdigital.offer.model;

public class Offer {

    private Double price;
    private String seller;
    private String sku;
    private long quantity;

    public Offer(double price, String seller, String sku, long quantity) {
        super();
        this.price = price;
        this.seller = seller;
        this.sku = sku;
        this.quantity = quantity;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + (int) (quantity ^ (quantity >>> 32));
        result = prime * result + ((seller == null) ? 0 : seller.hashCode());
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
        Offer other = (Offer) obj;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (quantity != other.quantity)
            return false;
        if (seller == null) {
            if (other.seller != null)
                return false;
        } else if (!seller.equals(other.seller))
            return false;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        return true;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Offer [price=" + price + ", seller=" + seller + ", sku=" + sku + ", quantity=" + quantity + ", id=" + getId() + "]";
    }

    public String idPriceString(){
        return String.format("id=%s price=%s\n",getId(),price);
    }

    public String getId() {
        return seller + "-" + sku;
    }
}
