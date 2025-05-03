package dev.pamparampam.Films;

import dev.pamparampam.Client;
import dev.pamparampam.Price;
import dev.pamparampam.Pricelist;
import dev.pamparampam.utils.GENRE;

import java.util.Objects;


public class ClientProgram extends Program implements Cloneable {

    private int devices;
    private Client client = null;

    public ClientProgram(String title, GENRE genre, int devices) {
        super(title, genre);
        if (devices <= 0) {
            throw new RuntimeException("Devices must be > 0");
        }
        this.devices = devices;
    }

    @Override
    public ClientProgram clone() {
        try {
            return (ClientProgram) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        String stringPrice;
        try {
            int price = getPrice();
            stringPrice = Integer.toString(price);
        } catch (RuntimeException e) {
            stringPrice = "Unknown";
        }
        return "ClientProgram{" + "devices=" + devices + ", title='" + title +
                ", genre=" + genre + ", price=" + stringPrice + "}";
    }

    public int getDevices() {
        return devices;
    }

    public void setClient(Client client) {
        //czy mi sie to podoba? Nie
        //Czy wolałbym zeby to było w construktorze? Tak
        //Chce jednak by ClientProgram miał możliwość sprawdzenia czy client ma subskrypcje,
        // i chce by get price było metodą na ClientProgram a nie gdzieś indziej
        this.client = client;
    }

    public int getPrice() {
        if (client == null) {
            throw new RuntimeException("You must call setClient() before calling getPrice()");
        }
        //get prices for given program
        Price price = Pricelist.getPricelist().getProgramPrice(this);
        if (price != null) {
            return price.calculateForClient(client.hasSubscription(), devices);
        }
        throw new RuntimeException("Unknown price");
    }

    public int getSingularPrice() {
        return getPrice() / devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientProgram that = (ClientProgram) o;
        return getDevices() == that.getDevices() && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getGenre(), getDevices());
    }

    public void setDevices(int devices) {
        this.devices = devices;
    }
}
