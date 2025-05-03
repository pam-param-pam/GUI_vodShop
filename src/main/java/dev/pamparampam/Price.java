package dev.pamparampam;

import dev.pamparampam.utils.GENRE;

import java.util.Objects;

public class Price {
    private Integer subscriptionPrice = null;
    private Integer devicesNeededForDiscount = null;
    ;
    private Integer normalPrice = null;
    ;
    private Integer discountedPrice = null;
    ;

    public Price(int subscriptionPrice, int devicesNeededForDiscount, int normalPrice, int discountedPrice) {
        this.subscriptionPrice = subscriptionPrice;
        this.devicesNeededForDiscount = devicesNeededForDiscount;
        this.normalPrice = normalPrice;
        this.discountedPrice = discountedPrice;
    }

    public Price(int devicesNeededForDiscount, int normalPrice, int discountedPrice) {
        this.devicesNeededForDiscount = devicesNeededForDiscount;
        this.normalPrice = normalPrice;
        this.discountedPrice = discountedPrice;
    }

    public Price(int subscriptionPrice, int normalPrice) {
        this.subscriptionPrice = subscriptionPrice;
        this.normalPrice = normalPrice;
    }

    public Price() {
        this.normalPrice = 0;
    }


    public int calculateForClient(boolean isSubscription, int devices) {
        if (isSubscription) {
            if (subscriptionPrice != null) {
                return subscriptionPrice * devices;
            }
        }
        if (devicesNeededForDiscount != null && devices >= devicesNeededForDiscount) {
            return discountedPrice * devices;
        }
        return normalPrice * devices;
    }

    @Override
    public String toString() {
        return "Price{" +
                "subscriptionPrice=" + subscriptionPrice +
                ", devicesNeededForDiscount=" + devicesNeededForDiscount +
                ", normalPrice=" + normalPrice +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}