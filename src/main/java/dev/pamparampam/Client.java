package dev.pamparampam;

import dev.pamparampam.Films.ClientProgram;
import dev.pamparampam.utils.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

import static dev.pamparampam.utils.GoofyBool.*;

public class Client {
    private final String name;
    private final UUID uuid = UUID.randomUUID();
    private double balance;
    private final boolean isSubbed;
    private final Wishlist wishList;
    private final Basket basket;
    private final LastTransaction lastTransaction;

    public Client(String name, int balance, GoofyBool SUB) {
        this.name = name;
        this.balance = balance;
        this.isSubbed = (SUB == YES);
        this.wishList = new Wishlist();
        this.basket = new Basket();
        this.lastTransaction = new LastTransaction();
    }

    public void add(ClientProgram program) {
        program.setClient(this);
        wishList.addProgram(program);
    }

    public void remove(ClientProgram program) {
        wishList.removeProgram(program);
    }

    public void returnVOD(GENRE genre, String title, int devices) {
        //tak wiem ze to pozwala na to by user dostał wiecej kasy niż zapłacił.
        //Jeżeli np zmieni sie cenna albo user zmieni subskrypcje
        //Ale to jest ✨ feature ✨
        ClientProgram program = new ClientProgram(title, genre, devices);

        double priceToReturn = lastTransaction.getPaidPrice(program);
        addToBalance(priceToReturn);

    }

    public void pack() {
        ArrayList<ClientProgram> wishListPrograms = wishList.getPrograms();
        Pricelist cennik = Pricelist.getPricelist();

        ArrayList<ClientProgram> programsWithPrice = new ArrayList<>();

        for (ClientProgram program : wishListPrograms) {
            // check if pricelist has a price for this program
            Price price = cennik.getProgramPrice(program);
            if (price != null) {
                programsWithPrice.add(program);
            }
        }
        basket.setPrograms(programsWithPrice);
        wishList.removePrograms(programsWithPrice);

    }

    public void pay(PaymentType paymentType, boolean allowPartialPayment) {
        lastTransaction.clearPrograms();

        double provision = (paymentType == PaymentType.CARD) ? 0.02 : 0;
        int totalPrice = basket.getTotalPrice();
        double priceWithProvision = Math.ceil(totalPrice * (1 + provision) * 100) / 100;
        ArrayList<ClientProgram> programs = new ArrayList<>(basket.getPrograms());


        if (balance >= priceWithProvision) {
            //full payment
            removeFromBalance(priceWithProvision);
            lastTransaction.setPrograms(programs);
            basket.clearPrograms();
            System.out.println("Paid full basket: " + priceWithProvision);
            return;
        }

        //not enough money
        if (!allowPartialPayment) {
            basket.clearPrograms();
            System.out.println("Not enough funds. Basket and wishlist cleared.");
            return;
        }

        //partial payment mode
        programs.sort(Comparator.comparingInt(ClientProgram::getPrice)); //sort cheapest first


        for (ClientProgram program : programs) {
            int deviceCount = program.getDevices();
            int singularPrice = program.getSingularPrice();
            int maxAffordableDevices = 0;
            int paidTotal = 0;

            for (int i = 1; i <= deviceCount; i++) {
                double priceForI = Math.ceil(singularPrice * i * (1 + (provision / 100.0)));
                if (balance >= paidTotal + priceForI) {
                    maxAffordableDevices = i;
                } else {
                    break;
                }
            }

            if (maxAffordableDevices == 0) {
                continue;
            }
            ClientProgram clonedProgram = program.clone();
            clonedProgram.setDevices(maxAffordableDevices);
            lastTransaction.addProgram(clonedProgram);

            removeFromBalance(program.getSingularPrice() * maxAffordableDevices);
            //we are not able to pay for all devices
            if (maxAffordableDevices < program.getDevices()) {
                program.setDevices(program.getDevices() - maxAffordableDevices);
            } else {
                basket.removeProgram(program);
            }

        }

        System.out.println("Partially paid for programs");

    }

    private void addToBalance(double value) {
        balance += value;
    }

    private void removeFromBalance(double value) {
        balance -= value;
    }

    public boolean hasSubscription() {
        return isSubbed;
    }

    public double getWallet() {
        return balance;
    }

    public Basket getBasket() {
        return basket;
    }

    public String getName() {
        return name;
    }

    public UUID getUID() {
        return uuid;
    }

    public Wishlist getWishlist() {
        return wishList;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', balance=" + balance + ", hasSubscription=" + isSubbed + "}";
    }
}
