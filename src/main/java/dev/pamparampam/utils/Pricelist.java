package dev.pamparampam.utils;

import dev.pamparampam.Films.Program;

import java.util.HashMap;


public class Pricelist {
    private static Pricelist INSTANCE;
    HashMap<Program, Price> priceListMap = new HashMap<>();

    private Pricelist() {
    }

    public static Pricelist getPricelist() {
        if (INSTANCE == null) {
            INSTANCE = new Pricelist();
        }
        return INSTANCE;
    }

    // Full 6-param version
    public void add(GENRE genre, String title, int subscriptionPrice, int devicesNeededForDiscount, int normalPrice, int discountedPrice) {
        Program program = new Program(title, genre);
        Price price = new Price(subscriptionPrice, devicesNeededForDiscount, normalPrice, discountedPrice);
        putPrice(program, price);
    }

    public void add(GENRE genre, String title, int devicesNeededForDiscount, int normalPrice, int discountedPrice) {
        Program program = new Program(title, genre);
        Price price = new Price(devicesNeededForDiscount, normalPrice, discountedPrice);
        putPrice(program, price);
    }

    public void add(GENRE genre, String title, int subscriptionPrice, int normalPrice) {
        Program program = new Program(title, genre);
        Price price = new Price(subscriptionPrice, normalPrice);
        putPrice(program, price);
    }

    public void add(GENRE genre, String title) {
        Program program = new Program(title, genre);
        Price price = new Price();
        putPrice(program, price);
    }

    public void remove(GENRE genre, String title) {
        Program key = new Program(title, genre);
        priceListMap.remove(key);

    }

    public Price getProgramPrice(Program program) {
        //just casting wont work
        Program key = new Program(program.getTitle(), program.getGenre());
        return priceListMap.get(key);
    }

    private void putPrice(Program program, Price price) {
        priceListMap.put(program, price);
    }

    @Override
    public String toString() {
        return "Pricelist{" +
                "HashPriceList=" + priceListMap +
                '}';
    }
}
