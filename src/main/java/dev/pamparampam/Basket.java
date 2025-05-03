package dev.pamparampam;

import dev.pamparampam.Films.ClientProgram;

public class Basket extends ProgramList {

    public int getTotalPrice() {
        int totalPrice = 0;
        for (ClientProgram clientProgram : getPrograms()) {
            totalPrice += clientProgram.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Basket{" + super.toString() + "}";
    }

}
